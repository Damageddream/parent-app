package bg.enterprise.parent_app.service.write;

import bg.enterprise.parent_app.exception.exc.EntityNotFoundException;
import bg.enterprise.parent_app.model.dto.PrescriptionDto;
import bg.enterprise.parent_app.model.entity.Child;
import bg.enterprise.parent_app.model.entity.Medication;
import bg.enterprise.parent_app.model.entity.Prescription;
import bg.enterprise.parent_app.model.mapper.PrescriptionMapper;
import bg.enterprise.parent_app.repository.write.ChildRepository;
import bg.enterprise.parent_app.repository.write.MedicationRepository;
import bg.enterprise.parent_app.repository.write.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final ChildRepository childRepository;
    private final MedicationRepository medicationRepository;

    public PrescriptionDto getPrescriptionById(Long id) {
        log.info("Fetching prescription with ID: {}", id);
        return prescriptionRepository.findById(id)
                .map(prescriptionMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Prescription not found with id " + id));
    }

    @Transactional
    public PrescriptionDto createPrescription(Long childId, PrescriptionDto prescriptionDto) {
        log.info("Creating new prescription for child ID: {}", childId);

        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found with id " + childId));

        Medication medication = medicationRepository.findById(prescriptionDto.getMedicationId())
                .orElseThrow(() -> new EntityNotFoundException("Medication not found with id " + prescriptionDto.getMedicationId()));

        Prescription prescription = prescriptionMapper.toEntity(prescriptionDto);
        prescription.setChild(child);
        prescription.setMedication(medication);

        prescription = prescriptionRepository.save(prescription);
        return prescriptionMapper.toDTO(prescription);
    }

    @Transactional
    public PrescriptionDto updatePrescription(Long id, PrescriptionDto prescriptionDto) {
        log.info("Updating prescription with ID: {}", id);

        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prescription not found with id " + id));

        if (prescriptionDto.getMedicationId() != null) {
            Medication medication = medicationRepository.findById(prescriptionDto.getMedicationId())
                    .orElseThrow(() -> new EntityNotFoundException("Medication not found with id " + prescriptionDto.getMedicationId()));
            prescription.setMedication(medication);
        }

        prescriptionMapper.updateEntityFromDto(prescriptionDto, prescription);
        prescription = prescriptionRepository.save(prescription);
        return prescriptionMapper.toDTO(prescription);
    }

    @Transactional
    public void deletePrescription(Long id) {
        log.info("Deleting prescription with ID: {}", id);

        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prescription not found with id " + id));

        prescriptionRepository.delete(prescription);
        log.info("Successfully deleted prescription with ID: {}", id);
    }

    public List<PrescriptionDto> getPrescriptionsByChildId(Long childId) {
        log.info("Fetching prescriptions for child ID: {}", childId);

        if (!childRepository.existsById(childId)) {
            throw new EntityNotFoundException("Child not found with id " + childId);
        }

        return prescriptionRepository.findByChildId(childId)
                .stream()
                .map(prescriptionMapper::toDTO)
                .collect(Collectors.toList());
    }
}