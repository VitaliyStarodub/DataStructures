package com.starodub;

import java.util.Objects;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

   /* public LinkedList() {
        Node<T> node = new Node<>();
        node.isHead = true;
        this.head = node;
        this.tail = node;
    }*/

    public void add(T t) {
        Node<T> node = new Node<>(t);
        addNode(node);
        size++;
    }

    public Integer size() {
        return size;
    }

    public T get(int index) {
        return getNode(index).value;
    }

    private Node<T> getNode(int index) {
        if(index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException("");
        }
        Node<T> node = null;
        if (index > size / 2) {
            node = getElementFromTail(size - (index + 1));
        } else {
            node = getElementFromHead(index);
        }
        return node;
    }

    private Node<T> getElementFromHead (int index) {

        Node<T> node = head;
        for(int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    private Node<T> getElementFromTail (int index) {
        Node<T> node = tail;
        for(int i = 0; i < index; i++) {
            node = node.prev;
        }
        return node;
    }

    private void addNode(Node<T> node) {
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public void remove(T t) {
        Node<T> node = getNodeByValue(t);
        removeNode(node);
        size--;
    }

    private Node<T> getNodeByValue(T t) {
        Node<T> node = head;
        Node<T> result = null;
        for(int i = 0; i < size; i++){
            if(node.value == t) {
               result = node;
               break;
            }
            node = node.next;
        }
        return result;
    }

    public void remove(int index) {
        Node<T> node = getNode(index);
        removeNode(node);
        size--;
    }

    private void removeNode(Node<T> node) {
        node.prev.next = node.next; // Node prevNode = node.prev.next
        node.next.prev = node.prev; // Node nextNode = node.next.prev
    }

    class Node<T> {

        private T value;
        private Node<T> next;
        private Node<T> prev;
        //private boolean isHead;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value) &&
                    Objects.equals(next, node.next) &&
                    Objects.equals(prev, node.prev);
        }

        @Override
        public int hashCode() {

            return Objects.hash(value, next, prev);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        list.remove(1);
        System.out.println(list.get(1));

        String s = "jfdlf";
        System.out.println(s.charAt(1));
    }
}
