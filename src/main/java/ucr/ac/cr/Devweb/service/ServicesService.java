package ucr.ac.cr.Devweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.enums.Category;
import ucr.ac.cr.Devweb.model.DTO.ServicesDTO;
import ucr.ac.cr.Devweb.model.Services;
import ucr.ac.cr.Devweb.model.User;
import ucr.ac.cr.Devweb.repository.ServicesRepository;
import ucr.ac.cr.Devweb.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private UserRepository userRepository;

    public ServicesDTO saveService(Services services) {

        User freelancer = userRepository.findById(services.getFreelancer().getId()).orElseThrow(() -> new RuntimeException("Freelancer no encontrado"));

        if (!"FREELANCER".equalsIgnoreCase(freelancer.getRole().name())) {
            throw new RuntimeException("El usuario no tiene rol FREELANCER");
        }

        boolean exists = servicesRepository.existsByTitleAndFreelancerId(services.getTitle(), freelancer.getId());

        if (exists) {
            throw new RuntimeException("Ya existe un servicio con este título para este freelancer");
        }

        services.setFreelancer(freelancer);

        return this.convertServicesDTO(this.servicesRepository.save(services));
    }

    public List<ServicesDTO> findAllServices() {
        return convertListDTO(this.servicesRepository.findAll());
    }

    public List<ServicesDTO> findAllByFreelancerId(Long id) {
        List<Services> listServices = this.servicesRepository.findAllByFreelancerId(id);
        return convertListDTO(listServices);
    }

    public ServicesDTO editServices(Long id, Services servicesEdit) {
        Optional<Services> servicesOp = this.servicesRepository.findById(id);

        if (servicesOp.isPresent()) {
            Services services = servicesOp.get();

            services.setTitle(servicesEdit.getTitle());
            services.setCategory(servicesEdit.getCategory());

            User freelancer = userRepository
                    .findById(servicesEdit.getFreelancer().getId())
                    .orElseThrow(() -> new RuntimeException("Freelancer no encontrado"));

            services.setFreelancer(freelancer);

            services.setPrice(servicesEdit.getPrice());
            services.setDescription(servicesEdit.getDescription());

            return this.convertServicesDTO(this.servicesRepository.save(services));
        }

        return null;
    }


    public void deleteServices(Long id) {
        this.servicesRepository.deleteById(id);
    }

    //filtrar por categoría
    public List<ServicesDTO> findByCategory(Category category) {
        List<Services> listServices = this.servicesRepository.findByCategory(category);
        return convertListDTO(listServices);
    }


    //Buscar todos los servicios con orden de precio ascendente
    public List<ServicesDTO> findAllByOrderByPriceAsc() {
        return convertListDTO(this.servicesRepository.findAllByOrderByPriceAsc());
    }

    //Buscar todos los servicios con orden descendente
    public List<ServicesDTO> findAllByOrderByPriceDesc() {
        return convertListDTO(this.servicesRepository.findAllByOrderByPriceDesc());
    }


    //convertidores dto
    public ServicesDTO convertServicesDTO(Services services) {
        ServicesDTO dto = new ServicesDTO();

        dto.setTitle(services.getTitle());
        dto.setId(services.getId());
        dto.setCategory(services.getCategory());
        dto.setFreelancerName(services.getFreelancer().getName());
        dto.setFreelancerId(services.getFreelancer().getId());
        dto.setPrice(services.getPrice());
        dto.setImageUrl(services.getImageUrl());
        dto.setDescription(services.getDescription());


        return dto;
    }


    public List<ServicesDTO> convertListDTO(List<Services> listServices) {
        List<ServicesDTO> listDTO = new ArrayList<>();
        for (Services services : listServices) {
            listDTO.add(this.convertServicesDTO(services));
        }
        return listDTO;
    }
}
