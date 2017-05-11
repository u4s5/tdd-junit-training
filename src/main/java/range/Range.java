package range;

import java.util.Iterator;
import java.util.List;

public class Range {

    public Range(double start, double end) {

    }

    boolean isBefore(Range otherRange) {
        return false;
    }

    boolean isAfter(Range otherRange) {
        return false;
    }

    boolean isConcurrent(Range otherRange) {
        return false;
    }

    long getLowerBound() {
        return 0L;
    }

    long getUpperBound() {
        return 0L;
    }

    boolean contains(long value) {
        return false;
    }

    List<Long> asList() {
        return null;
    }

    Iterator<Long> asIterator() {
        return null;
    }

}
