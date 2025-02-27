package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.service.external.MedicationImportService;
import bg.enterprise.parent_app.util.ExceptionHandlingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImportController {

    private final MedicationImportService medicationImportService;

    @GetMapping("/import/medication-data")
    public ResponseEntity<String> importMedicationData() {
        try {
            log.info("Importing medication data");
            medicationImportService.importMedicationDataFromXls();
            return ResponseEntity.ok("Medication data imported successfully");
        } catch (Exception e) {
            log.error("Error while importing medication data: {}", ExceptionHandlingUtils.getRootCauseMessage(e));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Import failed: " + e.getMessage());
        }
    }
}