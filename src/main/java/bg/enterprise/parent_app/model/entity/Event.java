package bg.enterprise.parent_app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class Event extends Audit {

  @Column(nullable = false)
  private LocalDate eventStart;

  private LocalDate eventEnd;
}