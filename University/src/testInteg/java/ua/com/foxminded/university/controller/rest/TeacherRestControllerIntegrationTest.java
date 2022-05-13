package ua.com.foxminded.university.controller.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import ua.com.foxminded.university.dto.TeacherDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class TeacherRestControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	static void setup(@Autowired DataSource dataSource) {
		try (Connection connection = dataSource.getConnection()) {
			ScriptUtils.executeSqlScript(connection, new ClassPathResource("/testInteg-data.sql"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void index_shoulReturnTeachers_whenGetTeachers() throws Exception {
		this.mockMvc.perform(
				get("/api/teachers")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].firstName", is("Volodymyrov")));
	}
	
	@Test
	public void show_shoulReturnTeacher_whenGetTeacherById() throws Exception {
		this.mockMvc.perform(
				get("/api/teachers/11")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is("Volodymyrov")));
	}
	
	@Test
	public void create_shoulReturnResponseStatusCreated_whenCreateTeacher() throws Exception {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setFirstName("Test");
		teacherDto.setSecondName("Test");
		teacherDto.setBirthDate(LocalDate.of(2000, 1, 1));
		teacherDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		teacherDto.setPhone("380999999999");
		teacherDto.setEmail("email@mail.xyz");
		
		mockMvc.perform(post("/api/teachers")
				.content(asJsonString(teacherDto))
			    .contentType(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(status().isCreated());
	}
	
	@Test
	public void update_shoulReturnResponseStatusOk_whenUpdateTeacher() throws Exception {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setFirstName("Volodymyrov");
		teacherDto.setSecondName("Volodymyr2");
		teacherDto.setBirthDate(LocalDate.of(2000,10,06));
		teacherDto.setAddress("Country: Ukraine, City: Lytsk, Street: Volodymyrska");
		teacherDto.setPhone("380999999999");
		teacherDto.setEmail("email@mail.xyz");
		
		mockMvc.perform(put("/api/teachers/11")
				.content(asJsonString(teacherDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void delete_shoulReturnResponseStatusOk_whenDeleteTeacher() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/teachers/{id}", "13")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void showLessons_shoulReturnTeacherLessons_whenGetTeacherLessons() throws Exception {
		this.mockMvc.perform(
				get("/api/teachers/11/lessons?startDate=2022-04-01&endDate=2022-06-01")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)));
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
