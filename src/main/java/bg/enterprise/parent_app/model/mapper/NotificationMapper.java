package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.NotificationDto;
import bg.enterprise.parent_app.model.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDto toDTO(Notification entity);

    Notification toEntity(NotificationDto dto);
}