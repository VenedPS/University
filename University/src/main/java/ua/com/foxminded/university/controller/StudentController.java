package ua.com.foxminded.university.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("students", studentService.readAll());
        return "students/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        StudentDto studentDto = studentService.readById(id);
    	model.addAttribute("student", studentDto);
        
        LocalDate startDate = LocalDate.now().plusMonths(-2).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);        
        List<LessonDto> lessons = new ArrayList<LessonDto>();
        try {
            lessons = studentService.getStudentLessons(studentDto, startDate, endDate);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("lessons", lessons);
        return "students/show";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("student") StudentDto studentDto) {
        return "students/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("student") @Valid StudentDto studentDto,
    		BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()) {
    		return "students/new";
    	}
        studentDto.setGroupId(1);
        studentService.create(studentDto);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
    	model.addAttribute("student", studentService.readById(id));
        return "students/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") @Valid StudentDto studentDto,
    		BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()) {
    		return "students/edit";
    	}
    	studentDto.setGroupId(1);
    	studentService.update(studentDto);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        studentService.delete(id);
        return "redirect:/students";
    }

}
