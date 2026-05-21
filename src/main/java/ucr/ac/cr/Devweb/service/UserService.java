package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.model.DTO.UserDTO;
import ucr.ac.cr.Devweb.model.User;
import ucr.ac.cr.Devweb.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //OBTENER TODOS LOS USUARIOS
    public List<UserDTO> getAllUsers() {
        return convertList(userRepository.findAll());
    }


    //CREAR UN NUEVO USUARIO
    public UserDTO createUser(User user) {
        User saved = userRepository.save(user);
        return convertirUserDTO(saved);
    }

    //ENCONTRAR USUARIOS POR ID
    public UserDTO findByIdUser(Long id){
        Optional<User> optional=this.userRepository.findById(id);
        if(optional.isPresent()){
            return this.convertirUserDTO(optional.get());
        }

        throw new RuntimeException("Usuario no encontrado con id: " + id); //Tira error si el id del usuario no se encuentra
    }

    //ACTUALIZAR USUARIO
    public UserDTO updateUser(Long id, User user) {
        Optional<User> optional = this.userRepository.findById(id);
        if(optional.isPresent()){
            User existing = optional.get();
            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            existing.setRole(user.getRole());
            existing.setVerified(user.getVerified());
            existing.setRating(user.getRating());
            return convertirUserDTO(userRepository.save(existing));
        }
        throw new RuntimeException("Usuario no encontrado con id: " + id); //Tira error si el id del usuario no se encuentra
    }

    //ELIMINAR USUARIO
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            throw new RuntimeException("Usuario no encontrado con id: " + id); //Tira error si el id del usuario no se encuentra
        }
        userRepository.deleteById(id);
    }

    public UserDTO convertirUserDTO(User user){
        UserDTO dto= new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        dto.setVerified(user.getVerified());
        dto.setRating(user.getRating());
        return dto;
    }

    public List<UserDTO> convertList(List<User> listUser){
        List<UserDTO> listDTO = new ArrayList<>();
        for (User user: listUser){
            listDTO.add(this.convertirUserDTO(user));
        }
        return listDTO;
    }



}
