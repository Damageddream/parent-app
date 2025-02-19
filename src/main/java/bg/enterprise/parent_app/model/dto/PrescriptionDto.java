package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PrescriptionDto extends AuditDto {
    private long childId;
    private long medicationId;
    private float dosageAmount;
    private String dosageUnit;
    private int frequencyPerDay;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;
    private List<DosageLogDto> dosageLogs;
    private List<MedicineOpenLogDto> medicineOpenLogs;
}
