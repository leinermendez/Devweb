package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.Devweb.enums.VerificationStatus;
import ucr.ac.cr.Devweb.model.Verification;
import ucr.ac.cr.Devweb.repository.VerificationRepository;

import java.util.List;

import static ucr.ac.cr.Devweb.enums.VerificationStatus.PENDING;//importo unicamente este estado

@Service
public class VerificationService {

    @Autowired
    private VerificationRepository verificationRepository;

    public List<Verification> findAllRequistPending(){//retorna la lista de las solicitudes que estan en estado PENDING
        return this.verificationRepository.findByStatus(PENDING);
    }
}
