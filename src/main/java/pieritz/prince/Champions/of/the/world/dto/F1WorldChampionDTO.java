package pieritz.prince.Champions.of.the.world.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pieritz.prince.Champions.of.the.world.domain.F1WorldChampion;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class F1WorldChampionDTO {

    @NotNull(message = "Season cannot be null")
    @Min(value = 1900, message = "Season must be greater than 1900")
    @Max(value = 2100, message = "Season must be less than 2100")
    private Integer season;

    @NotNull(message = "Races cannot be null")
    @Min(value = 1, message = "Races must be at least 1")
    private Integer races;

    @NotBlank(message = "Champion name cannot be blank")
    private String champion;

    @NotBlank(message = "Team name cannot be blank")
    private String team;

    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;

    @NotNull(message = "Points cannot be null")
    @DecimalMin(value = "0.00", message = "Points must be greater than 0")
    private BigDecimal points;

    @NotNull(message = "Wins cannot be null")
    @Min(value = 0, message = "Wins cannot be negative")
    private Integer wins;

    public F1WorldChampion toEntity() {
        return F1WorldChampion.builder()
                .season(this.season)
                .races(this.races)
                .champion(this.champion)
                .team(this.team)
                .nationality(this.nationality)
                .age(this.age)
                .points(this.points)
                .wins(this.wins)
                .build();
    }

    public static F1WorldChampionDTO fromEntity(F1WorldChampion entity) {
        return F1WorldChampionDTO.builder()
                .season(entity.getSeason())
                .races(entity.getRaces())
                .champion(entity.getChampion())
                .team(entity.getTeam())
                .nationality(entity.getNationality())
                .age(entity.getAge())
                .points(entity.getPoints())
                .wins(entity.getWins())
                .build();
    }
}
