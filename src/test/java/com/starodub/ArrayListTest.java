package com.starodub;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {

    private ArrayList<Integer> arrayList;
    private ArrayList<Integer> arrayListForAdding;

    @Before
    public void setUp() throws Exception {
        arrayList = new ArrayList<>();
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(50);
        arrayList.add(60);
        arrayList.add(70);
    }

    @Test
    public void testAddElemToArrayPositiveScenario() {
        arrayListForAdding = new ArrayList<>();

        arrayListForAdding.add(11);
        arrayListForAdding.add(12);
        arrayListForAdding.add(13);
        arrayListForAdding.add(14);
        arrayListForAdding.add(15);

        Integer expectedResult = 15;
        Assert.assertEquals(expectedResult, arrayListForAdding.get(4));
    }

    @Test
    public void testAddElemToArrayNegativeScenario() {
        Integer expectedResult = 20;
        Assert.assertNotEquals(expectedResult, arrayList.get(0));
    }

    @Test
    public void testGetElemFromArrayPositiveScenario() {
        Integer expectedResult = 70;
        Assert.assertEquals(expectedResult, arrayList.get(arrayList.size() - 1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetElemFromArrayNegativeScenario() {
        arrayList.get(100);
    }

    @Test
    public void testRemoveElemFromArrayPositiveScenario() {

        arrayList.remove(0);

        Integer expectedResult = 20;
        Assert.assertEquals(expectedResult, arrayList.get(0));
    }

    @Test
    public void testRemoveElemFromArrayNegativeScenario() {

        arrayList.remove(0);

        Integer expectedResult = 30;
        Assert.assertNotEquals(expectedResult, arrayList.get(0));
    }

}