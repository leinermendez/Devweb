package ucr.ac.cr.Devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ucr.ac.cr.Devweb.service.VerificationService;

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



}
