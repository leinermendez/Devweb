package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.enums.VerificationStatus;
import ucr.ac.cr.Devweb.model.DTO.ServicesDTO;
import ucr.ac.cr.Devweb.model.DTO.VerificationDTO;
import ucr.ac.cr.Devweb.model.Services;
import ucr.ac.cr.Devweb.model.Verification;
import ucr.ac.cr.Devweb.repository.VerificationRepository;

import java.util.ArrayList;
import java.util.List;

import static ucr.ac.cr.Devweb.enums.VerificationStatus.PENDING;//importo unicamente este estado

@Service
public class VerificationService {

    @Autowired
    private VerificationRepository verificationRepository;

    public List<VerificationDTO> findAllRequistPending(){//retorna la lista de las solicitudes que estan en estado PENDING
        return convertListDTO(this.verificationRepository.findByStatus(PENDING));
    }

    public VerificationDTO converterToDTO(Verification verification) {
        return new VerificationDTO(
                verification.getId(),
                verification.getEvidenceUrl(),
                verification.getStatus(),
                verification.getRequestDate(),
                verification.getUser().getName()
        );
    }

        public List<VerificationDTO> convertListDTO(List<Verification> listVerification) {
            List<VerificationDTO> listDTO = new ArrayList<>();
            for (Verification verification : listVerification) {
                listDTO.add(this.converterToDTO(verification));
            }
            return listDTO;
        }
}

