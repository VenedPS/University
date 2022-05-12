package ua.com.foxminded.university.controller.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.service.StudentService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentRestController.class)
public class StudentRestControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    
    private static StudentDto studentDto = new StudentDto();
    
	@BeforeAll
	static void setup() {
		studentDto.setId(1);
		studentDto.setFirstName("Test");
		studentDto.setSecondName("Test");
		studentDto.setBirthDate(LocalDate.of(2000, 1, 1));
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		studentDto.setGroupId(1);	
	}
	
    @Test
    public void index_shouldReturnStudents_whenStudentListMocked() throws Exception {
        List<StudentDto> students = Arrays.asList(studentDto);

        BDDMockito.given(studentService.readAll()).willReturn(students);

		mockMvc.perform(get("/api/students")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].firstName", is(studentDto.getFirstName())));
    }
    
    @Test
    public void index_shouldReturnNotFound_whenNullMocked() throws Exception {
    	BDDMockito.given(studentService.readAll()).willReturn(null);
    	
    	mockMvc.perform(get("/api/students")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isNotFound());
    }
    
    @Test
    public void show_shouldReturnStudent_whenStudentMocked() throws Exception {
    	BDDMockito.given(studentService.readById(1)).willReturn(studentDto);
    	
    	mockMvc.perform(get("/api/students/1")
    			.contentType(MediaType.APPLICATION_JSON))
		    	.andExpect(status().isOk())
		    	.andExpect(jsonPath("$.firstName", is(studentDto.getFirstName())));
    }
    
    @Test
    public void show_shouldReturnNotFound_whenStudentNotExist() throws Exception {
    	mockMvc.perform(get("/api/students/2"))
		    	.andExpect(status().isNotFound());
    }
    
    @Test
    public void create_shouldReturnCreated_whenStudentDtoValid() throws Exception {
    	studentDto.setBirthDate(LocalDate.of(2000, 1, 1));
    	
    	Mockito.doNothing().when(studentService).create(studentDto);
        mockMvc.perform(post("/api/students")
        		.content(asJsonString(studentDto))
			    .contentType(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void create_shouldReturnValidationErrorMessage_whenStudentDtoNotValid() throws Exception {
    	String expectedPart = "Invalid birth date";

		studentDto.setBirthDate(LocalDate.of(2020, 1, 1));
    	
    	Mockito.doNothing().when(studentService).create(studentDto);
    	mockMvc.perform(post("/api/students")
    			.content(asJsonString(studentDto))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
		    	.andExpect(status().isBadRequest())
		    	.andExpect(content().string(containsString(expectedPart)));
    }
    
    @Test
    public void create_shouldReturnBadRequest_whenStudentIsNull() throws Exception {
    	Mockito.doNothing().when(studentService).create(null);
    	mockMvc.perform(post("/api/students")
    			.content(asJsonString(null))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void create_shouldReturnBadRequest_whenMockedNoSuchElementException() throws Exception {
    	Mockito.doThrow(NoSuchElementException.class).when(studentService).create(null);
    	mockMvc.perform(post("/api/students")
    			.content(asJsonString(null))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void update_shouldReturnOk_whenStudentDtoValid() throws Exception {
    	studentDto.setBirthDate(LocalDate.of(2000, 1, 1));
    	
    	Mockito.doNothing().when(studentService).update(studentDto);
    	mockMvc.perform(put("/api/students/1")
    			.content(asJsonString(studentDto))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk());
    }
    
    @Test
    public void update_shouldReturnValidationErrorMessage_whenStudentDtoNotValid() throws Exception {
    	String expectedPart = "Invalid birth date";
    	
    	studentDto.setBirthDate(LocalDate.of(2020, 1, 1));
    	
    	Mockito.doNothing().when(studentService).update(studentDto);
    	mockMvc.perform(put("/api/students/1")
    			.content(asJsonString(studentDto))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
		    	.andExpect(status().isBadRequest())
		    	.andExpect(content().string(containsString(expectedPart)));
    }
    
    @Test
    public void update_shouldReturnBadRequest_whenStudentIsNull() throws Exception {
    	Mockito.doNothing().when(studentService).update(null);
    	mockMvc.perform(put("/api/students/1")
    			.content(asJsonString(null))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void update_shouldReturnBadRequest_whenMockedNoSuchElementException() throws Exception {
    	Mockito.doThrow(NoSuchElementException.class).when(studentService).update(null);
    	mockMvc.perform(put("/api/students/1")
    			.content(asJsonString(null))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void delete_shouldReturnOk_whenStudentMocked() throws Exception {
    	Mockito.doNothing().when(studentService).delete(1);
    	mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/students/1")
    			.content(asJsonString(studentDto))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk());
    }
    
    @Test
    public void delete_shouldReturnBadRequest_whenMockedIncorrectResultSizeDataAccessException() throws Exception {
    	Mockito.doThrow(IncorrectResultSizeDataAccessException.class).when(studentService).delete(1);
    	mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/students/1"))
				.andExpect(status().isBadRequest());
    }
    
    @Test
    public void delete_shouldReturnNotFound_whenMockedNoSuchElementException() throws Exception {
    	Mockito.doThrow(NoSuchElementException.class).when(studentService).delete(1);
    	mockMvc.perform(MockMvcRequestBuilders
    			.delete("/api/students/1"))
    			.andExpect(status().isNotFound());
    }
    
    @Test
    public void showLessons_shouldReturnStudentLessons_whenLessonsMocked() throws Exception {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(1);
        lessonDto.setTimetableId(1);
        lessonDto.setDate(LocalDate.of(2022, 5, 1));
        lessonDto.setLessonNumber(1);
        lessonDto.setGroupId(1);
        lessonDto.setCourseId(1);
        lessonDto.setClassroomId(1);
        lessonDto.setTeacherId(1);
    	
    	List<LessonDto> lessons = Arrays.asList(lessonDto);

        BDDMockito.given(studentService.getStudentLessons(null, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 1))).willReturn(lessons);

		mockMvc.perform(get("/api/students/1/lessons?startDate=2022-04-01&endDate=2022-06-01")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(studentDto.getId())));
    } 
    
    @Test
    public void showLessons_shouldReturnNotFound_whenStudentIsNull() throws Exception {
    	BDDMockito.given(studentService.getStudentLessons(null, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 1))).willReturn(null);
    	
    	mockMvc.perform(get("/api/students/1/lessons?startDate=2022-04-01&endDate=2022-06-01")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isNotFound());
    } 
    
    @Test
    public void showLessons_shouldReturnBadRequest_whenMockedIncorrectResultSizeDataAccessException() throws Exception {
    	Mockito.doThrow(IncorrectResultSizeDataAccessException.class).when(studentService).getStudentLessons(null, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 1));
    	mockMvc.perform(get("/api/students/1/lessons?startDate=2022-04-01&endDate=2022-06-01")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isBadRequest());
    } 
    
	public static String asJsonString(final Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			return objectMapper.writeValueAsString(object);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
}
