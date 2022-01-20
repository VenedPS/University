package ua.com.foxminded.university.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping("/students")
    public String helloPage() {
        System.out.println("111");
        return "students/studentsList";
    }
}
