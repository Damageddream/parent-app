package bg.enterprise.parent_app.service.write;

import bg.enterprise.parent_app.exception.exc.EntityNotFoundException;
import bg.enterprise.parent_app.model.dto.NotificationDto;
import bg.enterprise.parent_app.model.entity.Child;
import bg.enterprise.parent_app.model.entity.Notification;
import bg.enterprise.parent_app.model.entity.Parent;
import bg.enterprise.parent_app.model.mapper.NotificationMapper;
import bg.enterprise.parent_app.repository.write.ChildRepository;
import bg.enterprise.parent_app.repository.write.NotificationRepository;
import bg.enterprise.parent_app.repository.write.ParentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;

    public NotificationDto getNotificationById(Long id) {
        log.info("Fetching notification with ID: {}", id);
        return notificationRepository.findById(id)
                .map(notificationMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id " + id));
    }

    @Transactional
    public NotificationDto createNotification(Long parentId, Long childId, NotificationDto notificationDto) {
        log.info("Creating new notification for parent ID: {} and child ID: {}", parentId, childId);

        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found with id " + parentId));

        Child child = null;
        if (childId != null) {
            child = childRepository.findById(childId)
                    .orElseThrow(() -> new EntityNotFoundException("Child not found with id " + childId));
        }

        Notification notification = notificationMapper.toEntity(notificationDto);
        notification.setParent(parent);
        notification.setChild(child);

        notification = notificationRepository.save(notification);
        return notificationMapper.toDTO(notification);
    }

    @Transactional
    public NotificationDto updateNotification(Long id, NotificationDto notificationDto) {
        log.info("Updating notification with ID: {}", id);

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id " + id));

        notificationMapper.updateEntityFromDto(notificationDto, notification);
        notification = notificationRepository.save(notification);
        return notificationMapper.toDTO(notification);
    }

    @Transactional
    public void deleteNotification(Long id) {
        log.info("Deleting notification with ID: {}", id);

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id " + id));

        notificationRepository.delete(notification);
        log.info("Successfully deleted notification with ID: {}", id);
    }

    public List<NotificationDto> getNotificationsByParentId(Long parentId) {
        log.info("Fetching notifications for parent ID: {}", parentId);

        if (!parentRepository.existsById(parentId)) {
            throw new EntityNotFoundException("Parent not found with id " + parentId);
        }

        return notificationRepository.findByParentId(parentId)
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<NotificationDto> getNotificationsByChildId(Long childId) {
        log.info("Fetching notifications for child ID: {}", childId);

        if (!childRepository.existsById(childId)) {
            throw new EntityNotFoundException("Child not found with id " + childId);
        }

        return notificationRepository.findByChildId(childId)
                .stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }
}