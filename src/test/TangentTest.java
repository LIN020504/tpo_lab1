package test;

import Main.Tangent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TangentTest {
    final double EPS = 1.0E-5;

    public TangentTest() {
    }

    @DisplayName("Test for tg(x)")
    @ParameterizedTest
    @ValueSource(doubles = {180,170,150,120,60,50,30,10,0,-10,-30,-50,-60,-120,-150,-170,-180})
    public void testTanx(double x) {
        double actual = Tangent.tanX(x);
        double except = Math.tan(Math.toRadians(x));
        Assertions.assertEquals(actual, except, EPS);
    }

    @DisplayName("Test for exception")
    @ParameterizedTest
    @ValueSource(doubles = {90, -90})
    public void checkIllegalArguments(double x) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Tangent.tanX(x);
        });
    }
}