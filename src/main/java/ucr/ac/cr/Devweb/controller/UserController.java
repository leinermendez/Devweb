package ucr.ac.cr.Devweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.Devweb.model.DTO.UserDTO;
import ucr.ac.cr.Devweb.model.User;
import ucr.ac.cr.Devweb.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //listar todos
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //registrar
    @PostMapping
    public ResponseEntity<?> saveUser(@Validated @RequestBody User user, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for(FieldError error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        UserDTO saved = this.userService.createUser(user);
        if (saved == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario con el ID " + user.getId() + " ya se encuentra registrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //ver un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        UserDTO user = this.userService.findByIdUser(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    //editar
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@RequestBody User user, @PathVariable Long id) {
        UserDTO updated = this.userService.updateUser(id, user);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }






}
