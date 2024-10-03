package pieritz.prince.Champions.of.the.world.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pieritz.prince.Champions.of.the.world.domain.F1WorldChampion;
import pieritz.prince.Champions.of.the.world.dto.F1WorldChampionDTO;
import pieritz.prince.Champions.of.the.world.repositories.F1WorldChampionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class F1WorldChampionServiceImplementation implements F1WorldChampionService {

    private final F1WorldChampionRepository f1WorldChampionRepository;

    public List<F1WorldChampion> saveChampions(List<F1WorldChampionDTO> dtos) {
        List<F1WorldChampion> entities = dtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        f1WorldChampionRepository.saveAll(entities);
        return f1WorldChampionRepository.findAllByOrderBySeasonAsc();
    }


    @Override
    public List<F1WorldChampion> getChampions() {
        return f1WorldChampionRepository.findAllByOrderBySeasonAsc();
    }

    @Override
    public List<F1WorldChampion> getChampionsBySeason(int season) {
        if (season == 0) {
            return f1WorldChampionRepository.findAll();
        }
        return f1WorldChampionRepository.findBySeason(season);
    }

    @Override
    public List<F1WorldChampion> getChampionsByTeam(String team) {
        // if team exists in DB
        if (f1WorldChampionRepository.existsByTeam(team)) {
            return f1WorldChampionRepository.findByTeamOrderBySeasonAsc(team);
        }
        return List.of();
    }

    @Override
    public List<F1WorldChampion> getChampionByNationality(String nationality) {
        if (f1WorldChampionRepository.existsByNationality(nationality)) {
            return f1WorldChampionRepository.findByNationalityOrderBySeasonAsc(nationality);
        }
        return null;
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedByPoints() {
        return f1WorldChampionRepository.findAllByOrderByPointsDesc();
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedByWins() {
        return f1WorldChampionRepository.findAllByOrderByWinsDesc();
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedByAge() {
        return f1WorldChampionRepository.findAllByOrderByAgeAsc();
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedByNationality() {
        return f1WorldChampionRepository.findAllByOrderByNationalityAsc();
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedBySeason() {
        return f1WorldChampionRepository.findAllByOrderBySeasonAsc();
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedByRaces() {
        return f1WorldChampionRepository.findAllByOrderByRacesDesc();
    }

    @Override
    public List<F1WorldChampion> findAllByPointsGreaterThan(BigDecimal points) {
        return f1WorldChampionRepository.findAllByPointsGreaterThan(points);
    }

    @Override
    public List<F1WorldChampion> findAllByPointsLessThan(BigDecimal points) {
        return f1WorldChampionRepository.findAllByPointsLessThan(points);
    }

    private F1WorldChampion convertToEntity(F1WorldChampionDTO dto) {
        return F1WorldChampion.builder()
                .season(dto.getSeason())
                .races(dto.getRaces())
                .champion(dto.getChampion())
                .team(dto.getTeam())
                .nationality(dto.getNationality())
                .age(dto.getAge())
                .points(dto.getPoints())
                .wins(dto.getWins())
                .build();
    }
}
