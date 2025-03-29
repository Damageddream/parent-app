package bg.enterprise.parent_app.model.entity;

import bg.enterprise.parent_app.model.type.InjuryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "injury")
@Getter
@Setter
public class Injury extends Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private InjuryType injuryType;

    @Column(length = 3000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @OneToMany(mappedBy = "injury", fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "injury_medications",
            joinColumns = @JoinColumn(name = "injury_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<Medication> medications = new ArrayList<>();
}