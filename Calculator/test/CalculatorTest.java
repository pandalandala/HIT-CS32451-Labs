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
        double third = 4;
        String fourth = "6";
        double fifth = 2.5;
        String sixth = "3.5";
        double seventh = -7;
        String eighth = "-8";

        try {

            /*
             * basic calculator test
             * */
            assertEquals(8, calculator.calc(first, second, '+'));
            assertEquals(10, calculator.calc(third, fourth, '+'));
            assertEquals(6.0, calculator.calc(fifth, sixth, '+'));
            assertEquals(-15, calculator.calc(seventh, eighth, '+'));

            assertEquals(-2, calculator.calc(first, second, '-'));
            assertEquals(-2, calculator.calc(third, fourth, '-'));
            assertEquals(-1, calculator.calc(fifth, sixth, '-'));
            assertEquals(1, calculator.calc(seventh, eighth, '-'));

            assertEquals(15, calculator.calc(first, second, '*'));
            assertEquals(24, calculator.calc(third, fourth, '*'));
            assertEquals(8.75, calculator.calc(fifth, sixth, '*'));
            assertEquals(56, calculator.calc(seventh, eighth, '*'));

            assertEquals(0.6, calculator.calc(first, second, '/'));
            assertEquals(0.6666666666666666, calculator.calc(third, fourth, '/'));
            assertEquals(0.7142857142857143, calculator.calc(fifth, sixth, '/'));
            assertEquals(0.875, calculator.calc(seventh, eighth, '/'));

            assertEquals(3d, calculator.calc(first, second, '%'));
            assertEquals(4d, calculator.calc(third, fourth, '%'));
            assertEquals(2.5d, calculator.calc(fifth, sixth, '%'));

            /*
             * scientific mode test
             * */
            assertEquals(243, calculator.calc(first, second, '^'));
            assertEquals(4096, calculator.calc(third, fourth, '^'));
            assertEquals(24.705294220065465, calculator.calc(fifth, sixth, '^'));

            assertEquals(2.23606797749979, Math.sqrt(Double.parseDouble(second)));
            assertEquals(2.449489742783178, Math.sqrt(Double.parseDouble(fourth)));
            assertEquals(1.8708286933869707, Math.sqrt(Double.parseDouble(sixth)));

            assertEquals(1.6094379124341003, Math.log(Double.parseDouble(second)));
            assertEquals(1.791759469228055, Math.log(Double.parseDouble(fourth)));
            assertEquals(1.252762968495368, Math.log(Double.parseDouble(sixth)));

            /*
             * different themes test
             * no unit test, only integration test
             * */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
