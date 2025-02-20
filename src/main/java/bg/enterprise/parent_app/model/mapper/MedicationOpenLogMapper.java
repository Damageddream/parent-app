package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.MedicationOpenLogDto;
import bg.enterprise.parent_app.model.entity.MedicationOpenLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicationOpenLogMapper {

    MedicationOpenLogDto toDTO(MedicationOpenLog entity);

    MedicationOpenLog toEntity(MedicationOpenLogDto dto);
}