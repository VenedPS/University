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
import ua.com.foxminded.university.service.StudentService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ControllerTestConfig.class })
public class StudentControllerTest {

    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.studentController).build();
    }

    @Test
    public void index_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(MockMvcResultMatchers.view().name("students/index"));
    }

    @Test
    public void show_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/0"))
                .andExpect(MockMvcResultMatchers.view().name("students/show"));
    }

    @Test
    public void newStudent_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/new"))
                .andExpect(MockMvcResultMatchers.view().name("students/new"));
    }

    @Test
    public void create_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/students"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
    }

    @Test
    public void edit_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/0/edit"))
                .andExpect(MockMvcResultMatchers.view().name("students/edit"));
    }

    @Test
    public void update_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/students/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
    }

    @Test
    public void delete_shouldReturnViewName_whenMockMvc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/students/0"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
    }
}
