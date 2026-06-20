package ucr.ac.cr.Devweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    //conprobacion si el reporte ha sido hecho anteriormente por el mismo cliente, hacia el mismo contenido
    // hacia el mismo tipo de contenido y que el reporte todavia este pendiente o en revicio

    Boolean reportV= this.reportRepository.existsByClientAndTypeAndContentIdAndTargetTypeAndStatusIn(report.getClient(),report.getType(),report.getContentId(),report.getTargetType(),List.of(ReportStatus.PENDING,ReportStatus.UNDER_REVIEW));

    if (reportV){
        return null;
    }
    return this.converToDTO(this.reportRepository.save(report));
    }

    public List<ReportDTO> findAllReportsUnder_Review(){    //retorna los reportes en revicion
        return converListDTO(this.reportRepository.findByStatus(ReportStatus.UNDER_REVIEW));
    }
    public List<ReportDTO> findAllReportsResolved(){        //retorna los reportes resueltos
        return converListDTO(this.reportRepository.findByStatus(ReportStatus.RESOLVED));
    }
    public List<ReportDTO> findAllReportsRejected(){        //retorna los reportes rechazados
        return converListDTO(this.reportRepository.findByStatus(ReportStatus.REJECTED));
    }
    public List<ReportDTO> findAllReportsPending(){         //retorna los reportes pendientes
        return converListDTO(this.reportRepository.findByStatus(ReportStatus.PENDING));
    }



    //converciones a DTO
    public ReportDTO converToDTO(Report report){
        return new ReportDTO(   report.getId(),
                report.getDescription(),
                userService.convertirUserDTO(report.getClient()),
                report.getStatus(),
                report.getDate(),
                report.getContentId(),
                report.getTargetType());
    }

    public List<ReportDTO> converListDTO(List<Report> reportList){
        List<ReportDTO> dtoList = new ArrayList<>();
        for (Report report : reportList){
            dtoList.add(this.converToDTO(report));
        }
        return dtoList;
    }

}
