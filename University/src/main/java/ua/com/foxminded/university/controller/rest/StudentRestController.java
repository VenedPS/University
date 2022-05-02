package ua.com.foxminded.university.controller.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.com.foxminded.university.controller.StudentController;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentRestController {

	private final StudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<List<StudentDto>> index(Model model) {
        final List<StudentDto> students = studentService.readAll();

        if(students != null && !students.isEmpty()) {
        	return new ResponseEntity<>(students, HttpStatus.OK);
        }        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> show(@PathVariable("id") int id) {
    	final StudentDto studentDto = studentService.readById(id);
       	return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid StudentDto studentDto) {
        studentService.create(studentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody @Valid StudentDto studentDto) {
    	
    	studentDto.setId(id);    	
    	studentService.update(studentDto);

    	return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
    	   studentService.delete(id);
    	   return new ResponseEntity<>(HttpStatus.OK);
    	}
    
    @GetMapping("/{id}/lessons")
    public ResponseEntity<List<LessonDto>> showLessons(
    		@PathVariable("id") int id,
    		@RequestParam("startDate") String startDateLine, 
    		@RequestParam("endDate") String endDateLine) {
    	
    	StudentDto studentDto = studentService.readById(id);
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
    	LocalDate startDate = LocalDate.parse(startDateLine, formatter);
        LocalDate endDate =  LocalDate.parse(endDateLine, formatter);        
        
        List<LessonDto> lessons = new ArrayList<LessonDto>();
        try {
            lessons = studentService.getStudentLessons(studentDto, startDate, endDate);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }
        
        if(lessons != null && !lessons.isEmpty()) {
        	return new ResponseEntity<>(lessons, HttpStatus.OK);
        }        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
