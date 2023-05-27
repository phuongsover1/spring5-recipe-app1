package guru.springframework.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "recipe" })
@Data
@Entity
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  private BigDecimal amount;

  @ManyToOne
  private Recipe recipe;

  @OneToOne(fetch = FetchType.EAGER)
  private UnitOfMeasure uom;

  public Ingredient(
      String description,
      BigDecimal amount,
      Recipe recipe,
      UnitOfMeasure uom) {
    this.description = description;
    this.amount = amount;
    this.recipe = recipe;
    this.uom = uom;
  }

  public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
    this(description, amount, null, uom);
  }

  public Ingredient(Long id, String description, BigDecimal amount, UnitOfMeasure uom) {
    this(id, description, amount, null, uom);
  }
}
