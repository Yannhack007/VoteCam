package group.sig.projet.Services;

import group.sig.projet.Models.Resultats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketNotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Envoyer une notification en temps réel
    public void sendValidatedResultNotification(Resultats resultat) {
        messagingTemplate.convertAndSend("/topic/validated-results", resultat);
    }
}
