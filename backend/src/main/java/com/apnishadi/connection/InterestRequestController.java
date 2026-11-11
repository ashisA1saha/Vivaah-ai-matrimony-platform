package com.apnishadi.connection;
import com.apnishadi.auth.CurrentAccount; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/interest-requests") public class InterestRequestController { private final ConnectionService service; public InterestRequestController(ConnectionService s){service=s;}
@GetMapping public List<InterestRequestResponse> getRequests(@RequestParam Long profileId,@RequestParam(defaultValue="incoming") String box){CurrentAccount.requireOwnProfile(profileId);return service.getRequests(CurrentAccount.profileId(),box);}
@PostMapping public InterestRequestResponse sendRequest(@Valid @RequestBody CreateInterestRequest r){return service.sendRequest(new CreateInterestRequest(CurrentAccount.profileId(),r.receiverProfileId()));}
@PostMapping("/{requestId}/accept") public ConversationResponse accept(@PathVariable Long requestId,@RequestBody(required=false) RespondToInterestRequest ignored){return service.acceptRequest(requestId,CurrentAccount.profileId());}
@PostMapping("/{requestId}/decline") public InterestRequestResponse decline(@PathVariable Long requestId,@RequestBody(required=false) RespondToInterestRequest ignored){return service.declineRequest(requestId,CurrentAccount.profileId());}
}
