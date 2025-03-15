package bg.enterprise.parent_app.service.write;

import bg.enterprise.parent_app.exception.exc.EntityNotFoundException;
import bg.enterprise.parent_app.model.dto.DosageLogDto;
import bg.enterprise.parent_app.model.entity.DosageLog;
import bg.enterprise.parent_app.model.entity.Prescription;
import bg.enterprise.parent_app.model.mapper.DosageLogMapper;
import bg.enterprise.parent_app.repository.write.DosageLogRepository;
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
public class DosageLogService {

    private final DosageLogRepository dosageLogRepository;
    private final DosageLogMapper dosageLogMapper;
    private final PrescriptionRepository prescriptionRepository;

    public DosageLogDto getDosageLogById(Long id) {
        log.info("Fetching dosage log with: [id={}]", id);
        return dosageLogRepository.findById(id)
                .map(dosageLogMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Dosage log not found with id " + id));
    }

    @Transactional
    public DosageLogDto createDosageLog(Long prescriptionId, DosageLogDto dosageLogDto) {
        log.info("Creating new dosage log for prescription: [id={}]", prescriptionId);

        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Prescription not found with id " + prescriptionId));

        DosageLog dosageLog = dosageLogMapper.toEntity(dosageLogDto);
        dosageLog = dosageLogRepository.save(dosageLog);

        return dosageLogMapper.toDTO(dosageLog);
    }

    @Transactional
    public DosageLogDto updateDosageLog(Long id, DosageLogDto dosageLogDto) {
        log.info("Updating dosage log with: [id={}]", id);

        DosageLog dosageLog = dosageLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dosage log not found with id " + id));

        dosageLogMapper.updateEntityFromDto(dosageLogDto, dosageLog);
        dosageLog = dosageLogRepository.save(dosageLog);

        return dosageLogMapper.toDTO(dosageLog);
    }

    @Transactional
    public void deleteDosageLog(Long id) {
        log.info("Deleting dosage log with: [id={}]", id);

        DosageLog dosageLog = dosageLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dosage log not found with id " + id));

        dosageLogRepository.delete(dosageLog);
        log.info("Successfully deleted dosage log: [id={}]", id);
    }
}