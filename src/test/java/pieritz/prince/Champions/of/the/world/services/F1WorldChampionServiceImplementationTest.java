package pieritz.prince.Champions.of.the.world.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import pieritz.prince.Champions.of.the.world.domain.F1WorldChampion;
import pieritz.prince.Champions.of.the.world.dto.F1WorldChampionDTO;
import pieritz.prince.Champions.of.the.world.repositories.F1WorldChampionRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class F1WorldChampionServiceImplementationTest {

    @Mock
    private F1WorldChampionRepository f1WorldChampionRepository;

    private F1WorldChampionServiceImplementation f1WorldChampionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        f1WorldChampionService = new F1WorldChampionServiceImplementation(f1WorldChampionRepository);
    }

    @Test
    void testSaveChampions() {
        F1WorldChampionDTO dto = createValidDTO();
        List<F1WorldChampionDTO> dtos = List.of(dto);

        when(f1WorldChampionRepository.findAllByOrderBySeasonAsc()).thenReturn(List.of(
                new F1WorldChampion(null, 2023, 22, "Max Verstappen", "Red Bull", "NL", 26, BigDecimal.valueOf(454.5), 15)
        ));

        List<F1WorldChampion> savedDtos = f1WorldChampionService.saveChampions(dtos);

        assertNotNull(savedDtos, "Saved DTOs should not be null.");
        assertEquals(1, savedDtos.size(), "Expected one saved DTO.");
        assertEquals("Max Verstappen", savedDtos.get(0).getChampion(), "Champion should be Max Verstappen.");
        verify(f1WorldChampionRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testGetAllChampions() {
        when(f1WorldChampionRepository.findAllByOrderBySeasonAsc()).thenReturn(List.of(
                new F1WorldChampion(null, 2023, 22, "Max Verstappen", "Red Bull", "NL", 26, BigDecimal.valueOf(454.5), 15)
        ));

        List<F1WorldChampion> champions = f1WorldChampionService.getChampions();

        assertNotNull(champions, "Champions list should not be null.");
        assertEquals(1, champions.size(), "Expected one champion.");
        assertEquals("Max Verstappen", champions.get(0).getChampion(), "Champion should be Max Verstappen.");
    }

    @Test
    void testGetChampionBySeason() {
        when(f1WorldChampionRepository.findBySeason(2023)).thenReturn(
                new F1WorldChampion(null, 2023, 22, "Max Verstappen", "Red Bull", "NL", 26, BigDecimal.valueOf(454.5), 15)
        );

        F1WorldChampion champion = f1WorldChampionService.getChampionBySeason(2023);

        assertNotNull(champion, "Champion should not be null.");
        assertEquals("Max Verstappen", champion.getChampion(), "Champion should be Max Verstappen.");
    }

    private F1WorldChampionDTO createValidDTO() {
        return new F1WorldChampionDTO(2023, 22, "Max Verstappen", "Red Bull", "NL", 26, BigDecimal.valueOf(454.5), 15);
    }
}
