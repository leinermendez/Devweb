package ucr.ac.cr.Devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.Devweb.model.Report;
import ucr.ac.cr.Devweb.service.ReportService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")  -  se necesita una nueva dependencia para filtrar usuarios
    public ResponseEntity<?> listVerificationsPending(){return ResponseEntity.ok(this.reportService.findAllReportsPending());}



}
