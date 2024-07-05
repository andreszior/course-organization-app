package com.amv.courseorganizationapp.controllers;

import com.amv.courseorganizationapp.entities.Teacher;
import com.amv.courseorganizationapp.exceptions.PersonAlreadyExistsException;
import com.amv.courseorganizationapp.exceptions.RequestValidationException;
import com.amv.courseorganizationapp.loggers.Log;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@Tag(
        name = "Teacher",
        description = "This controller control all data about teachers"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Wrong request", content = @Content(schema = @Schema(implementation = BindingResult.class))),
        @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(schema = @Schema(implementation = String.class))),
})
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @PostMapping("/register")
    @Operation(summary = "Resgister a new Teacher ",
            description= "Given information from the teacher, it creats a new entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher created", content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "Problems with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with teacher data required for the registration", required = true,
    content = @Content(schema = @Schema(implementation = Teacher.class)))
    public ResponseEntity<?> registerTeacher(@Valid @RequestBody Teacher teacher, BindingResult bindingResult) {
        try{
            validation(teacher.getDni());
            teacher = teacherService.createTeacher(teacher);

        } catch (RequestValidationException rve) {
            Log.logError(rve.getMessage(), rve);
            return new ResponseEntity<>(rve.getHttpMessage(), rve.getHttpStatus());
        } catch (Exception e) {
            Log.logError(e.getMessage(), e);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }

        Log.logInfo("teacher registered");
        return new ResponseEntity<>(teacher.getId(), HttpStatus.CREATED);
    }


    private void validation(String dni) throws RequestValidationException {
        if(teacherService.teacherExists(dni)){
            throw new PersonAlreadyExistsException("User exists with this dni.");
        }
    }


}
