package bg.enterprise.parent_app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "dosage_log")
@Getter
@Setter
public class DosageLog extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Float dosageGiven;

    @Column(nullable = false)
    private Instant administeredAt;

    @Column(length = 3000)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;
}
