package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.ParentDto;
import bg.enterprise.parent_app.model.dto.PrescriptionDto;
import bg.enterprise.parent_app.model.entity.Parent;
import bg.enterprise.parent_app.model.entity.Prescription;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {

    PrescriptionDto toDTO(Prescription entity);

    Prescription toEntity(PrescriptionDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PrescriptionDto parentDto, @MappingTarget Prescription parent);
}