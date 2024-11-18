package com.test.water;

import com.test.water.landscape.FloodRow;
import com.test.water.landscape.Landscape;
import org.junit.Test;

public class TestRunner {


    Landscape landscape = new Landscape();

    @Test(expected = IllegalArgumentException.class)
    public void testMaxPositionOverflow() {
        int[] data = new int[Landscape.MAX_POSITION + 1];
        landscape.calcAmount(data);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxHeightOverflow() {
        int[] data = new int[100];
        data[5] = Landscape.MAX_HEIGHT + 1;
        landscape.calcAmount(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxHeightUnderflow() {
        int[] data = new int[100];
        data[5] = -1;
        landscape.calcAmount(data);
    }

    @Test
    public void testMaxPosition() {
        int[] data = new int[Landscape.MAX_POSITION];
        data[2] = 1;
        data[4] = 1;
        assert landscape.calcAmount(data) == 1;
    }

    @Test
    public void testMaxHeight() {
        int[] data = new int[100];
        data[4] = Landscape.MAX_HEIGHT;
        data[2] = 2;
        assert landscape.calcAmount(data) == 2;
    }

    @Test
    public void testTestTask() {
        int[] data = {5, 2, 3, 4, 5, 4, 0, 3, 1};
        assert landscape.calcAmount(data) == 9;
    }

    @Test
    public void testTestEmpty() {
        int[] data = {0, 0, 0, 0, 0};
        assert landscape.calcAmount(data) == 0;
    }

    @Test
    public void testTestFull() {
        int[] data = {9, 9, 9, 9, 9};
        assert landscape.calcAmount(data) == 0;
    }

    @Test
    public void testSquare() {
        int[] data = {10, 0, 0, 0, 10};
        assert landscape.calcAmount(data) == 30;
    }


    @Test
    public void testTwoHill() {
        int[] data = {10, 0, 20, 0, 10};
        assert landscape.calcAmount(data) == 20;
    }

    @Test
    public void testFloodRow() {
        FloodRow row = new FloodRow(10, 5, 3);

        assert row.contain(10, 5);
        assert row.contain(9, 5);
        assert row.contain(8, 5);

        assert !row.contain(10, 6);
        assert !row.contain(11, 5);
        assert !row.contain(7, 5);
    }

    @Test
    public void testResultRow() {
        int[] data = new int[Landscape.MAX_POSITION];
        data[2] = 1;
        data[4] = 1;
        assert landscape.calcAmount(data) == 1;
        assert landscape.getFloodRows().size() == 1;
        assert landscape.getFloodRows().stream().findFirst().isPresent();

        FloodRow row = landscape.getFloodRows().stream().findFirst().get();
        assert row.size == 1;
        assert row.x == 3;
        assert row.y == 1;
    }

    @Test
    public void testResultLongRow() {
        int[] data = new int[102];
        data[0] = 1;
        data[101] = 1;
        assert landscape.calcAmount(data) == 100;
        assert landscape.getFloodRows().size() == 1;
        assert landscape.getFloodRows().stream().findFirst().isPresent();

        FloodRow row = landscape.getFloodRows().stream().findFirst().get();
        assert row.size == 100;
        assert row.x == 100;
        assert row.y == 1;
    }

}