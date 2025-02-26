package bg.enterprise.parent_app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "medication")
@Getter
@Setter
public class Medication extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    private String brand;

    @Column(length = 3000)
    private String description;

    @Column(length = 50, nullable = false)
    private String dosageForm;

    private LocalDate openDate;

    @Column(length = 3000)
    private String instructions;
}
