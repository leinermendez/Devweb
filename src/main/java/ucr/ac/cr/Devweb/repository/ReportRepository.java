package ucr.ac.cr.Devweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucr.ac.cr.Devweb.enums.ReportStatus;
import ucr.ac.cr.Devweb.enums.ReportTargetType;
import ucr.ac.cr.Devweb.enums.ReportType;
import ucr.ac.cr.Devweb.model.Report;
import ucr.ac.cr.Devweb.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByStatus(ReportStatus status);
    Optional<Report> findById(Long id);
    boolean existsByClientAndTypeAndContentIdAndTargetTypeAndStatusIn(User client, ReportType type, Long contentId, ReportTargetType targetType, List<ReportStatus> status);
}
