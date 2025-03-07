package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.DosageLogDto;
import bg.enterprise.parent_app.service.write.DosageLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dosage-logs")
@RequiredArgsConstructor
@Slf4j
public class DosageLogController {

    private final DosageLogService dosageLogService;


    @GetMapping("/{id}")
    public ResponseEntity<DosageLogDto> getDosageLogById(@PathVariable Long id) {
        log.info("Received request to fetch dosage log with ID: {}", id);
        DosageLogDto dosageLogDto = dosageLogService.getDosageLogById(id);
        return ResponseEntity.ok(dosageLogDto);
    }


    @PostMapping("/{prescriptionId}")
    public ResponseEntity<DosageLogDto> createDosageLog(@PathVariable Long prescriptionId, @RequestBody DosageLogDto dosageLogDto) {
        log.info("Received request to create a new dosage log for prescription ID: {}", prescriptionId);
        DosageLogDto createdDosageLog = dosageLogService.createDosageLog(prescriptionId, dosageLogDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDosageLog);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DosageLogDto> updateDosageLog(@PathVariable Long id, @RequestBody DosageLogDto dosageLogDto) {
        log.info("Received request to update dosage log with ID: {}", id);
        DosageLogDto updatedDosageLog = dosageLogService.updateDosageLog(id, dosageLogDto);
        return ResponseEntity.ok(updatedDosageLog);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDosageLog(@PathVariable Long id) {
        log.info("Received request to delete dosage log with ID: {}", id);
        dosageLogService.deleteDosageLog(id);
        return ResponseEntity.noContent().build();
    }
}
