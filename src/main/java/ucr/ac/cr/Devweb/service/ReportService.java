package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ucr.ac.cr.Devweb.enums.ReportStatus;
import ucr.ac.cr.Devweb.model.DTO.ReportDTO;
import ucr.ac.cr.Devweb.model.Report;
import ucr.ac.cr.Devweb.repository.ReportRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserService userService;

    public ReportDTO saveReport(Report report){
        //validamos hacia que lado esta hecho el reporte
        boolean reportUser = report.getReportedUser() != null;
        boolean reportProject = report.getReportedProjectId() != null;


        //Validamos que el reporte no reporte un/ningun usuario y proyecto al mismo tiempo
        if (reportProject==reportUser){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have to report either a user or a project, not both or neither.");
        }

        boolean reportDuplicate;
        if (reportUser){
            //validamos si se reporta a si mismo
            if (report.getReportedBy().getId().equals(report.getReportedUser().getId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hmm, you can't report yourself.");
            }
            reportDuplicate=this.reportRepository.existsByReportedByAndTypeAndReportedUserAndStatus(report.getReportedBy(),report.getType(),report.getReportedUser(),ReportStatus.PENDING);
        }else{
            reportDuplicate=this.reportRepository.existsByReportedByAndTypeAndReportedProjectIdAndStatus(report.getReportedBy(),report.getType(),report.getReportedProjectId(),ReportStatus.PENDING);
        }

        if (reportDuplicate){
            return null;
        }

        return this.converToDTO(this.reportRepository.save(report));
    }

    public List<ReportDTO> findAllReportsResolved(){        //retorna los reportes resueltos
        return converListDTO(this.reportRepository.findByStatus(ReportStatus.RESOLVED));
    }
    public List<ReportDTO> findAllReportsDismissed(){        //retorna los reportes rechazados
        return converListDTO(this.reportRepository.findByStatus(ReportStatus.DISMISSED));
    }
    public List<ReportDTO> findAllReportsPending(){         //retorna los reportes pendientes
        return converListDTO(this.reportRepository.findByStatus(ReportStatus.PENDING));
    }

    public ReportDTO changeStatus(Long id, ReportStatus status){
        Report report = this.reportRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report no found"));
        report.setStatus(status);
        return converToDTO(this.reportRepository.save(report));
    }


    //converciones a DTO
    public ReportDTO converToDTO(Report report){
        ReportDTO dto= new ReportDTO(report.getId(),
                report.getType(),
                report.getDescription(),
                this.userService.convertirUserDTO(report.getReportedBy()),
                report.getStatus(),
                report.getCreatedAt(),
                report.getReportedProjectId(),
                report.getReportedUser() != null ? this.userService.convertirUserDTO(report.getReportedUser()) : null);// un if en una sola linea de codigo "condición ? valorSiEsTrue : valorSiEsFalse"
        return dto;
    }

    public List<ReportDTO> converListDTO(List<Report> reportList){
        List<ReportDTO> dtoList = new ArrayList<>();
        for (Report report : reportList){
            dtoList.add(this.converToDTO(report));
        }
        return dtoList;
    }

}
