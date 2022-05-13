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

import ua.com.foxminded.university.dto.StudentDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class StudentRestControllerIntegrationTest {

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
	public void index_shoulReturnStudents_whenGetStudents() throws Exception {
		this.mockMvc.perform(
				get("/api/students")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].firstName", is("Ivanov")));
	}
	
	@Test
	public void show_shoulReturnStudent_whenGetStudentById() throws Exception {
		this.mockMvc.perform(
				get("/api/students/11")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is("Ivanov")));
	}
	
	@Test
	public void create_shoulReturnResponseStatusCreated_whenCreateStudent () throws Exception {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("Test");
		studentDto.setSecondName("Test");
		studentDto.setBirthDate(LocalDate.of(2000, 1, 1));
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		studentDto.setGroupId(1);
		
		mockMvc.perform(post("/api/students")
				.content(asJsonString(studentDto))
			    .contentType(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(status().isCreated());
	}
	
	@Test
	public void update_shoulReturnResponseStatusOk_whenUpdateStudent () throws Exception {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("Ivanov");
		studentDto.setSecondName("Ivan2");
		studentDto.setBirthDate(LocalDate.of(2000,10,06));
		studentDto.setAddress("Country: Ukraine, City: Lytsk, Street: Volodymyrska");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		studentDto.setGroupId(1);
		
		mockMvc.perform(put("/api/students/11")
				.content(asJsonString(studentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void delete_shoulReturnResponseStatusOk_whenDeleteStudent () throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/students/{id}", "13")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void showLessons_shoulReturnStudentLessons_whenGetStudentLessons() throws Exception {
		this.mockMvc.perform(
				get("/api/students/11/lessons?startDate=2022-04-01&endDate=2022-06-01")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
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

