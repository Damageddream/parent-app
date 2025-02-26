package bg.enterprise.parent_app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chronic_condition")
@Getter
@Setter
public class ChronicCondition extends Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 3000)
    private String description;

    @Column(length = 3000)
    private String instructions;

    @Column(length = 3000)
    private String notes;
}
