package com.amv.courseorganizationapp.controllers;

import com.amv.courseorganizationapp.entities.Subject;
import com.amv.courseorganizationapp.exceptions.PersonAlreadyExistsException;
import com.amv.courseorganizationapp.exceptions.RequestValidationException;
import com.amv.courseorganizationapp.loggers.Log;
import com.amv.courseorganizationapp.services.impl.SubjectService;
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
@RequestMapping("/subject")
@Tag(
        name = "Subject",
        description = "This controller controls all data about subjects"
)
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Wrong request", content = @Content(schema = @Schema(implementation = BindingResult.class))),
        @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(implementation = String.class))),
})
public class SubjectController {

    private final SubjectService subjectService;
    private final TeacherService teacherService;

    public SubjectController(SubjectService subjectService, TeacherService teacherService) {
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new subject",
            description = "Given information from the subject, it creates a new one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subject created", content = @Content(schema = @Schema(implementation = Integer.class))),
            @ApiResponse(responseCode = "400", description = "Problems with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with subject data required for the registration", required = true,
            content = @Content(schema = @Schema(implementation = Subject.class)))
    public ResponseEntity<?> registerSubject(@Valid @RequestBody Subject subject, BindingResult bindingResult) {
        try{
            validation(subject.getId());
            subject = subjectService.createSubject(subject);
        }catch (RequestValidationException rve){
            Log.logError(rve.getMessage(), rve);
            return new ResponseEntity<>(rve.getHttpMessage(), rve.getHttpStatus());
        }
        Log.logInfo("Subject created");
        return new ResponseEntity<>(subject.getId(), HttpStatus.CREATED);
    }

    private void validation(Long id) throws RequestValidationException {
        if(subjectService.subjectExists(id)) {
            throw new PersonAlreadyExistsException("Subject exists with this id.");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a subject ",
            description = "Given an id, it can delete the subject from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "subject delete", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Problemas with the entry", content = @Content(schema = @Schema(implementation = BindingResult.class))),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entry with subject's id required for the deletion", required = true,
            content = @Content(schema = @Schema(implementation = Subject.class)))
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        try{
            subjectService.deleteSubject(id);
        } catch (Exception e){
            Log.logError(e.getMessage(), e);
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }

        Log.logInfo("Subject deleted");
        return new ResponseEntity<>("Subject eliminated", HttpStatus.ACCEPTED);
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @RequestBody Map<String, Object> updates){

        if(subjectService.updateSubject(id, updates)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}/teacher")
    public ResponseEntity<?> getSubjectTeacher(@PathVariable Long id){

        if(subjectService.subjectExists(id)){
            Log.logInfo("Subject found");
            return new ResponseEntity<>(teacherService.getTeacherById(subjectService.getSubject(id).getIdTeacher())
            , HttpStatus.OK);
        }
        Log.logWarn("Subject teacher not found");
        return new ResponseEntity<>("Mentor not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSubject(@PathVariable Long id){

        if(subjectService.subjectExists(id)){
            Log.logInfo("Subject found");
            return new ResponseEntity<>(subjectService.getSubject(id), HttpStatus.OK);
        }
        Log.logWarn("Subject not found");
        return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
    }

}
