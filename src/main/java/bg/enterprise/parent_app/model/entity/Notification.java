package bg.enterprise.parent_app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "notification")
@Getter
@Setter
public class Notification extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @Column(length = 300, nullable = false)
    private String message;

    private boolean isRead;
}
