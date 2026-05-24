package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.enums.Category;
import ucr.ac.cr.Devweb.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
    List<Services> findAllByFreelancerId(Long id); //Buscar los servicios de un freelancer
    List<Services> findByCategory(Category category); //Filtrar por categoría

    List<Services> findAllByOrderByPriceAsc(); //Buscar todos los servicios con orden ascendente
    List<Services> findAllByOrderByPriceDesc(); //Lo mismo, pero descedente
}
