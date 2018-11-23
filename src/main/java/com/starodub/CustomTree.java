package com.starodub;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    private Entry<String> root;

    public CustomTree() {
        root = new Entry<>("0");
    }

    public String getParent(String s) {
        String nameParent = null;
        List<Entry<String>> intQueue = new ArrayList<>();
        intQueue.add(root);

        while (!intQueue.isEmpty()) {
            Entry<String> current = intQueue.remove(0);
            if (current.leftChild != null) {
                if (s.equals(current.leftChild.elementName)) return current.elementName;
                intQueue.add(current.leftChild);
            }

            if (current.rightChild != null) {
                if (s.equals(current.rightChild.elementName)) return current.elementName;
                intQueue.add(current.rightChild);
            }
        }
        return nameParent;
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof String) {
            boolean deleteElement = false;
            List<Entry<String>> intQueue = new ArrayList<Entry<String>>();
            intQueue.add(root);

            while (!intQueue.isEmpty()) {
                Entry<String> current = intQueue.remove(0);
                if (current.leftChild != null) {
                    if (o.equals(current.leftChild.elementName)) {
                        current.leftChild = null;
                        deleteElement = true;
                        break;
                    }
                    intQueue.add(current.leftChild);
                }
                if (current.rightChild != null) {
                    if (o.equals(current.rightChild.elementName)) {
                        current.rightChild = null;
                        deleteElement = true;
                        break;
                    }
                    intQueue.add(current.rightChild);
                }
            }
            boolean addElement = false;
            intQueue = new ArrayList<>();
            intQueue.add(root);

            while (!intQueue.isEmpty()) {
                Entry<String> current = intQueue.remove(0);

                if (current.availableToAddLeftChildren) {
                    addElement = true;
                    break;
                } else if (current.leftChild != null) {
                    intQueue.add(current.leftChild);
                }

                if (current.availableToAddRightChildren) {
                    addElement = true;
                    break;
                } else if (current.rightChild != null) {
                    intQueue.add(current.rightChild);
                }
            }
            return deleteElement;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }


    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String o) {
        boolean addElement = false;
        List<Entry<String>> intQueueNull = new ArrayList<>();
        List<Entry<String>> intQueue = new ArrayList<>();
        intQueue.add(root);

        while (!intQueue.isEmpty()) {
            Entry<String> current = intQueue.remove(0);

            if (current.availableToAddLeftChildren) {
                current.leftChild = new Entry<String>((String) o);
                current.checkChildren();
                addElement = true;
                break;
            } else if (current.leftChild != null) {
                intQueue.add(current.leftChild);
            }

            if (current.availableToAddRightChildren) {
                current.rightChild = new Entry<String>((String) o);
                current.checkChildren();
                addElement = true;
                break;
            } else if (current.rightChild != null) {
                intQueue.add(current.rightChild);
            }
            if (current.rightChild == null && current.leftChild == null)
                intQueueNull.add(current);
        }

        if (!addElement) {
            Entry<String> newElement = new Entry<String>((String) o);

            while (!intQueueNull.isEmpty()) {
                Entry<String> current = intQueueNull.remove(0);

                if (current.leftChild == null) {
                    if (newElement == null) current.availableToAddLeftChildren = true;
                    current.leftChild = newElement;
                    newElement = null;
                }

                if (current.rightChild == null) {
                    if (newElement == null) current.availableToAddRightChildren = true;
                    current.rightChild = newElement;
                    newElement = null;
                }
            }
        }
        return true;
    }


    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int size = -1;
        List<Entry<String>> intQueue = new ArrayList<>();
        intQueue.add(root);

        while (!intQueue.isEmpty()) {
            Entry<String> current = intQueue.remove(0);
            size++;
            if (current.leftChild != null) {
                intQueue.add(current.leftChild);
            }

            if (current.rightChild != null) {
                intQueue.add(current.rightChild);
            }
        }
        return size;
    }

    static class Entry<T> implements Serializable {
        private String elementName;
        private int lineNumber;
        private boolean availableToAddLeftChildren, availableToAddRightChildren;
        private Entry<T> parent;
        private Entry<T> leftChild;
        private Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

    public static void main(String[] args) {
        List<String> list = new CustomTree();

        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }

        System.out.println("The list size is " + list.size());
        System.out.println("The expected parent is 3. The actual parent is " + ((CustomTree) list).getParent("8"));
        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("20"));

        list.remove("3");
        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("8"));

        list.add("16");
        System.out.println("The expected parent is 9. The actual parent is " + ((CustomTree) list).getParent("16"));

        list.remove("4");
        list.remove("5");
        list.remove("6");
        System.out.println("Expected: true. Actual: " + list.add("20"));
        System.out.println("The expected parent is 1. The actual parent is " + ((CustomTree) list).getParent("20"));
    }
}
