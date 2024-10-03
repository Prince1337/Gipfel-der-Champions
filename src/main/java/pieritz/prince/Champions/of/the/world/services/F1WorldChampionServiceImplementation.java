package pieritz.prince.Champions.of.the.world.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pieritz.prince.Champions.of.the.world.domain.F1WorldChampion;
import pieritz.prince.Champions.of.the.world.dto.F1WorldChampionDTO;
import pieritz.prince.Champions.of.the.world.repositories.F1WorldChampionRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class F1WorldChampionServiceImplementation implements F1WorldChampionService {

    private static final Logger logger = LoggerFactory.getLogger(F1WorldChampionServiceImplementation.class);

    private final F1WorldChampionRepository f1WorldChampionRepository;

    @Override
    public List<F1WorldChampion> saveChampions(List<F1WorldChampionDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            logger.warn("No champions provided for saving.");
            return Collections.emptyList();
        }

        List<F1WorldChampion> entities = dtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        try {
            f1WorldChampionRepository.saveAll(entities);
            logger.info("Saved {} champions.", entities.size());
        } catch (Exception e) {
            logger.error("Error saving champions: ", e);
        }

        return f1WorldChampionRepository.findAllByOrderBySeasonAsc();
    }

    @Override
    public List<F1WorldChampion> getChampions() {
        logger.info("Fetching all champions.");
        return f1WorldChampionRepository.findAllByOrderBySeasonAsc();
    }

    @Override
    public List<F1WorldChampion> getChampionsBySeason(int season) {
        if (season <= 0) {
            logger.warn("Invalid season number: {}", season);
            return Collections.emptyList();
        }
        logger.info("Fetching champions for season: {}", season);
        return f1WorldChampionRepository.findBySeason(season);
    }

    @Override
    public List<F1WorldChampion> getChampionsByTeam(String team) {
        if (team == null || team.isEmpty()) {
            logger.warn("Team name is empty or null.");
            return Collections.emptyList();
        }

        if (!f1WorldChampionRepository.existsByTeam(team)) {
            logger.info("No champions found for team: {}", team);
            return Collections.emptyList();
        }

        logger.info("Fetching champions for team: {}", team);
        return f1WorldChampionRepository.findByTeamOrderBySeasonAsc(team);
    }

    @Override
    public List<F1WorldChampion> getChampionByNationality(String nationality) {
        if (nationality == null || nationality.isEmpty()) {
            logger.warn("Nationality is empty or null.");
            return Collections.emptyList();
        }

        if (!f1WorldChampionRepository.existsByNationality(nationality)) {
            logger.info("No champions found for nationality: {}", nationality);
            return Collections.emptyList();
        }

        logger.info("Fetching champions for nationality: {}", nationality);
        return f1WorldChampionRepository.findByNationalityOrderBySeasonAsc(nationality);
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedByPoints() {
        logger.info("Fetching champions sorted by points.");
        return f1WorldChampionRepository.findAllByOrderByPointsDesc();
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedByWins() {
        logger.info("Fetching champions sorted by wins.");
        return f1WorldChampionRepository.findAllByOrderByWinsDesc();
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedByAge() {
        logger.info("Fetching champions sorted by age.");
        return f1WorldChampionRepository.findAllByOrderByAgeAsc();
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedByNationality() {
        logger.info("Fetching champions sorted by nationality.");
        return f1WorldChampionRepository.findAllByOrderByNationalityAsc();
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedBySeason() {
        logger.info("Fetching champions sorted by season.");
        return f1WorldChampionRepository.findAllByOrderBySeasonAsc();
    }

    @Override
    public List<F1WorldChampion> getChampionsSortedByRaces() {
        logger.info("Fetching champions sorted by races.");
        return f1WorldChampionRepository.findAllByOrderByRacesDesc();
    }

    @Override
    public List<F1WorldChampion> filterChampionsByPointsGreaterThan(BigDecimal points) {
        if (points == null || points.compareTo(BigDecimal.ZERO) <= 0) {
            logger.warn("Invalid points value: {}", points);
            return Collections.emptyList();
        }

        logger.info("Fetching champions with points greater than: {}", points);
        return f1WorldChampionRepository.findAllByPointsGreaterThan(points);
    }

    @Override
    public List<F1WorldChampion> filterChampionsByPointsLessThan(BigDecimal points) {
        if (points == null || points.compareTo(BigDecimal.ZERO) <= 0) {
            logger.warn("Invalid points value: {}", points);
            return Collections.emptyList();
        }

        logger.info("Fetching champions with points less than: {}", points);
        return f1WorldChampionRepository.findAllByPointsLessThan(points);
    }

    private F1WorldChampion convertToEntity(F1WorldChampionDTO dto) {
        if (dto == null) {
            logger.warn("Received null DTO for conversion.");
            return null;
        }

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
