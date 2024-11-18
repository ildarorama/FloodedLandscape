package com.test.water.landscape;

import java.util.HashSet;
import java.util.Set;

public class Landscape {
    public final static int MAX_HEIGHT = 32000;
    public final static int MAX_POSITION = 32000;

    private final Set<FloodRow> floodRows = new HashSet<>();
    private int maxHeight = 0;

    public Set<FloodRow> getFloodRows() {
        return floodRows;
    }


    private int getMaxValue(int[] a) {
        int max = 0;
        if (a.length > MAX_POSITION) {
            throw new IllegalArgumentException("Array size greater than " + MAX_POSITION);
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0 || a[i] > MAX_HEIGHT) {
                throw new IllegalArgumentException("Some element in array exceeded MAX_HEIGHT or less then 0: index=" + i + ", value=" + a[i]);
            }
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public long calcAmount(int[] data) {
        maxHeight = getMaxValue(data);
        int sum[] = new int[maxHeight];
        int prev = 0;
        long calc = 0;
        floodRows.clear();

        for (int i = 0; i < data.length; i++) {
            int current = data[i];

            if (current < prev) {
                while (current < prev) {
                    sum[current]++;
                    current++;
                }
            }
            if (current > prev) {
                int j = prev;
                while (j < current && sum[j] > 0) {
                    calc += sum[j];
                    floodRows.add(new FloodRow(i - 1, j + 1, sum[j]));
                    sum[j] = 0;
                    j++;
                }
            }
            while (current < sum.length && sum[current] > 0) {
                sum[current]++;
                current++;
            }
            prev = data[i];
        }
        return calc;
    }
}
