package com.apnishadi.match;
import com.apnishadi.auth.CurrentAccount;
import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/match-reports") public class MatchReportController { private final MatchReportService service; public MatchReportController(MatchReportService s){service=s;} @PostMapping public MatchReport createReport(@Valid @RequestBody MatchReportRequest r){CurrentAccount.requireOwnProfile(r.profileOneId()); return service.createReport(CurrentAccount.profileId(),r.profileTwoId());} }
