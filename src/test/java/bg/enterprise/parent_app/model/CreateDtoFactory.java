package bg.enterprise.parent_app.model;

import bg.enterprise.parent_app.model.dto.*;
import bg.enterprise.parent_app.model.type.IllnessType;
import bg.enterprise.parent_app.model.type.InjuryType;

import java.time.Instant;
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

    public static ParentDto createParentDto(String firstName, String lastName, String email, String password, String phone,
                                            List<ChildDto> children, List<DosageLogDto> dosageLogs, List<NotificationDto> notifications) {
        return ParentDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .children(children)
                .dosageLogs(dosageLogs)
                .notifications(notifications)
                .build();
    }

    public static PrescriptionDto createPrescriptionDto(Long childId, Long medicationId, Float dosageAmount,
                                                        String dosageUnit, Integer frequencyPerDay, LocalDate startDate,
                                                        LocalDate endDate, String notes, List<DosageLogDto> dosageLogDtoList) {
        return PrescriptionDto.builder()
                .childId(childId)
                .medicationId(medicationId)
                .dosageAmount(dosageAmount)
                .dosageUnit(dosageUnit)
                .frequencyPerDay(frequencyPerDay)
                .startDate(startDate)
                .endDate(endDate)
                .notes(notes)
                .dosageLogs(dosageLogDtoList)
                .build();
    }

    public static DosageLogDto createDosageLofDto(Long dosageGiven, Instant administeredAt, String notes) {
        return DosageLogDto.builder()
                .dosageGiven(dosageGiven)
                .administeredAt(administeredAt)
                .notes(notes)
                .build();
    }

    public static InjuryDto createInjuryDto(String name, Long childId, InjuryType injuryType, String description) {
        return InjuryDto.builder()
                .name(name)
                .childId(childId)
                .injuryType(injuryType)
                .description(description)
                .build();
    }

    public static MedicationDto createMedicationDto(String name, String brand, String description, String dosageForm, LocalDate openDate, String instruction) {
        return MedicationDto.builder()
                .name(name)
                .brand(brand)
                .description(description)
                .dosageForm(dosageForm)
                .openDate(openDate)
                .instructions(instruction)
                .build();
    }

    public static NotificationDto createNotificationDto(Long parentId, Long childId, String message, boolean isRead) {
        return NotificationDto.builder()
                .parentId(parentId)
                .childId(childId)
                .message(message)
                .isRead(isRead)
                .build();
    }


    public static ChronicConditionDto createChronicConditionDto(String name, String description, String instruction, String notes) {
        return ChronicConditionDto.builder()
                .name(name)
                .description(description)
                .instructions(instruction)
                .notes(notes)
                .build();
    }
}
