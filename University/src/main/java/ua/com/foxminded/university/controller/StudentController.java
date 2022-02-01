package ua.com.foxminded.university.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.foxminded.university.dao.sql.LessonDaoSql;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.service.LessonService;
import ua.com.foxminded.university.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final LessonService lessonService;
    private final Logger logger = LoggerFactory.getLogger(LessonDaoSql.class);

    @Autowired
    public StudentController(StudentService studentService, LessonService lessonService) {
        this.studentService = studentService;
        this.lessonService = lessonService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("students", studentService.readAll());
        return "students/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentService.readById(id));
        
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);        
        List<LessonDto> lessons = new ArrayList<LessonDto>();
        try {
            lessons = lessonService.getStudentLessons(id, startDate, endDate);
        } catch (LessonNotFoundException e) {
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
    public String create(@ModelAttribute("student") StudentDto studentDto) {
        studentDto.setId(0 + (int) (Math.random() * ((1000 - 0) + 1)));
        studentService.create(studentDto);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("student", studentService.readById(id));
        return "students/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") StudentDto studentDto) {
        studentService.update(studentDto);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        studentService.delete(id);
        return "redirect:/students";
    }

}
