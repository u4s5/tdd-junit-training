package range;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {

    @Test
    void testIsBeforeTrue() {
        assertTrue(new Range(0L, 5L).isBefore(new Range(10L, 17L)));
    }

    @Test
    void testIsBeforeFalse() {
        assertFalse(new Range(-5L, 10L).isBefore(new Range(-10L, -7L)));
    }

    @Test
    void testIsAfterTrue() {
        assertTrue(new Range(-1L, 3L).isAfter(new Range(-10L, -7L)));
    }

    @Test
    void testIsAfterFalse() {
        assertFalse(new Range(0L, 5L).isAfter(new Range(7L, 10L)));
    }

    @Test
    void testIsConcurrentTrue() {
        assertTrue(new Range(0L, 4L).isConcurrent(new Range(1L, 2L)));
    }

    @Test
    void testIsConcurrentFalse() {
        assertFalse(new Range(-7L, -5L).isConcurrent(new Range(5L, 7L)));
    }

    @Test
    void testGetLowerBound() {
        assertEquals(5L, new Range(5L, 150L).getLowerBound());
    }

    @Test
    void testGetUpperBound() {
        assertEquals(-10L, new Range(-1024L, -10L).getUpperBound());
    }

    @Test
    void testContainsTrue() {
        assertTrue(new Range(-10L, 10L).contains(7L));
    }

    @Test
    void testContainsFalse() {
        assertFalse(new Range(-1540L, -125L).contains(150L));
    }

    @Test
    void testAsList() {
        List<Long> testingList = new Range(0L, 5L).asList();
        assertTrue(testingList.get(0).equals(0L) &&
                testingList.get(1).equals(1L) &&
                testingList.get(2).equals(2L) &&
                testingList.get(3).equals(3L) &&
                testingList.get(4).equals(4L) &&
                testingList.get(5).equals(5L));
    }

    @Test
    void testAsIterator() {
        Iterator<Long> testingIterator = new Range(5L, 10L).asIterator();
        boolean correct = true;
        long i = 5;
        while (testingIterator.hasNext()) {
            correct = correct && testingIterator.next().equals(i);
            i++;
        }
        assertTrue(correct);
    }

}
