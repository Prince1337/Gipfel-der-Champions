package pieritz.prince.Champions.of.the.world.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pieritz.prince.Champions.of.the.world.domain.F1WorldChampion;
import pieritz.prince.Champions.of.the.world.services.F1WorldChampionService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class F1WorldChampionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private F1WorldChampionService f1WorldChampionService;

    private F1WorldChampion champion1;
    private F1WorldChampion champion2;

    @BeforeEach
    public void setUp() {
        champion1 = new F1WorldChampion();
        champion1.setSeason(2021);
        champion1.setChampion("Lewis Hamilton");
        champion1.setTeam("Mercedes");
        champion1.setNationality("British");
        champion1.setAge(36);
        champion1.setPoints(BigDecimal.valueOf(100));
        champion1.setWins(11);

        champion2 = new F1WorldChampion();
        champion2.setSeason(2020);
        champion2.setChampion("Max Verstappen");
        champion2.setTeam("Red Bull Racing");
        champion2.setNationality("Dutch");
        champion2.setAge(23);
        champion2.setPoints(BigDecimal.valueOf(200));
        champion2.setWins(15);
    }

    @Test
    public void testGetChampions() throws Exception {
        List<F1WorldChampion> champions = Arrays.asList(champion1, champion2);
        Mockito.when(f1WorldChampionService.getChampions()).thenReturn(champions);

        mockMvc.perform(get("/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].season").value(2021))
                .andExpect(jsonPath("$[0].champion").value("Lewis Hamilton"))
                .andExpect(jsonPath("$[1].season").value(2020))
                .andExpect(jsonPath("$[1].champion").value("Max Verstappen"));
    }

    @Test
    public void testGetChampionsBySeason() throws Exception {
        List<F1WorldChampion> champions = Collections.singletonList(champion1);
        Mockito.when(f1WorldChampionService.getChampionsBySeason(2021)).thenReturn(champions);

        mockMvc.perform(get("/v1/season/{season}", 2021))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].season").value(2021))
                .andExpect(jsonPath("$[0].champion").value("Lewis Hamilton"));
    }

    @Test
    public void testGetChampionsByTeam() throws Exception {
        List<F1WorldChampion> champions = Collections.singletonList(champion1);
        Mockito.when(f1WorldChampionService.getChampionsByTeam("Mercedes")).thenReturn(champions);

        mockMvc.perform(get("/v1/team/{team}", "Mercedes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].team").value("Mercedes"))
                .andExpect(jsonPath("$[0].champion").value("Lewis Hamilton"));
    }

    @Test
    public void testGetChampionByNationality() throws Exception {
        List<F1WorldChampion> champions = Collections.singletonList(champion1);
        Mockito.when(f1WorldChampionService.getChampionByNationality("British")).thenReturn(champions);

        mockMvc.perform(get("/v1/nationality/{nationality}", "British"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nationality").value("British"))
                .andExpect(jsonPath("$[0].champion").value("Lewis Hamilton"));
    }

    @Test
    public void testGetChampionsSortedByPoints() throws Exception {
        List<F1WorldChampion> champions = Arrays.asList(champion2, champion1);
        Mockito.when(f1WorldChampionService.getChampionsSortedByPoints()).thenReturn(champions);

        mockMvc.perform(get("/v1/points"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].points").value(200))
                .andExpect(jsonPath("$[1].points").value(100));
    }

    @Test
    public void testGetChampionsSortedByWins() throws Exception {
        List<F1WorldChampion> champions = Arrays.asList(champion2, champion1);
        Mockito.when(f1WorldChampionService.getChampionsSortedByWins()).thenReturn(champions);

        mockMvc.perform(get("/v1/wins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].wins").value(15))
                .andExpect(jsonPath("$[1].wins").value(11));
    }

    @Test
    public void testGetChampionsSortedByAge() throws Exception {
        List<F1WorldChampion> champions = Arrays.asList(champion2, champion1);
        Mockito.when(f1WorldChampionService.getChampionsSortedByAge()).thenReturn(champions);

        mockMvc.perform(get("/v1/age"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(23))
                .andExpect(jsonPath("$[1].age").value(36));
    }

    @Test
    public void testGetChampionsSortedByNationality() throws Exception {
        List<F1WorldChampion> champions = Arrays.asList(champion2, champion1);
        Mockito.when(f1WorldChampionService.getChampionsSortedByNationality()).thenReturn(champions);

        mockMvc.perform(get("/v1/nationality"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nationality").value("Dutch"))
                .andExpect(jsonPath("$[1].nationality").value("British"));
    }

    @Test
    public void testGetChampionsSortedByRaces() throws Exception {
        List<F1WorldChampion> champions = Arrays.asList(champion1, champion2);
        Mockito.when(f1WorldChampionService.getChampionsSortedByRaces()).thenReturn(champions);

        mockMvc.perform(get("/v1/races"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].season").value(2021))
                .andExpect(jsonPath("$[1].season").value(2020));
    }
}
