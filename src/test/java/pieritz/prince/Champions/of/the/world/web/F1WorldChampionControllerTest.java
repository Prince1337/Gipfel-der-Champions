package pieritz.prince.Champions.of.the.world.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pieritz.prince.Champions.of.the.world.domain.F1WorldChampion;
import pieritz.prince.Champions.of.the.world.services.F1WorldChampionService;

import java.util.Arrays;
import java.util.List;

@ActiveProfiles("test")
@Transactional
public class F1WorldChampionControllerTest {

    @Mock
    private F1WorldChampionService f1WorldChampionService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        F1WorldChampionController f1WorldChampionController = new F1WorldChampionController(f1WorldChampionService);
        mockMvc = MockMvcBuilders.standaloneSetup(f1WorldChampionController).build();
    }

    @Test
    public void testGetChampions() {
        List<F1WorldChampion> champions = Arrays.asList(new F1WorldChampion(), new F1WorldChampion());
        when(f1WorldChampionService.getChampions()).thenReturn(champions);

        try {
            mockMvc.perform(get("/v1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isNotEmpty());

            verify(f1WorldChampionService, times(1)).getChampions();
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetChampionBySeason() {
        int season = 2021;
        F1WorldChampion champion = new F1WorldChampion();
        when(f1WorldChampionService.getChampionBySeason(season)).thenReturn(champion);

        try {
            mockMvc.perform(get("/v1/season/{season}", season))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isNotEmpty());

            verify(f1WorldChampionService, times(1)).getChampionBySeason(season);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetChampionsByTeam() {
        String team = "Ferrari";
        List<F1WorldChampion> champions = Arrays.asList(new F1WorldChampion(), new F1WorldChampion());
        when(f1WorldChampionService.getChampionsByTeam(team)).thenReturn(champions);

        try {
            mockMvc.perform(get("/v1/team/{team}", team))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isNotEmpty());

            verify(f1WorldChampionService, times(1)).getChampionsByTeam(team);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetChampionByNationality() {
        String nationality = "British";
        List<F1WorldChampion> champions = Arrays.asList(new F1WorldChampion(), new F1WorldChampion());
        when(f1WorldChampionService.getChampionByNationality(nationality)).thenReturn(champions);

        try {
            mockMvc.perform(get("/v1/nationality/{nationality}", nationality))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isNotEmpty());

            verify(f1WorldChampionService, times(1)).getChampionByNationality(nationality);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetChampionsSortedByPoints() {
        List<F1WorldChampion> champions = Arrays.asList(new F1WorldChampion(), new F1WorldChampion());
        when(f1WorldChampionService.getChampionsSortedByPoints()).thenReturn(champions);

        try {
            mockMvc.perform(get("/v1/points"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isNotEmpty());

            verify(f1WorldChampionService, times(1)).getChampionsSortedByPoints();
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetChampionsSortedByWins() {
        List<F1WorldChampion> champions = Arrays.asList(new F1WorldChampion(), new F1WorldChampion());
        when(f1WorldChampionService.getChampionsSortedByWins()).thenReturn(champions);

        try {
            mockMvc.perform(get("/v1/wins"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isNotEmpty());

            verify(f1WorldChampionService, times(1)).getChampionsSortedByWins();
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetChampionsSortedByAge() {
        List<F1WorldChampion> champions = Arrays.asList(new F1WorldChampion(), new F1WorldChampion());
        when(f1WorldChampionService.getChampionsSortedByAge()).thenReturn(champions);

        try {
            mockMvc.perform(get("/v1/age"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isNotEmpty());

            verify(f1WorldChampionService, times(1)).getChampionsSortedByAge();
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetChampionsSortedByNationality() {
        List<F1WorldChampion> champions = Arrays.asList(new F1WorldChampion(), new F1WorldChampion());
        when(f1WorldChampionService.getChampionsSortedByNationality()).thenReturn(champions);

        try {
            mockMvc.perform(get("/v1/nationality"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isNotEmpty());

            verify(f1WorldChampionService, times(1)).getChampionsSortedByNationality();
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetChampionsSortedByRaces() {
        List<F1WorldChampion> champions = Arrays.asList(new F1WorldChampion(), new F1WorldChampion());
        when(f1WorldChampionService.getChampionsSortedByRaces()).thenReturn(champions);

        try {
            mockMvc.perform(get("/v1/races"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isNotEmpty());

            verify(f1WorldChampionService, times(1)).getChampionsSortedByRaces();
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
