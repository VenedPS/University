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
import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.service. TeacherService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TeacherRestController.class)
public class TeacherRestControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;
    
    private static TeacherDto teacherDto = new TeacherDto();
    
	@BeforeAll
	static void setup() {
		teacherDto.setId(1);
		teacherDto.setFirstName("Test");
		teacherDto.setSecondName("Test");
		teacherDto.setBirthDate(LocalDate.of(2000, 1, 1));
		teacherDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		teacherDto.setPhone("380999999999");
		teacherDto.setEmail("email@mail.xyz");
	}
	
    @Test
    public void index_shouldReturnTeachers_whenTeacherListMocked() throws Exception {
        List<TeacherDto> teachers = Arrays.asList(teacherDto);

        BDDMockito.given(teacherService.readAll()).willReturn(teachers);

		mockMvc.perform(get("/api/teachers")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].firstName", is(teacherDto.getFirstName())));
    }
    
    @Test
    public void index_shouldReturnNotFound_whenNullMocked() throws Exception {
    	BDDMockito.given(teacherService.readAll()).willReturn(null);
    	
    	mockMvc.perform(get("/api/teachers")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isNotFound());
    }
    
    @Test
    public void show_shouldReturnTeacher_whenTeacherMocked() throws Exception {
    	BDDMockito.given(teacherService.readById(1)).willReturn(teacherDto);
    	
    	mockMvc.perform(get("/api/teachers/1")
    			.contentType(MediaType.APPLICATION_JSON))
		    	.andExpect(status().isOk())
		    	.andExpect(jsonPath("$.firstName", is(teacherDto.getFirstName())));
    }
    
    @Test
    public void show_shouldReturnNotFound_whenTeacherNotExist() throws Exception {
    	mockMvc.perform(get("/api/teachers/2"))
		    	.andExpect(status().isNotFound());
    }
    
    @Test
    public void create_shouldReturnCreated_whenTeacherDtoValid() throws Exception {
    	teacherDto.setBirthDate(LocalDate.of(2000, 1, 1));
    	
    	Mockito.doNothing().when(teacherService).create(teacherDto);
        mockMvc.perform(post("/api/teachers")
        		.content(asJsonString(teacherDto))
			    .contentType(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void create_shouldReturnValidationErrorMessage_whenTeacherDtoNotValid() throws Exception {
    	String expectedPart = "Invalid birth date";

		teacherDto.setBirthDate(LocalDate.of(2020, 1, 1));
    	
    	Mockito.doNothing().when(teacherService).create(teacherDto);
    	mockMvc.perform(post("/api/teachers")
    			.content(asJsonString(teacherDto))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
		    	.andExpect(status().isBadRequest())
		    	.andExpect(content().string(containsString(expectedPart)));
    }
    
    @Test
    public void create_shouldReturnBadRequest_whenTeacherIsNull() throws Exception {
    	Mockito.doNothing().when(teacherService).create(null);
    	mockMvc.perform(post("/api/teachers")
    			.content(asJsonString(null))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void create_shouldReturnBadRequest_whenMockedNoSuchElementException() throws Exception {
    	Mockito.doThrow(NoSuchElementException.class).when(teacherService).create(null);
    	mockMvc.perform(post("/api/teachers")
    			.content(asJsonString(null))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void update_shouldReturnOk_whenTeacherDtoValid() throws Exception {
    	teacherDto.setBirthDate(LocalDate.of(2000, 1, 1));
    	
    	Mockito.doNothing().when(teacherService).update(teacherDto);
    	mockMvc.perform(put("/api/teachers/1")
    			.content(asJsonString(teacherDto))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk());
    }
    
    @Test
    public void update_shouldReturnValidationErrorMessage_whenTeacherDtoNotValid() throws Exception {
    	String expectedPart = "Invalid birth date";
    	
    	teacherDto.setBirthDate(LocalDate.of(2020, 1, 1));
    	
    	Mockito.doNothing().when(teacherService).update(teacherDto);
    	mockMvc.perform(put("/api/teachers/1")
    			.content(asJsonString(teacherDto))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
		    	.andExpect(status().isBadRequest())
		    	.andExpect(content().string(containsString(expectedPart)));
    }
    
    @Test
    public void update_shouldReturnBadRequest_whenTeacherIsNull() throws Exception {
    	Mockito.doNothing().when(teacherService).update(null);
    	mockMvc.perform(put("/api/teachers/1")
    			.content(asJsonString(null))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void update_shouldReturnBadRequest_whenMockedNoSuchElementException() throws Exception {
    	Mockito.doThrow(NoSuchElementException.class).when(teacherService).update(null);
    	mockMvc.perform(put("/api/teachers/1")
    			.content(asJsonString(null))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void delete_shouldReturnOk_whenTeacherMocked() throws Exception {
    	Mockito.doNothing().when(teacherService).delete(1);
    	mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/teachers/1")
    			.content(asJsonString(teacherDto))
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk());
    }
    
    @Test
    public void delete_shouldReturnBadRequest_whenMockedIncorrectResultSizeDataAccessException() throws Exception {
    	Mockito.doThrow(IncorrectResultSizeDataAccessException.class).when(teacherService).delete(1);
    	mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/teachers/1"))
				.andExpect(status().isBadRequest());
    }
    
    @Test
    public void delete_shouldReturnNotFound_whenMockedNoSuchElementException() throws Exception {
    	Mockito.doThrow(NoSuchElementException.class).when(teacherService).delete(1);
    	mockMvc.perform(MockMvcRequestBuilders
    			.delete("/api/teachers/1"))
    			.andExpect(status().isNotFound());
    }
    
    @Test
    public void showLessons_shouldReturnTeacherLessons_whenLessonsMocked() throws Exception {
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

        BDDMockito.given(teacherService.getTeacherLessons(1, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 1))).willReturn(lessons);

		mockMvc.perform(get("/api/teachers/1/lessons?startDate=2022-04-01&endDate=2022-06-01")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(teacherDto.getId())));
    } 
    
    @Test
    public void showLessons_shouldReturnNotFound_whenTeacherIsNull() throws Exception {
    	BDDMockito.given(teacherService.getTeacherLessons(2, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 1))).willReturn(null);
    	
    	mockMvc.perform(get("/api/teachers/2/lessons?startDate=2022-04-01&endDate=2022-06-01")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isNotFound());
    } 
    
    @Test
    public void showLessons_shouldReturnBadRequest_whenMockedIncorrectResultSizeDataAccessException() throws Exception {
    	Mockito.doThrow(IncorrectResultSizeDataAccessException.class).when(teacherService).getTeacherLessons(2, LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 1));
    	mockMvc.perform(get("/api/teachers/2/lessons?startDate=2022-04-01&endDate=2022-06-01")
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
