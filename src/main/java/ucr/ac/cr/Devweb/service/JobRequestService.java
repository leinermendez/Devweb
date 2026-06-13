package ucr.ac.cr.Devweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.enums.RequestStatus;
import ucr.ac.cr.Devweb.model.DTO.JobRequestDTO;
import ucr.ac.cr.Devweb.model.JobRequest;
import ucr.ac.cr.Devweb.model.Services;
import ucr.ac.cr.Devweb.repository.JobRequestRepository;
import ucr.ac.cr.Devweb.repository.ServicesRepository;
import ucr.ac.cr.Devweb.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobRequestService {

    @Autowired
    private JobRequestRepository jobRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    public JobRequestDTO saveJobRequest(JobRequest jobRequest) {

        if (jobRequest.getClient() == null || jobRequest.getFreelancer() == null || jobRequest.getService() == null) {
            throw new RuntimeException("Cliente, freelancer y servicio son obligatorios");
        }

        if(!userRepository.existsById(jobRequest.getClient().getId())){
            throw new RuntimeException("El cliente no existe");
        }

        if(!userRepository.existsById(jobRequest.getFreelancer().getId())){
            throw new RuntimeException("El freelancer no existe");
        }

        if(jobRequest.getClient().getId().equals(jobRequest.getFreelancer().getId())){
            throw new RuntimeException("El cliente no puede ser el mismo que el freelancer");
        }

        Services service = servicesRepository.findById(jobRequest.getService().getId()).orElse(null);

        if (service == null ){
            throw new RuntimeException("El servicio no existe");
        }

        if (!service.getFreelancer().getId().equals(jobRequest.getFreelancer().getId())) {
            throw new RuntimeException("El freelancer no ofrecer el servicio solicitado");
        }

        boolean duplicated = jobRequestRepository.existsByClientIdAndFreelancerIdAndServicesIdAndStatus(jobRequest.getClient().getId(), jobRequest.getFreelancer().getId(), jobRequest.getService().getId(), RequestStatus.PENDING);

        if(duplicated){
            throw new RuntimeException("Ya existe una solicitud pendiente para este servic");
        }

        return this.convertJobRequestDTO(this.jobRequestRepository.save(jobRequest));
    }

    public List<JobRequestDTO> findAllJobRequests() {
        return convertListDTO(this.jobRequestRepository.findAll());
    }

    public JobRequestDTO findByJobRequestId(Long id) {
        Optional<JobRequest> jobRequest = this.jobRequestRepository.findById(id);
        if (jobRequest.isPresent()) {
            return convertJobRequestDTO(jobRequest.get());
        }
        return null;
    }

    public List<JobRequestDTO> findAllByClientId(Long clientId) {
        List<JobRequest> requests = this.jobRequestRepository.findAllByClientId(clientId);
        return convertListDTO(requests);
    }


    public List<JobRequestDTO> findAllByFreelancerId(Long freelancerId) {
        List<JobRequest> requests = this.jobRequestRepository.findAllByFreelancerId(freelancerId);
        return convertListDTO(requests);
    }

    //por si el cliente desea actualizar la descripción de su solicitud
    public JobRequestDTO editJobRequest(Long id, JobRequest jobRequestEdit) {
        Optional<JobRequest> requestOp = this.jobRequestRepository.findById(id);
        if (requestOp.isPresent()) {
            JobRequest jobRequest = requestOp.get();

            jobRequest.setDescription(jobRequestEdit.getDescription());

            return convertJobRequestDTO(this.jobRequestRepository.save(jobRequest));
        }
        return null;
    }

    public void deleteJobRequest(Long id) {
        this.jobRequestRepository.deleteById(id);
    }


    //aceptar un trabajo
    public JobRequestDTO acceptRequest(Long id) {
        Optional<JobRequest> jobRequestOp = this.jobRequestRepository.findById(id);
        if (jobRequestOp.isPresent()) { // el servicio existe en la bd
            JobRequest request = jobRequestOp.get();

            // Solo se puede aceptar/rechazar si está en estado PENDING
            if(request.getStatus() != RequestStatus.PENDING){
                return null;
            }
            request.setStatus(RequestStatus.ACCEPTED);
            return convertJobRequestDTO(this.jobRequestRepository.save(request));
        }
        return null;
    }

    //rechazar un trabajo
    public JobRequestDTO rejectRequest(Long id) {
        Optional<JobRequest> jobRequestOp = this.jobRequestRepository.findById(id);
        if (jobRequestOp.isPresent()) {
            JobRequest request = jobRequestOp.get(); // el servicio existe en la bd

            // Solo se puede aceptar/rechazar si está en estado PENDING
            if(request.getStatus() != RequestStatus.PENDING){
                return null;
            }
            request.setStatus(RequestStatus.REJECTED);
            return convertJobRequestDTO(this.jobRequestRepository.save(request));
        }
        return null;
    }


    //completar un trabajo
    public JobRequestDTO completeRequest(Long id) {
        Optional<JobRequest> jobRequestOp = this.jobRequestRepository.findById(id);
        if (jobRequestOp.isPresent()) {
            JobRequest request = jobRequestOp.get();

            //Solo se puede completar si está en estado ACCEPTED
            if(request.getStatus() != RequestStatus.ACCEPTED){
                return null;
            }
            request.setStatus(RequestStatus.COMPLETED);

            return convertJobRequestDTO(this.jobRequestRepository.save(request));
        }
        return null;
    }


    // Convertidores DTO
    public JobRequestDTO convertJobRequestDTO(JobRequest jobRequest) {
        JobRequestDTO dto = new JobRequestDTO();

        dto.setId(jobRequest.getId());
        dto.setDescription(jobRequest.getDescription());
        dto.setStatus(jobRequest.getStatus());
        dto.setClientId(jobRequest.getClient().getId());
        dto.setFreelancerId(jobRequest.getFreelancer().getId());
        dto.setServiceId(jobRequest.getService().getId());

        return dto;
    }

    public List<JobRequestDTO> convertListDTO(List<JobRequest> requests) {
        List<JobRequestDTO> listDTO = new ArrayList<>();
        for (JobRequest request : requests) {
            listDTO.add(this.convertJobRequestDTO(request));
        }
        return listDTO;
    }

}
