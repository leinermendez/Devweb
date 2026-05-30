package ucr.ac.cr.Devweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.enums.RequestStatus;
import ucr.ac.cr.Devweb.model.DTO.JobRequestDTO;
import ucr.ac.cr.Devweb.model.JobRequest;
import ucr.ac.cr.Devweb.repository.JobRequestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobRequestService {

    @Autowired
    private JobRequestRepository jobRequestRepository;

    public JobRequestDTO saveJobRequest(JobRequest jobRequest) {
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

    public JobRequestDTO editJobRequest(Long id, JobRequest jobRequestEdit) {
        Optional<JobRequest> requestOp = this.jobRequestRepository.findById(id);
        if (requestOp.isPresent()) {
            JobRequest request = requestOp.get();

            request.setDescription(jobRequestEdit.getDescription());
            request.setClient(jobRequestEdit.getClient());
            request.setFreelancer(jobRequestEdit.getFreelancer());
            request.setService(jobRequestEdit.getService());

            return convertJobRequestDTO(this.jobRequestRepository.save(request));
        }
        return null;
    }

    public void deleteJobRequest(Long id) {
        this.jobRequestRepository.deleteById(id);
    }


    //aceptar un trabajo
    public JobRequestDTO acceptRequest(Long id) {
        Optional<JobRequest> jobRequestOp = this.jobRequestRepository.findById(id);
        if (jobRequestOp.isPresent()) {
            JobRequest request = jobRequestOp.get();
            request.setStatus(RequestStatus.ACCEPTED);

            return convertJobRequestDTO(this.jobRequestRepository.save(request));
        }
        return null;
    }

    //rechazar un trabajo
    public JobRequestDTO rejectRequest(Long id) {
        Optional<JobRequest> jobRequestOp = this.jobRequestRepository.findById(id);
        if (jobRequestOp.isPresent()) {
            JobRequest request = jobRequestOp.get();
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
