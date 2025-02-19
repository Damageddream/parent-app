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
    private long id;
    private float dosageGiven;
    private Instant administeredAt;
    private String notes;
}
