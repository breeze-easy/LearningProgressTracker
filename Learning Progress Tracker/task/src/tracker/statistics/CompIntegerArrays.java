package tracker.statistics;

import java.util.Comparator;

public class CompIntegerArrays implements Comparator<Integer[]> {
    int index; //
    boolean isDescending;

    public CompIntegerArrays(int index, boolean isDescending) {
        this.index = index;
        this.isDescending = isDescending;
    }

    @Override
    public int compare(Integer[] o1, Integer[] o2) {
        if(isDescending) return o2[index]-o1[index];
        return o1[index]-o2[index];
    }
}
