package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.NotificationDto;
import bg.enterprise.parent_app.model.dto.ParentDto;
import bg.enterprise.parent_app.model.entity.Notification;
import bg.enterprise.parent_app.model.entity.Parent;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDto toDTO(Notification entity);

    Notification toEntity(NotificationDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(NotificationDto notificationDto, @MappingTarget Notification notification);
}