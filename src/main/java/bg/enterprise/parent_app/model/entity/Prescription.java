package bg.enterprise.parent_app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prescription")
@Getter
@Setter
public class Prescription extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @ManyToOne
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;

    private Float dosageAmount;
    private String dosageUnit;
    private Integer frequencyPerDay;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DosageLog> dosageLogs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicineOpenLog> medicineOpenLogs = new ArrayList<>();
}
