package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.PrescriptionDto;
import bg.enterprise.parent_app.service.write.PrescriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;


    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDto> getPrescriptionById(@PathVariable Long id) {
        log.info("Received request to fetch prescription: [id={}]", id);
        PrescriptionDto prescriptionDto = prescriptionService.getPrescriptionById(id);
        return ResponseEntity.ok(prescriptionDto);
    }

    @PostMapping("/{childId}")
    public ResponseEntity<PrescriptionDto> createPrescription(@PathVariable Long childId, @RequestBody PrescriptionDto prescriptionDto) {
        log.info("Received request to create a new prescription for child: [id={}]", childId);
        PrescriptionDto createdPrescription = prescriptionService.createPrescription(childId, prescriptionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrescription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDto> updatePrescription(@PathVariable Long id, @RequestBody PrescriptionDto prescriptionDto) {
        log.info("Received request to update prescription: [id={}]", id);
        PrescriptionDto updatedPrescription = prescriptionService.updatePrescription(id, prescriptionDto);
        return ResponseEntity.ok(updatedPrescription);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        log.info("Received request to delete prescription: [id={}]", id);
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}
