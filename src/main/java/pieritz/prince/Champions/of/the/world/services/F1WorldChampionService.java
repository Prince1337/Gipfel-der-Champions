package pieritz.prince.Champions.of.the.world.services;

import pieritz.prince.Champions.of.the.world.domain.F1WorldChampion;
import pieritz.prince.Champions.of.the.world.dto.F1WorldChampionDTO;

import java.math.BigDecimal;
import java.util.List;

public interface F1WorldChampionService {
    List<F1WorldChampion> saveChampions(List<F1WorldChampionDTO> f1WorldChampionDTOS);

    List<F1WorldChampion> getChampions();

    List<F1WorldChampion> getChampionsBySeason(int season);

    List<F1WorldChampion> getChampionsByTeam(String team);

    List<F1WorldChampion> getChampionByNationality(String nationality);

    List<F1WorldChampion> getChampionsSortedByPoints();

    List<F1WorldChampion> getChampionsSortedByWins();

    List<F1WorldChampion> getChampionsSortedByAge();

    List<F1WorldChampion> getChampionsSortedByNationality();

    List<F1WorldChampion> getChampionsSortedBySeason();

    List<F1WorldChampion> getChampionsSortedByRaces();

    List<F1WorldChampion> findAllByPointsGreaterThan(BigDecimal points);

    List<F1WorldChampion> findAllByPointsLessThan(BigDecimal points);
}
