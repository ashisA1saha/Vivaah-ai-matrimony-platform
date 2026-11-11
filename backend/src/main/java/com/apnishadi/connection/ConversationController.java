package com.apnishadi.connection;
import com.apnishadi.auth.CurrentAccount; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/conversations") public class ConversationController { private final ConnectionService service; public ConversationController(ConnectionService s){service=s;}
@GetMapping public List<ConversationResponse> getConversations(@RequestParam Long profileId){CurrentAccount.requireOwnProfile(profileId);return service.getConversations(CurrentAccount.profileId());}
@GetMapping("/{conversationId}/messages") public List<ChatMessageResponse> getMessages(@PathVariable Long conversationId,@RequestParam Long profileId){CurrentAccount.requireOwnProfile(profileId);return service.getMessages(conversationId,CurrentAccount.profileId());}
@PostMapping("/{conversationId}/messages") public ChatMessageResponse sendMessage(@PathVariable Long conversationId,@Valid @RequestBody SendChatMessageRequest r){return service.sendMessage(conversationId,new SendChatMessageRequest(CurrentAccount.profileId(),r.message()));}
}
