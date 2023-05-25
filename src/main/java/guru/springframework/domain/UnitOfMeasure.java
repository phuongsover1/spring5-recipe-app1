package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class UnitOfMeasure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  public UnitOfMeasure(String description) {
    this.description = description;
  }

  @Builder
  public UnitOfMeasure(Long id, String description) {
    this.id = id;
    this.description = description;
  }
}
