package pieritz.prince.Champions.of.the.world.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pieritz.prince.Champions.of.the.world.domain.F1WorldChampion;
import pieritz.prince.Champions.of.the.world.services.F1WorldChampionService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/f1WorldChampions")
public class F1WorldChampionController {

    private final F1WorldChampionService f1WorldChampionService;

    @GetMapping()
    public ResponseEntity<List<F1WorldChampion>> getChampions() {
        return ResponseEntity.ok(f1WorldChampionService.getChampions());
    }

    @GetMapping("/season/{season}")
    public ResponseEntity<List<F1WorldChampion>> getChampionsBySeason(@PathVariable int season) {
        return ResponseEntity.ok(f1WorldChampionService.getChampionsBySeason(season));
    }

    @GetMapping("/team/{team}")
    public ResponseEntity<List<F1WorldChampion>> getChampionsByTeam(@PathVariable String team) {
        return ResponseEntity.ok(f1WorldChampionService.getChampionsByTeam(team));
    }

    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<List<F1WorldChampion>> getChampionByNationality(@PathVariable String nationality) {
        return ResponseEntity.ok(f1WorldChampionService.getChampionByNationality(nationality));
    }

    @GetMapping("/points")
    public ResponseEntity<List<F1WorldChampion>> getChampionsSortedByPoints() {
        return ResponseEntity.ok(f1WorldChampionService.getChampionsSortedByPoints());
    }

    @GetMapping("/wins")
    public ResponseEntity<List<F1WorldChampion>> getChampionsSortedByWins() {
        return ResponseEntity.ok(f1WorldChampionService.getChampionsSortedByWins());
    }

    @GetMapping("/age")
    public ResponseEntity<List<F1WorldChampion>> getChampionsSortedByAge() {
        return ResponseEntity.ok(f1WorldChampionService.getChampionsSortedByAge());
    }

    @GetMapping("/nationality")
    public ResponseEntity<List<F1WorldChampion>> getChampionsSortedByNationality() {
        return ResponseEntity.ok(f1WorldChampionService.getChampionsSortedByNationality());
    }

    @GetMapping("/races")
    public ResponseEntity<List<F1WorldChampion>> getChampionsSortedByRaces() {
        return ResponseEntity.ok(f1WorldChampionService.getChampionsSortedByRaces());
    }

    @GetMapping("/season")
    public ResponseEntity<List<F1WorldChampion>> getChampionsSortedBySeason() {
        return ResponseEntity.ok(f1WorldChampionService.getChampionsSortedBySeason());
    }

    @GetMapping("/points/greaterThan")
    public ResponseEntity<List<F1WorldChampion>> filterChampionsByPointsGreaterThan(@PathVariable BigDecimal points) {
        return ResponseEntity.ok(f1WorldChampionService.filterChampionsByPointsGreaterThan(points));
    }

    @GetMapping("/points/lessThan")
    public ResponseEntity<List<F1WorldChampion>> filterChampionsByPointsLessThan(@PathVariable BigDecimal points) {
        return ResponseEntity.ok(f1WorldChampionService.filterChampionsByPointsLessThan(points));
    }
}
