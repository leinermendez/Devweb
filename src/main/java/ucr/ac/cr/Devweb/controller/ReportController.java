package ucr.ac.cr.Devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.Devweb.enums.ReportStatus;
import ucr.ac.cr.Devweb.model.DTO.ReportDTO;
import ucr.ac.cr.Devweb.model.Report;
import ucr.ac.cr.Devweb.service.ReportService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")  -  se necesita una nueva dependencia para filtrar usuarios
    public ResponseEntity<?> listVerificationsPending(){return ResponseEntity.ok(this.reportService.findAllReportsPending());}

    @PostMapping
    public ResponseEntity<?> saveReports(@Validated @RequestBody Report report, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> errors=new HashMap<>();
            for (FieldError error: result.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        ReportDTO repoDTO=this.reportService.saveReport(report);
        if (repoDTO==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("You already have a pending or under review report.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repoDTO);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<?> changeStatusResolved(@PathVariable long id){
        return ResponseEntity.ok(this.reportService.changeStatus(id, ReportStatus.RESOLVED));
    }
    @PutMapping("/{id}/dismiss")
    public ResponseEntity<?> changeStatusDismiss(@PathVariable long id){
        return ResponseEntity.ok(this.reportService.changeStatus(id, ReportStatus.DISMISSED));
    }

    // endPoints depuracion

    @GetMapping("/Under")           // filtra los reportes en revicion
    public ResponseEntity<?> listVerificationsUnder(){return ResponseEntity.ok(this.reportService.findAllReportsUnder_Review());}
    @GetMapping("/Dismissed")        //filtra los reportes rechazados
    public ResponseEntity<?> listVerificationsRejected(){return ResponseEntity.ok(this.reportService.findAllReportsDismissed());}
    @GetMapping("/Resolved")        // filtra los reportes resueltos
    public ResponseEntity<?> listVerificationsResolved(){return ResponseEntity.ok(this.reportService.findAllReportsResolved());}
}
