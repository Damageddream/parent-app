package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.InjuryDto;
import bg.enterprise.parent_app.model.entity.Injury;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InjuryMapper {

    InjuryDto toDTO(Injury entity);

    Injury toEntity(InjuryDto dto);
}