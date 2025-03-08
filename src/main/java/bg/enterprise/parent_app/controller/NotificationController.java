package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.NotificationDto;
import bg.enterprise.parent_app.service.write.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;


    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotificationById(@PathVariable Long id) {
        log.info("Received request to fetch notification: [id={}]", id);
        NotificationDto notificationDto = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notificationDto);
    }


    @PostMapping("/{parentId}/{childId}")
    public ResponseEntity<NotificationDto> createNotification(@PathVariable Long parentId,
                                                              @PathVariable(required = false) Long childId,
                                                              @RequestBody NotificationDto notificationDto) {
        log.info("Received request to create a new notification for parent: [id={}] and child: [id={}]", parentId, childId);
        NotificationDto createdNotification = notificationService.createNotification(parentId, childId, notificationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotification);
    }


    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> updateNotification(@PathVariable Long id, @RequestBody NotificationDto notificationDto) {
        log.info("Received request to update notification: [id={}]", id);
        NotificationDto updatedNotification = notificationService.updateNotification(id, notificationDto);
        return ResponseEntity.ok(updatedNotification);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        log.info("Received request to delete notification: [id={}]", id);
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
