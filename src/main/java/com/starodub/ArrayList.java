package com.starodub;

public class ArrayList<T> {

    private static final int INITIAL_LENGTH = 16;
    private static final double LOAD_FACTOR = 0.25;
    private int size = 0;
    private int currentLength;

    private Object[] array;

    public ArrayList() {
        this.array = new Object[INITIAL_LENGTH];
        currentLength = INITIAL_LENGTH;
    }

    public int size() {
        return size;
    }

    public void add(T t){
        if(size + (size * LOAD_FACTOR) >= currentLength){
            increaseLength();
        }
        array[size] = t;
        size++;
    }

    public T get(int index){
        return (T) array[index];
    }

    public void remove(int index){
        System.arraycopy(array, index + 1, array, index, size);
        size--;
        if (size * LOAD_FACTOR <= currentLength) {
           decreaseLength();
        }
    }
    private void increaseLength() {
        currentLength = (int) (currentLength + currentLength * LOAD_FACTOR);
        Object[] newArray = new Object[currentLength];
        System.arraycopy(array, 0, newArray, 0, size);

        array = newArray;
    }
    private void decreaseLength() {
        currentLength /= LOAD_FACTOR;
        Object[] newArray = new Object[currentLength];
        System.arraycopy(array, 0, newArray, 0, size);

        array = newArray;
    }

    public static void main(String[] args) {
        ArrayList<Integer> listTest = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            listTest.add(i);
        }

        System.out.println(listTest.get(19));

    }
}
