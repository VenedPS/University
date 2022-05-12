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
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ua.com.foxminded.university.dto.LessonDto;
import ua.com.foxminded.university.dto.TeacherDto;
import ua.com.foxminded.university.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherRestController {
    
    private final TeacherService teacherService;
    
    @Autowired
    public TeacherRestController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    
    @Operation(summary = "Get all teachers")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Teachers were found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
			@ApiResponse(responseCode = "404", description = "No teachers found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping()
    public ResponseEntity<List<TeacherDto>> index(Model model) {
        final List<TeacherDto> teachers =  teacherService.readAll();

        if(teachers != null && !teachers.isEmpty()) {
        	return new ResponseEntity<>(teachers, HttpStatus.OK);
        }        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @Operation(summary = "Get a teacher by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The teacher was found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied"),
			@ApiResponse(responseCode = "404", description = "Teacher not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> show(@PathVariable("id") int id) {
    	final TeacherDto teacherDto = teacherService.readById(id);
    	if(teacherDto == null) {
			return new ResponseEntity<>(teacherDto, HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(teacherDto, HttpStatus.OK);
    }
    
    @Operation(summary = "Create teacher")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The teacher was created"),
			@ApiResponse(responseCode = "400", description = "Invalid data supplied"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid TeacherDto teacherDto) {
    	teacherService.create(teacherDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @Operation(summary = "Update teacher")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The teacher was updated"),
			@ApiResponse(responseCode = "400", description = "Invalid data supplied"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody @Valid TeacherDto teacherDto) {
    	
    	teacherDto.setId(id);    	
    	teacherService.update(teacherDto);

    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @Operation(summary = "Delete teacher by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The teacher was deleted"),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
    	teacherService.delete(id);
    	   return new ResponseEntity<>(HttpStatus.OK);
    	}
    
    @Operation(summary = "Get teachers lessons for a period")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lessons were found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = LessonDto.class)) }),
			@ApiResponse(responseCode = "404", description = "No lessons found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/{id}/lessons")
    public ResponseEntity<List<LessonDto>> showLessons(
    		@PathVariable("id") int id,
    		@RequestParam("startDate") String startDateLine, 
    		@RequestParam("endDate") String endDateLine) {
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
    	LocalDate startDate = LocalDate.parse(startDateLine, formatter);
        LocalDate endDate =  LocalDate.parse(endDateLine, formatter);        
        List<LessonDto> lessons = new ArrayList<LessonDto>();
        lessons = teacherService.getTeacherLessons(id, startDate, endDate);
        
        if(lessons != null && !lessons.isEmpty()) {
        	return new ResponseEntity<>(lessons, HttpStatus.OK);
        }        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
