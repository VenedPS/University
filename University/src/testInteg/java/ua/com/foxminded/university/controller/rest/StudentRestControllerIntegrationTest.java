package ua.com.foxminded.university.controller.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ua.com.foxminded.university.Main;
import ua.com.foxminded.university.dto.StudentDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class StudentRestControllerIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@BeforeAll
    static void setup(@Autowired DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("/test-data.sql"));
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void index_shouldReturnStudents_whenGetStudents() {
		String expectedPart = "{\"firstName\":\"Ivanov\",\"secondName\":\"Ivan";
		
		assertThat(this.restTemplate.getForObject(createURLWithPort("/api/students"),
				String.class)).contains(expectedPart);
	}
	
	@Test
	public void show_shouldReturnStudent_whenGetStudentById() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/students/11"),
				HttpMethod.GET, entity, String.class);

		String expected = "{id:11,firstName:Ivanov,secondName:Ivan,birthDate:2000-10-06,address:\"Country: Ukraine, City: Kyiv, Street: Drahomanova\",phone:\"380999999999\",email:email@mail.xyz,groupId:1}";
	    
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}

	@Test
	public void create_shouldReturnResponseStatusCreated_whenCreateStudent() {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("Test");
		studentDto.setSecondName("Test");
		studentDto.setBirthDate(LocalDate.of(2000, 1, 1));
		studentDto.setAddress("Country: Ukraine, City: Kyiv, Street: Drahomanova");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		studentDto.setGroupId(1);
		
		HttpEntity<StudentDto> entity = new HttpEntity<StudentDto>(studentDto, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/students"),
				HttpMethod.POST, entity, String.class);

		assertEquals(response.getStatusCode(),HttpStatus.CREATED);
	}
	
	@Test
	public void update_shouldReturnResponseStatusOk_whenUpdateStudent() {
		StudentDto studentDto = new StudentDto();
		studentDto.setFirstName("Sidorov");
		studentDto.setSecondName("Sidor1");
		studentDto.setBirthDate(LocalDate.of(2000, 10, 06));
		studentDto.setAddress("Country: Ukraine, City: Kharkiv, Street: Myru");
		studentDto.setPhone("380999999999");
		studentDto.setEmail("email@mail.xyz");
		studentDto.setGroupId(2);
		
		HttpEntity<StudentDto> entity = new HttpEntity<StudentDto>(studentDto, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/students/13"),
				HttpMethod.PUT, entity, String.class);
		
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void delete_shouldReturnResponseStatusOk_whenDeleteStudent() {
		StudentDto studentDto = new StudentDto();
		HttpEntity<StudentDto> entity = new HttpEntity<StudentDto>(studentDto, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/students/14"),
				HttpMethod.DELETE, entity, String.class);
		
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void showLessons_shouldReturnStudentLessons_whenGetStudentLessons() {
		String expectedPart = "{\"id\":1,\"timetableId\":1,\"date\":\"2022-04-19\",\"lessonNumber\":1,\"groupId\":1,\"courseId\":1,\"classroomId\":1,\"teacherId\":11}";

		assertThat(this.restTemplate.getForObject(createURLWithPort("/api/students/11/lessons?startDate=2022-04-01&endDate=2022-06-01"),
				String.class)).contains(expectedPart);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
