package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.enums.Role;
import ucr.ac.cr.Devweb.model.DTO.ProjectDTO;
import ucr.ac.cr.Devweb.model.Project;
import ucr.ac.cr.Devweb.model.User;
import ucr.ac.cr.Devweb.repository.ProjectRepository;
import ucr.ac.cr.Devweb.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    //OBTENER TODOS LOS PROYECTOS
    public List<ProjectDTO> getAllProjects() {
        return convertList(projectRepository.findAll());
    }


    //CREAR UN NUEVO PROYECTO
    public ProjectDTO createProject(Project project) {
        User freelancer = userRepository.findById(project.getFreelancer().getId()).orElseThrow();
        project.setFreelancer(freelancer);

        if (freelancer.getRole() != Role.FREELANCER) {
            throw new RuntimeException("El usuario no tiene rol de freelancer");
        }

        if (projectRepository.existsByTitleAndFreelancerId(project.getTitle(), freelancer.getId())) {
            throw new RuntimeException("Ya existe un proyecto con ese título para este freelancer");
        }

        Project saved = projectRepository.save(project);
        Project full = projectRepository.findById(saved.getId()).orElseThrow();
        return convertirProjectDTO(full);
    }

    //OBETENER TODOS LOS PROYECTOS DE UN FREELANCER
    public List<ProjectDTO> getByFreelancerId(Long id){
        List<Project> projects = projectRepository.findByFreelancerId(id);
        return convertList(projects);
    }

    //OBETENER TODOS LOS PROYECTOS POR CATEGORIA
    public List<ProjectDTO> getByCategory(String category){
        List<Project> projects = projectRepository.findByCategory(category);
        return convertList(projects);
    }


    //ACTUALIZAR Proyecto
    public ProjectDTO updateProject(Long id, Project project) {
        Optional<Project> optional = this.projectRepository.findById(id);
        if(optional.isPresent()){
            Project existing = optional.get();
            existing.setTitle(project.getTitle());
            existing.setDescription(project.getDescription());
            existing.setCategory(project.getCategory());
            existing.setImageUrl(project.getImageUrl());

            // Cargar el usuario completo
            User freelancer = userRepository.findById(project.getFreelancer().getId()).orElseThrow();
            existing.setFreelancer(freelancer);
            return convertirProjectDTO(projectRepository.save(existing));
        }
        throw new RuntimeException("Proyecto no encontrado con id: " + id);
    }

    //ELIMINAR PROYECTO
    public void deleteProject(Long id) {
        if(!projectRepository.existsById(id)){
            throw new RuntimeException("Proyecto no encontrado con id: " + id); //Tira error si el id del Proyecto no se encuentra
        }
        projectRepository.deleteById(id);
    }

    public ProjectDTO convertirProjectDTO(Project project){
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setFreelancerName(project.getFreelancer().getName());
        dto.setImageUrl(project.getImageUrl());
        return dto;
    }

    public List<ProjectDTO> convertList(List<Project> listProject){
        List<ProjectDTO> listDTO = new ArrayList<>();
        for (Project project: listProject){
            listDTO.add(this.convertirProjectDTO(project));
        }
        return listDTO;
    }

}
