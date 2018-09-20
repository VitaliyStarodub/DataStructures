package com.starodub;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

    private LinkedList<Integer> linkedList;

    @Before
    public void setUp() throws Exception {
        linkedList = new LinkedList<>();
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.add(40);
        linkedList.add(50);
        linkedList.add(60);
        linkedList.add(70);
    }

    @Test
    public void testAddingElemToArrayPositiveScenario() {
        Integer expectedResult = 10;
        Assert.assertEquals(expectedResult, linkedList.get(0));
    }

    @Test
    public void testAddingElemToArrayNegativeScenario() {
        Integer expectedResult = 20;
        Assert.assertNotEquals(expectedResult, linkedList.get(0));
    }

    @Test
    public void testSizeOfArrayPositiveScenario() {
        Integer expectedResult = 7;
        Assert.assertEquals(expectedResult, linkedList.size());
    }

    @Test
    public void testSizeOfArrayNegativeScenario() {
        Integer expectedResult = 6;
        Assert.assertNotEquals(expectedResult, linkedList.size());
    }

    @Test
    public void testGettingElemFromArrayPositiveScenario() {
        Integer expectedResult = 70;
        Assert.assertEquals(expectedResult, linkedList.get(linkedList.size() - 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGettingElemFromArrayNegativeScenario() {
        linkedList.get(100);
    }

    @Test
    public void testRemoveByIndexThatLastElementShifted() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        Integer expectedResult = 3;
        linkedList.remove(0);
        Assert.assertEquals(expectedResult, linkedList.get(0));

    }

    @Test
    public void testRemoveByValueThatLastElementShifted() {
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        Integer expectedResult = 3;
        linkedList.remove(Integer.valueOf(2));
        Assert.assertEquals(expectedResult, linkedList.get(1));
    }
}