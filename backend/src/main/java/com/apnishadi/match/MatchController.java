package com.apnishadi.match;
import com.apnishadi.auth.CurrentAccount;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/matches") public class MatchController { private final MatchReportService service; public MatchController(MatchReportService s){service=s;} @GetMapping public List<MatchCandidate> getMatches(@RequestParam Long profileId){CurrentAccount.requireOwnProfile(profileId); return service.getMatchesForProfile(CurrentAccount.profileId());} }
