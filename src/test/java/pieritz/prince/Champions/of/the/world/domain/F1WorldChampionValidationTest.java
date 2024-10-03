package pieritz.prince.Champions.of.the.world.domain;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class F1WorldChampionValidationTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenAllFieldsValid_thenNoConstraintViolations() {
        F1WorldChampion champion = createValidChampion();
        Set<ConstraintViolation<F1WorldChampion>> violations = validator.validate(champion);
        assertTrue(violations.isEmpty(), "Expected no constraint violations for a valid champion.");
    }

    @Test
    public void whenSeasonIsNull_thenConstraintViolation() {
        F1WorldChampion champion = createValidChampion();
        champion.setSeason(null);
        Set<ConstraintViolation<F1WorldChampion>> violations = validator.validate(champion);
        assertViolation(violations, "Season cannot be null");
    }

    @Test
    public void whenChampionIsBlank_thenConstraintViolation() {
        F1WorldChampion champion = createValidChampion();
        champion.setChampion("");
        Set<ConstraintViolation<F1WorldChampion>> violations = validator.validate(champion);
        assertViolation(violations, "Drivers name cannot be blank");
    }

    @Test
    public void whenAgeIsTooLow_thenConstraintViolation() {
        F1WorldChampion champion = createValidChampion();
        champion.setAge(16);
        Set<ConstraintViolation<F1WorldChampion>> violations = validator.validate(champion);
        assertViolation(violations, "Drivers must be at least 18 years old");
    }

    @Test
    public void whenPointsAreNegative_thenConstraintViolation() {
        F1WorldChampion champion = createValidChampion();
        champion.setPoints(new BigDecimal("-50.0"));
        Set<ConstraintViolation<F1WorldChampion>> violations = validator.validate(champion);
        assertViolation(violations, "Points must be at least 0");
    }

    private F1WorldChampion createValidChampion() {
        return F1WorldChampion.builder()
                .season(2023)
                .races(22)
                .champion("Max Verstappen")
                .team("Red Bull Racing")
                .nationality("Dutch")
                .age(26)
                .points(new BigDecimal("454.5"))
                .wins(15)
                .build();
    }

    private void assertViolation(Set<ConstraintViolation<F1WorldChampion>> violations, String expectedMessage) {
        assertEquals(1, violations.size(), "Expected one constraint violation.");
        ConstraintViolation<F1WorldChampion> violation = violations.iterator().next();
        assertEquals(expectedMessage, violation.getMessage(), "Expected violation message does not match.");
    }
}
