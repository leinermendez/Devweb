package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.Devweb.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByFreelancerId(Long id); //busca proyectos por Id de Freelancer

    List<Project> findByCategory(String category);//Busca proyectos por categoria

}

