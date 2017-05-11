package calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private final double DELTA = 1e-2;

    @ParameterizedTest
    @CsvSource({"'5*3', 15.0", "'1+3*7', 22.0", "'5*2-11', -1.0",
            "'(5+3)*2', 16.0", "'5*(4-3)/10', 0.5"})
    void testCalculator(String expression, double result) {
        assertEquals(result, Calculator.calc(expression), DELTA);
    }

}
