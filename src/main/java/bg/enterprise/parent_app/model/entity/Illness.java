package bg.enterprise.parent_app.model.entity;

import bg.enterprise.parent_app.model.type.IllnessType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "illness")
@Getter
@Setter
public class Illness extends Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private IllnessType illnessType;

    @Column(length = 3000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @OneToMany(mappedBy = "illness", fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "illness_medications",
            joinColumns = @JoinColumn(name = "illness_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<Medication> medications = new ArrayList<>();
}