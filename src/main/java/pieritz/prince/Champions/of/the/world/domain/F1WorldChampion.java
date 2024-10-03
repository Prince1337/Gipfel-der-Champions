package pieritz.prince.Champions.of.the.world.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "f1_world_champions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class F1WorldChampion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Season cannot be null")
    @Min(value = 1950, message = "The first F1 championship started in 1950")
    private Integer season;

    @NotNull(message = "Number of races cannot be null")
    @Min(value = 1, message = "A season must have at least 1 race")
    private Integer races;

    @NotBlank(message = "Drivers name cannot be blank")
    private String champion;

    @NotBlank(message = "Team cannot be blank")
    private String team;

    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Drivers must be at least 18 years old")
    private Integer age;

    @NotNull(message = "Points cannot be null")
    @DecimalMin(value = "0.0", message = "Points must be at least 0")
    private BigDecimal points;

    @NotNull(message = "Wins cannot be null")
    @Min(value = 0, message = "Wins cannot be negative")
    private Integer wins;
}
