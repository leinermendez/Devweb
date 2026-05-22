package ucr.ac.cr.Devweb.repository;

import ucr.ac.cr.Devweb.enums.Category;
import ucr.ac.cr.Devweb.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findByFreelancerId(Long id);
    List<Service> findByCategory(Category category);

    List<Service> findAllByOrderByPriceAsc(); //Buscar todos los servicios con orden ascendente
    List<Service> findAllByOrderByPriceDesc(); //Lo mismo, pero descedente
}
