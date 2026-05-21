package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.model.DTO.ProjectDTO;
import ucr.ac.cr.Devweb.model.Project;
import ucr.ac.cr.Devweb.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    //OBTENER TODOS LOS PROYECTOS
    public List<ProjectDTO> getAllProjects() {
        return convertList(projectRepository.findAll());
    }


    //CREAR UN NUEVO PROYECTO
    public ProjectDTO createProject(Project project) {
        Project saved = projectRepository.save(project);
        return convertirProjectDTO(saved);
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
            existing.setImageUrl(project.getTitle());
            existing.setDescription(project.getDescription());
            existing.setCategory(project.getCategory());
            existing.setImageUrl(project.getImageUrl());
            existing.setFreelancer(project.getFreelancer());
            return convertirProjectDTO(projectRepository.save(existing));
        }
        throw new RuntimeException("Proyecto no encontrado con id: " + id); //Tira error si el id del proyecto no se encuentra
    }

    //ELIMINAR PROYECTO
    public void deleteProject(Long id) {
        if(!projectRepository.existsById(id)){
            throw new RuntimeException("Proyecto no encontrado con id: " + id); //Tira error si el id del Proyecto no se encuentra
        }
        projectRepository.deleteById(id);
    }

    public ProjectDTO convertirProjectDTO(Project project){
        ProjectDTO dto= new ProjectDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setFreelancerName(project.getFreelancer().getName());
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
