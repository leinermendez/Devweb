package ucr.ac.cr.Devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.Devweb.model.DTO.VerificationDTO;
import ucr.ac.cr.Devweb.model.Verification;
import ucr.ac.cr.Devweb.service.VerificationService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/verification")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")  -  se necesita una nueva dependencia para filtrar usuarios
    public ResponseEntity<?> listVerificationsPending(){
        return ResponseEntity.ok(this.verificationService.findAllRequistPending());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')") -  se necesita una nueva dependencia para filtrar usuarios
    public ResponseEntity<?> showVerification(@PathVariable Long id){
        return ResponseEntity.ok(this.verificationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveVerification(@Validated @RequestBody Verification verification, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> errors=new HashMap<>();
            for (FieldError error : result.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        VerificationDTO veriDTO=this.verificationService.saveVerification(verification);

        if (veriDTO==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("You already have a pending request to review.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(veriDTO);
    }



}
