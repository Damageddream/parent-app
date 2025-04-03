package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.ParentDto;
import bg.enterprise.parent_app.model.dto.PrescriptionDto;
import bg.enterprise.parent_app.model.entity.Parent;
import bg.enterprise.parent_app.model.entity.Prescription;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {

    @Mapping(source = "child.id", target = "childId")
    @Mapping(source = "medication.id", target = "medicationId")
    PrescriptionDto toDTO(Prescription entity);

    @Mapping(source = "childId", target = "child.id")
    @Mapping(source = "medicationId", target = "medication.id")
    Prescription toEntity(PrescriptionDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "childId", target = "child.id")
    @Mapping(source = "medicationId", target = "medication.id")
    void updateEntityFromDto(PrescriptionDto dto, @MappingTarget Prescription entity);
}