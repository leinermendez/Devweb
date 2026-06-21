package ucr.ac.cr.Devweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.Devweb.enums.ReportStatus;
import ucr.ac.cr.Devweb.enums.ReportType;
import ucr.ac.cr.Devweb.model.Report;
import ucr.ac.cr.Devweb.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByStatus(ReportStatus status);
    Optional<Report> findById(Long id);
    boolean existsByReportedByAndTypeAndReportedUserAndStatusIn(User reportedBy, ReportType type, User reportedUser, List<ReportStatus> status);
    boolean existsByReportedByAndTypeAndReportedProjectIdAndStatusIn(User reportedBy, ReportType type, Long reportedProjectId, List<ReportStatus> status);
}
