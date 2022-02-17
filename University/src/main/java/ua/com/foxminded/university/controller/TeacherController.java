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
import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    
    private final TeacherService teacherService;
    private final Logger logger = LoggerFactory.getLogger(LessonDaoSql.class);
    
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("teachers", teacherService.readAll());
        return "teachers/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", teacherService.readById(id));
        
        LocalDate startDate = LocalDate.now().plusMonths(-2).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);        
        List<LessonDto> lessons = new ArrayList<LessonDto>();
        try {
            lessons = teacherService.getTeacherLessons(id, startDate, endDate);
        } catch (LessonNotFoundException e) {
            logger.error(e.getMessage());
        }
        model.addAttribute("lessons", lessons);
        return "teachers/show";
    }
    
    @GetMapping("/new")
    public String newTeacher(@ModelAttribute("teacher") TeacherDto teacherDto) {
        return "teachers/new";
    }
   
    @PostMapping()
    public String create(@ModelAttribute("teacher") TeacherDto teacherDto) {
        teacherService.create(teacherDto);
        return "redirect:/teachers";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("teacher", teacherService.readById(id));
        return "teachers/edit";
    }
    
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("teacher") TeacherDto teacherDto) {
        teacherService.update(teacherDto);
        return "redirect:/teachers";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        teacherService.delete(id);
        return "redirect:/teachers";
    }

}
