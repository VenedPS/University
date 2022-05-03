package ua.com.foxminded.university.controller.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.service.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherRestController {
    
    private final TeacherService teacherService;
    
    @Autowired
    public TeacherRestController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    
    @GetMapping()
    public ResponseEntity<List<TeacherDto>> index(Model model) {
        final List<TeacherDto> teachers =  teacherService.readAll();

        if(teachers != null && !teachers.isEmpty()) {
        	return new ResponseEntity<>(teachers, HttpStatus.OK);
        }        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> show(@PathVariable("id") int id) {
    	final TeacherDto teacherDto = teacherService.readById(id);
       	return new ResponseEntity<>(teacherDto, HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid TeacherDto teacherDto) {
    	teacherService.create(teacherDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody @Valid TeacherDto teacherDto) {
    	
    	teacherDto.setId(id);    	
    	teacherService.update(teacherDto);

    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
    	teacherService.delete(id);
    	   return new ResponseEntity<>(HttpStatus.OK);
    	}
    
    @GetMapping("/{id}/lessons")
    public ResponseEntity<List<LessonDto>> showLessons(
    		@PathVariable("id") int id,
    		@RequestParam("startDate") String startDateLine, 
    		@RequestParam("endDate") String endDateLine) {
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
    	LocalDate startDate = LocalDate.parse(startDateLine, formatter);
        LocalDate endDate =  LocalDate.parse(endDateLine, formatter);        
        List<LessonDto> lessons = new ArrayList<LessonDto>();
        lessons = teacherService.getTeacherLessons(id, startDate, endDate);
        
        if(lessons != null && !lessons.isEmpty()) {
        	return new ResponseEntity<>(lessons, HttpStatus.OK);
        }        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
