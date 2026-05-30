package ucr.ac.cr.Devweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import ucr.ac.cr.Devweb.model.DTO.JobRequestDTO;
import ucr.ac.cr.Devweb.model.JobRequest;
import ucr.ac.cr.Devweb.service.JobRequestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobrequest")
public class JobRequestController {

    @Autowired
    private JobRequestService jobRequestService;

    @PostMapping("/save")
    public ResponseEntity<?> saveJobRequest(@Validated @RequestBody JobRequest jobRequest, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        JobRequestDTO dto = this.jobRequestService.saveJobRequest(jobRequest);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La solicitud de servicio con id " + jobRequest.getId() + " ya se encuentra registrada");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllJobRequests() {
        return ResponseEntity.ok(this.jobRequestService.findAllJobRequests());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByJobRequestId(@PathVariable Long id) {
        JobRequestDTO dto = this.jobRequestService.findByJobRequestId(id);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La solicitud de servicio buscada no se encontró");
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findByClient/{id}")
    public ResponseEntity<?> findAllByClientId(@PathVariable Long id) {
        List<JobRequestDTO> requestsList = this.jobRequestService.findAllByClientId(id);

        if (requestsList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente no posee solicitudes");
        }
        return ResponseEntity.ok(requestsList);
    }

    @GetMapping("/findByFreelancer/{id}")
    public ResponseEntity<?> findAllByFreelancerId(@PathVariable Long id) {
        List<JobRequestDTO> requestsList = this.jobRequestService.findAllByFreelancerId(id);

        if (requestsList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El freelancer no posee solicitudes");
        }
        return ResponseEntity.ok(requestsList);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editJobRequest(@PathVariable Long id, @Validated @RequestBody JobRequest jobRequestEdit, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        JobRequestDTO dto = this.jobRequestService.editJobRequest(id, jobRequestEdit);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La solicitud digitada esta vacia");
        }

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJobRequest(@PathVariable Long id) {
        this.jobRequestService.deleteJobRequest(id);
        return ResponseEntity.noContent().build();
    }

    // metodos put para aceptar, rechazar o marcar como completo
    @PutMapping("/accept/{id}")
    public ResponseEntity<?> acceptRequest(@PathVariable Long id) {
        JobRequestDTO dto = this.jobRequestService.acceptRequest(id);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectRequest(@PathVariable Long id) {
        JobRequestDTO dto = this.jobRequestService.rejectRequest(id);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<?> completeRequest(@PathVariable Long id) {
        JobRequestDTO dto = this.jobRequestService.completeRequest(id);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}