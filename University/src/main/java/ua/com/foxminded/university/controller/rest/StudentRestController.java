package ua.com.foxminded.university.controller.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.StudentDto;
import ua.com.foxminded.university.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

	private final StudentService studentService;

	@Autowired
	public StudentRestController(StudentService studentService) {
		this.studentService = studentService;
	}

	@Operation(summary = "Get all students")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Students were found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
			@ApiResponse(responseCode = "404", description = "No students found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping()
	public ResponseEntity<List<StudentDto>> index(Model model) {
		final List<StudentDto> students = studentService.readAll();

		if (students != null && !students.isEmpty()) {
			return new ResponseEntity<>(students, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Get a student by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The student was found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied"),
			@ApiResponse(responseCode = "404", description = "Student not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> show(@PathVariable("id") int id) {
		final StudentDto studentDto = studentService.readById(id);
		if(studentDto == null) {
			return new ResponseEntity<>(studentDto, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(studentDto, HttpStatus.OK);
	}

	@Operation(summary = "Create student")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The student was created"),
			@ApiResponse(responseCode = "400", description = "Invalid data supplied"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody @Valid StudentDto studentDto) {
		studentService.create(studentDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "Update student")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The student was updated"),
			@ApiResponse(responseCode = "400", description = "Invalid data supplied"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody @Valid StudentDto studentDto) {

		studentDto.setId(id);
		studentService.update(studentDto);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Delete student by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The student was deleted"),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
		studentService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Get students lessons for a period")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lessons were found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = LessonDto.class)) }),
			@ApiResponse(responseCode = "404", description = "No lessons found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/{id}/lessons")
	public ResponseEntity<List<LessonDto>> showLessons(@PathVariable("id") int id,
			@RequestParam("startDate") String startDateLine, @RequestParam("endDate") String endDateLine) {

		StudentDto studentDto = studentService.readById(id);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(startDateLine, formatter);
		LocalDate endDate = LocalDate.parse(endDateLine, formatter);
		List<LessonDto> lessons = new ArrayList<LessonDto>();
		lessons = studentService.getStudentLessons(studentDto, startDate, endDate);

		if (lessons != null && !lessons.isEmpty()) {
			return new ResponseEntity<>(lessons, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
