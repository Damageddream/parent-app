package bg.enterprise.parent_app.model;

import bg.enterprise.parent_app.model.dto.ChildDto;
import bg.enterprise.parent_app.model.dto.IllnessDto;
import bg.enterprise.parent_app.model.dto.NotificationDto;
import bg.enterprise.parent_app.model.dto.PrescriptionDto;
import bg.enterprise.parent_app.model.type.IllnessType;

import java.time.LocalDate;
import java.util.List;

public class CreateDtoFactory {

    public static IllnessDto createIllnessDto(Long id, Long childId, String name, IllnessType type, String description) {
        return IllnessDto.builder()
                .id(id)
                .childId(childId)
                .name(name)
                .illnessType(type)
                .description(description)
                .build();
    }

    public static ChildDto createChildDto(Long parentId, String firstName, String lastName,
                                          LocalDate birthday, String notes, List<PrescriptionDto> prescriptionDtoList,
                                          List<NotificationDto> notificationDtoList) {
        return ChildDto.builder()
                .parentId(parentId)
                .firstName(firstName)
                .lastName(lastName)
                .birthdate(birthday)
                .notes(notes)
                .prescriptions(prescriptionDtoList)
                .notifications(notificationDtoList)
                .build();
    }

}
