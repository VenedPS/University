package ua.com.foxminded.university.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import ua.com.foxminded.university.converter.LessonConverter;
import ua.com.foxminded.university.converter.TeacherConverter;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.exception.LessonNotFoundException;
import ua.com.foxminded.university.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    
    private final TeacherService teacherService;
    private final TeacherConverter teacherConverter;
    private final LessonConverter lessonConverter;
    private final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    
    @Autowired
    public TeacherController(
    		TeacherService teacherService, 
    		TeacherConverter teacherConverter,
    		LessonConverter lessonConverter) {
    	
        this.teacherService = teacherService;
        this.teacherConverter = teacherConverter;
        this.lessonConverter = lessonConverter;
    }
    
    @GetMapping()
    public String index(Model model) {
        List<TeacherDto> teachers = teacherConverter.toDtoList(teacherService.readAll());
    	model.addAttribute("teachers", teachers);
        return "teachers/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
    	TeacherDto teacherDto = teacherConverter.toDto(teacherService.readById(id));
    	model.addAttribute("teacher", teacherDto);
        
        LocalDate startDate = LocalDate.now().plusMonths(-2).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1);        
        List<LessonDto> lessons = new ArrayList<LessonDto>();
        try {
            lessons = lessonConverter.toDtoList(teacherService.getTeacherLessons(id, startDate, endDate));
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
    public String create(@ModelAttribute("teacher") @Valid TeacherDto teacherDto,
    		BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()) {
    		return "teachers/new";
    	}
        
    	teacherService.create(teacherConverter.toEntity(teacherDto));
        return "redirect:/teachers";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        TeacherDto teacherDto = teacherConverter.toDto(teacherService.readById(id));
    	model.addAttribute("teacher", teacherDto);
        return "teachers/edit";
    }
    
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("teacher") @Valid TeacherDto teacherDto,
    		BindingResult bindingResult) {
    	
    	if(bindingResult.hasErrors()) {
    		return "teachers/edit";
    	}
    	
    	teacherService.update(teacherConverter.toEntity(teacherDto));
        return "redirect:/teachers";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        teacherService.delete(id);
        return "redirect:/teachers";
    }

}
