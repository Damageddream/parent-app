package bg.enterprise.parent_app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Audit {

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private Instant creationDate;

  @CreatedBy
  @Column(length = 40, nullable = false, updatable = false)
  private String createdBy;

  @LastModifiedDate
  private Instant updateDate;

  @LastModifiedBy
  @Column(length = 40)
  private String updatedBy;
}