package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PrescriptionDto extends AuditDto {
    private Long childId;
    private Long medicationId;
    private BigDecimal dosageAmount;
    private String dosageUnit;
    private Integer frequencyPerDay;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;
    private List<DosageLogDto> dosageLogs;
    private List<MedicineOpenLogDto> medicineOpenLogs;
}
