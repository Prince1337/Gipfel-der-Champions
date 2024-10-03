package pieritz.prince.Champions.of.the.world.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class F1WorldChampionDTO {

    private Integer season;
    private Integer races;
    private String champion;
    private String team;
    private String nationality;
    private Integer age;
    private BigDecimal points;
    private Integer wins;

}
