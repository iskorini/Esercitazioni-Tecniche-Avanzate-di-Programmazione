package junit.example;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Federico on 12/10/2016.
 */
public class FactorialTest {
    private Factorial f;

    @Before
    public void setUp() {
        f = new Factorial();
    }

    @Test
    public void testBaseCase() {
        assertFactorial(0, 1);
    }

    @Test
    public void testNonBaseCase() {
        assertFactorial(5,f.compute(4)*5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeInteger() {
        assertFactorial(-1, 0);
    }
    private void assertFactorial(int input , int expected) {
        int result = f.compute(input);
        assertEquals(expected,result);
    }
}
