package ucr.ac.cr.Devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.Devweb.enums.Category;
import ucr.ac.cr.Devweb.model.DTO.ServicesDTO;
import ucr.ac.cr.Devweb.model.Services;
import ucr.ac.cr.Devweb.service.ServicesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServicesService servicesService;

    @PostMapping("/save")
    public ResponseEntity<?> saveService(@Validated @RequestBody Services services, BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for(FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        ServicesDTO dto=this.servicesService.saveService(services);

        if(dto == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El servicio con id " + services.getId() + " ya se encuentra registrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllServices() {
        return ResponseEntity.ok(this.servicesService.findAllServices());
    }

    @GetMapping("/findByFreelancer/{id}")
    public ResponseEntity<?> findAllByFreelancerId(@PathVariable Long id) {
        List<ServicesDTO> servicesList = this.servicesService.findAllByFreelancerId(id);

        if (servicesList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario digitado no posee servicios disponibles");
        }
        return ResponseEntity.ok(servicesList);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editServices(@PathVariable Long id, @Validated @RequestBody Services servicesEdit, BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for(FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        ServicesDTO dto=this.servicesService.editServices(id, servicesEdit);

        if(dto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El servicio digitado esta vacio");
        }

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteServices(@PathVariable Long id) {
        this.servicesService.deleteServices(id);
        return ResponseEntity.noContent().build();
    }

    //filtrar por categoría
    @GetMapping("/findByCategory/{category}")
    public ResponseEntity<?> findByCategory(@PathVariable Category category) {
        List<ServicesDTO> servicesList = this.servicesService.findByCategory(category);

        if (servicesList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay servicios disponibles con la categoría digitada");
        }
        return ResponseEntity.ok(servicesList);
    }


    //Buscar todos los servicios con orden de precio ascendente
    @GetMapping("/sortPrice/Asc")
    public ResponseEntity<?> findAllByOrderByPriceAsc() {
        return ResponseEntity.ok(this.servicesService.findAllByOrderByPriceAsc());
    }

    @GetMapping("/sortPrice/Desc")
    //Buscar todos los servicios con orden descendente
    public ResponseEntity<?> findAllByOrderByPriceDesc() {
        return ResponseEntity.ok(this.servicesService.findAllByOrderByPriceDesc());
    }

}
