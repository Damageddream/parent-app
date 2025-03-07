package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.MedicationDto;
import bg.enterprise.parent_app.service.write.MedicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medications")
@RequiredArgsConstructor
@Slf4j
public class MedicationController {

    private final MedicationService medicationService;


    @GetMapping("/{id}")
    public ResponseEntity<MedicationDto> getMedicationById(@PathVariable Long id) {
        log.info("Received request to fetch medication with ID: {}", id);
        MedicationDto medicationDto = medicationService.getMedicationById(id);
        return ResponseEntity.ok(medicationDto);
    }


    @GetMapping
    public ResponseEntity<List<MedicationDto>> getAllMedications() {
        log.info("Received request to fetch all medications.");
        List<MedicationDto> medications = medicationService.getAllMedications();
        return ResponseEntity.ok(medications);
    }


    @PostMapping
    public ResponseEntity<MedicationDto> createMedication(@RequestBody MedicationDto medicationDto) {
        log.info("Received request to create a new medication: {}", medicationDto.getName());
        MedicationDto createdMedication = medicationService.createMedication(medicationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedication);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedicationDto> updateMedication(@PathVariable Long id, @RequestBody MedicationDto medicationDto) {
        log.info("Received request to update medication with ID: {}", id);
        MedicationDto updatedMedication = medicationService.updateMedication(id, medicationDto);
        return ResponseEntity.ok(updatedMedication);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        log.info("Received request to delete medication with ID: {}", id);
        medicationService.deleteMedication(id);
        return ResponseEntity.noContent().build();
    }
}