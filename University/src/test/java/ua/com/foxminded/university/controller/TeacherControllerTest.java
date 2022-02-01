package ua.com.foxminded.university.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.com.foxminded.university.config.ControllerTestConfig;
import ua.com.foxminded.university.service. TeacherService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ControllerTestConfig.class })
public class TeacherControllerTest {

    @Autowired
    private TeacherController teacherController;

    @MockBean
    private TeacherService teacherService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.teacherController).build();
    }

    @Test
    public void index_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers"))
                .andExpect(MockMvcResultMatchers.view().name("teachers/index"));
    }

    @Test
    public void show_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/0"))
                .andExpect(MockMvcResultMatchers.view().name("teachers/show"));
    }

    @Test
    public void newTeacher_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/new"))
                .andExpect(MockMvcResultMatchers.view().name("teachers/new"));
    }

    @Test
    public void create_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/teachers"));
    }

    @Test
    public void edit_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/0/edit"))
                .andExpect(MockMvcResultMatchers.view().name("teachers/edit"));
    }

    @Test
    public void update_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/teachers/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/teachers"));
    }

    @Test
    public void delete_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/teachers/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/teachers"));
    }
}

