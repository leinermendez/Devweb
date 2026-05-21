package ucr.ac.cr.Devweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.Devweb.model.DTO.ProjectDTO;
import ucr.ac.cr.Devweb.model.DTO.UserDTO;
import ucr.ac.cr.Devweb.model.Project;
import ucr.ac.cr.Devweb.service.ProjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    //listar todos
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.projectService.getAllProjects());
    }

    //registrar
    @PostMapping
    public ResponseEntity<?> saveProject(@Validated @RequestBody Project project, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for(FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        ProjectDTO saved = this.projectService.createProject(project);
        if (saved == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El proyecto con el ID " + project.getId() + " ya se encuentra registrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //ver proyectos por id del freelancer
    @GetMapping("/freelancer/{id}")
    public ResponseEntity<List<ProjectDTO>> getByFreelancerId(@PathVariable Long id) {
        List<ProjectDTO> projects = this.projectService.getByFreelancerId(id);

        if (projects.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projects);
    }

    //ver proyectos por categoria
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProjectDTO>> getByCategory(@PathVariable String category) {
        List<ProjectDTO> projects = this.projectService.getByCategory(category);
        if (projects.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projects);
    }

    //editar
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProjectById(@RequestBody Project project, @PathVariable Long id) {
        ProjectDTO updated = this.projectService.updateProject(id, project);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }



    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectById(@PathVariable Long id) {
        this.projectService.deleteProject(id);
        return ResponseEntity.ok("Proyecto eliminado correctamente");
    }

}
