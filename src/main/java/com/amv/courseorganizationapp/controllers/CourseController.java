package com.amv.courseorganizationapp.controllers;

import com.amv.courseorganizationapp.entities.Course;
import com.amv.courseorganizationapp.exceptions.PersonAlreadyExistsException;
import com.amv.courseorganizationapp.exceptions.RequestValidationException;
import com.amv.courseorganizationapp.loggers.Log;
import com.amv.courseorganizationapp.services.impl.CourseService;
import com.amv.courseorganizationapp.services.impl.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/course")
@Tag(
        name = "Course",
        description = "This controller control all data about courses"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Wrong request", content = @Content(schema = @Schema(implementation = BindingResult.class))),
        @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = String.class))),

})
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;


    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new course",
                description = "Given information from the course, it creates a new one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created", content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "Problems with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with course data required for the registration", required = true,
            content = @Content(schema = @Schema(implementation = Course.class)))
    public ResponseEntity<?> registerCourse(@Valid @RequestBody Course course, BindingResult bindingResult) {
        try{
            validation(course.getId());
            course = courseService.createCourse(course);
        }catch (RequestValidationException rve){
            Log.logError(rve.getMessage(), rve);
            return new ResponseEntity<>(rve.getHttpMessage(), rve.getHttpStatus());
        }
        Log.logInfo("Course created");
        return new ResponseEntity<>(course.getId(), HttpStatus.CREATED);
    }

    private void validation(Long id) throws RequestValidationException {
        if(courseService.courseExists(id)) {
            throw new PersonAlreadyExistsException("Course exists with this id.");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a course ",
            description = "Given a id, it can delete the course from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course delete", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Problemas with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with course's id required for the deletion", required = true,
            content = @Content(schema = @Schema(implementation = Course.class)))
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        try{
            courseService.deleteCourse(id);
        } catch (Exception e){
            Log.logError(e.getMessage(), e);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }

        Log.logInfo("Course deleted");
        return new ResponseEntity<>("Course eliminated", HttpStatus.ACCEPTED);
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Map<String, Object> updates){

        if(courseService.updateCourse(id, updates)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}/teacher")
    public ResponseEntity<?> getCourseTeacher(@PathVariable Long id){

        if(courseService.courseExists(id)){
            Log.logInfo("Course found");
            return new ResponseEntity<>(teacherService.getTeacherById(courseService.getCourse(id).getIdTeacher())
                    , HttpStatus.OK);
        }
        Log.logWarn("Course teacher not found");
        return new ResponseEntity<>("Mentor not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id){

        if(courseService.courseExists(id)){
            Log.logInfo("Course found");
            return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
        }
        Log.logWarn("Course not found");
        return new ResponseEntity<>("course not found", HttpStatus.NOT_FOUND);
    }


}
