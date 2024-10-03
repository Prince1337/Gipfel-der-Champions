package pieritz.prince.Champions.of.the.world.domain;

import jakarta.persistence.*;
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

    private Integer season;
    private Integer races;
    private String champion;
    private String team;
    private String nationality;
    private Integer age;
    private BigDecimal points;
    private Integer wins;


}