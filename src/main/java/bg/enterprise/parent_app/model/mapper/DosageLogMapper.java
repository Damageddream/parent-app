package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.DosageLogDto;
import bg.enterprise.parent_app.model.entity.DosageLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DosageLogMapper {

    DosageLogDto toDTO(DosageLog entity);

    DosageLog toEntity(DosageLogDto dto);
}