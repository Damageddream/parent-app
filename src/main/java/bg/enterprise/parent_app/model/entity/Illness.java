package bg.enterprise.parent_app.model.entity;

import bg.enterprise.parent_app.model.type.IllnessType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private IllnessType type;

    @Column(length = 3000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @OneToOne(mappedBy = "illness", fetch = FetchType.LAZY)
    private Prescription prescription;
}