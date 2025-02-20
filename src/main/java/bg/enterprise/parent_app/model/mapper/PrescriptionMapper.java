package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.PrescriptionDto;
import bg.enterprise.parent_app.model.entity.Prescription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {

    PrescriptionDto toDTO(Prescription entity);

    Prescription toEntity(PrescriptionDto dto);
}