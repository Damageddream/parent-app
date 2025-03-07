package bg.enterprise.parent_app.service.write;

import bg.enterprise.parent_app.exception.exc.EntityNotFoundException;
import bg.enterprise.parent_app.model.dto.MedicationDto;
import bg.enterprise.parent_app.model.entity.Medication;
import bg.enterprise.parent_app.model.mapper.MedicationMapper;
import bg.enterprise.parent_app.repository.write.MedicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    public MedicationDto getMedicationById(Long id) {
        log.info("Fetching medication with ID: {}", id);
        return medicationRepository.findById(id)
                .map(medicationMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Medication not found with id " + id));
    }

    @Transactional
    public MedicationDto createMedication(MedicationDto medicationDto) {
        log.info("Creating new medication: {}", medicationDto.getName());

        Medication medication = medicationMapper.toEntity(medicationDto);
        medication = medicationRepository.save(medication);

        return medicationMapper.toDTO(medication);
    }

    @Transactional
    public MedicationDto updateMedication(Long id, MedicationDto medicationDto) {
        log.info("Updating medication with ID: {}", id);

        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medication not found with id " + id));

        medicationMapper.updateEntityFromDto(medicationDto, medication);
        medication = medicationRepository.save(medication);

        return medicationMapper.toDTO(medication);
    }

    @Transactional
    public void deleteMedication(Long id) {
        log.info("Deleting medication with ID: {}", id);

        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medication not found with id " + id));

        medicationRepository.delete(medication);
        log.info("Successfully deleted medication with ID: {}", id);
    }

    public List<MedicationDto> getAllMedications() {
        log.info("Fetching all medications");

        return medicationRepository.findAll()
                .stream()
                .map(medicationMapper::toDTO)
                .collect(Collectors.toList());
    }
}