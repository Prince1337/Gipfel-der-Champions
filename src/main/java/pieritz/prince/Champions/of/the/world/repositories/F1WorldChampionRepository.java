package pieritz.prince.Champions.of.the.world.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pieritz.prince.Champions.of.the.world.domain.F1WorldChampion;

import java.math.BigDecimal;
import java.util.List;

public interface F1WorldChampionRepository extends JpaRepository<F1WorldChampion, Long> {
    List<F1WorldChampion> findBySeason(int season);

    boolean existsByTeam(String team);


    boolean existsByNationality(String nationality);

    List<F1WorldChampion> findByNationalityOrderBySeasonAsc(String nationality);

    List<F1WorldChampion> findAllByOrderByRacesDesc();

    List<F1WorldChampion> findAllByOrderBySeasonAsc();

    List<F1WorldChampion> findAllByOrderByNationalityAsc();

    List<F1WorldChampion> findAllByOrderByAgeAsc();

    List<F1WorldChampion> findAllByOrderByWinsDesc();

    List<F1WorldChampion> findAllByOrderByPointsDesc();

    List<F1WorldChampion> findByTeamOrderBySeasonAsc(String team);

    List<F1WorldChampion> findAllByPointsGreaterThan(BigDecimal points);

    List<F1WorldChampion> findAllByPointsLessThan(BigDecimal points);
}
