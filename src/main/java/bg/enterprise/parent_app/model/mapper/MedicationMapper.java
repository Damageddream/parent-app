package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.MedicationDto;
import bg.enterprise.parent_app.model.entity.Medication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    MedicationDto toDTO(Medication entity);

    Medication toEntity(MedicationDto dto);
}