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

    @OneToOne
    @JoinColumn(name = "illness_id")
    private Illness illness;

    @OneToOne
    @JoinColumn(name = "injury_id")
    private Injury injury;

    @ManyToOne
    @JoinColumn(name = "chronic_condition_id")
    private Injury chronicCondition;

    @ManyToOne
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;

    @Column(nullable = false)
    private Float dosageAmount;

    @Column(length = 20, nullable = false)
    private String dosageUnit;

    @Column(nullable = false)
    private Integer frequencyPerDay;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(length = 3000)
    private String notes;

    @OneToMany
    private List<DosageLog> dosageLogs = new ArrayList<>();
}
