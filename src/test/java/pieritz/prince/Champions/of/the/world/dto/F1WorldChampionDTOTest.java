package pieritz.prince.Champions.of.the.world.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class F1WorldChampionDTOTest {

    private F1WorldChampionDTO dto;

    @BeforeEach
    void setUp() {
        dto = new F1WorldChampionDTO(2023, 22, "Max Verstappen", "Red Bull", "NL", 26, BigDecimal.valueOf(454.5), 15);
    }

    @Test
    void testGetSeason() {
        assertEquals(2023, dto.getSeason(), "Season should be 2023.");
    }

    @Test
    void testGetRaces() {
        assertEquals(22, dto.getRaces(), "Races should be 22.");
    }

    @Test
    void testGetChampion() {
        assertEquals("Max Verstappen", dto.getChampion(), "Champion should be Max Verstappen.");
    }

    @Test
    void testGetTeam() {
        assertEquals("Red Bull", dto.getTeam(), "Team should be Red Bull.");
    }

    @Test
    void testGetNationality() {
        assertEquals("NL", dto.getNationality(), "Nationality should be NL.");
    }

    @Test
    void testGetAge() {
        assertEquals(26, dto.getAge(), "Age should be 26.");
    }

    @Test
    void testGetPoints() {
        assertEquals(BigDecimal.valueOf(454.5), dto.getPoints(), "Points should be 454.5.");
    }

    @Test
    void testGetWins() {
        assertEquals(15, dto.getWins(), "Wins should be 15.");
    }

    @Test
    void testConstructorWithAllFields() {
        F1WorldChampionDTO dto = new F1WorldChampionDTO(2022, 20, "Lewis Hamilton", "Mercedes", "GB", 35, BigDecimal.valueOf(300.5), 10);
        assertNotNull(dto, "DTO should be created successfully.");
        assertEquals(2022, dto.getSeason(), "Season should be 2022.");
        assertEquals("Lewis Hamilton", dto.getChampion(), "Champion should be Lewis Hamilton.");
        assertEquals("Mercedes", dto.getTeam(), "Team should be Mercedes.");
        assertEquals("GB", dto.getNationality(), "Nationality should be GB.");
        assertEquals(35, dto.getAge(), "Age should be 35.");
        assertEquals(BigDecimal.valueOf(300.5), dto.getPoints(), "Points should be 300.5.");
        assertEquals(10, dto.getWins(), "Wins should be 10.");
    }

    @Test
    void testConstructorWithNullValues() {
        F1WorldChampionDTO dto = new F1WorldChampionDTO(null, null, null, null, null, null, null, null);
        assertNotNull(dto, "DTO should be created successfully even with null values.");
        assertNull(dto.getSeason(), "Season should be null.");
        assertNull(dto.getChampion(), "Champion should be null.");
        assertNull(dto.getTeam(), "Team should be null.");
        assertNull(dto.getNationality(), "Nationality should be null.");
        assertNull(dto.getAge(), "Age should be null.");
        assertNull(dto.getPoints(), "Points should be null.");
        assertNull(dto.getWins(), "Wins should be null.");
    }
}
