package mutation.example;

import org.junit.Before;
import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.*;

/**
 * Created by Federico on 12/10/2016.
 */
public class TimelineTest {


    private Timeline timeline;

    @Before
    public void setUp() throws Exception {
        timeline = new Timeline();
    }

    @Test
    public void testDefaultGetCount() {
        assertEquals(timeline.getFetchCount(), Timeline.DEFAULT_FETCH_COUNT);
    }

    @Test
    public void testCorrectSetFetchCount() {
        timeline.setFetchCount(5);
        assertEquals(5, timeline.getFetchCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectSetFetchCountNegative() {
        timeline.setFetchCount(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectSetFetchCountZero() {
        timeline.setFetchCount(0);
    }




}