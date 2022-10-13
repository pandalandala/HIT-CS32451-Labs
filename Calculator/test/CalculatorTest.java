import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @org.junit.jupiter.api.Test
    void calc() {
        double first = 3;
        String second = "5";

        try {
            assertEquals(8, calculator.calc(first, second, '+'));
            assertEquals(-2, calculator.calc(first, second, '-'));
            assertEquals(15, calculator.calc(first, second, '*'));
            assertEquals(0.6, calculator.calc(first, second, '/'));
            assertEquals(3d, calculator.calc(first, second, '%'));
            assertEquals(243, calculator.calc(first, second, '^'));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}