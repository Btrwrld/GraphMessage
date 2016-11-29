package com.ce.datosi.GraphMessage.EstructurasDeDatos;


/**
 * Created by Chris on 04/11/2016.
 */

public class LinkedList<T> {
	
	

    public Node<T> head;
    protected Node<T> tail;
    public int size = 0;
   

    public LinkedList() {
        head = tail = null;
    }
    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(T info) {
        head = new Node<T>(info,head);
        if (tail == null) {
            tail = head;
            size++;
        }
        else{
            size++;
        }
    }
    public void add(T info) {
        if (!isEmpty()) {
            tail.next = new Node<T>(info);
            tail = tail.next;
            size++;
        }
        else {
            head = tail = new Node<T>(info);
            size++;
        }
    }


    public boolean remove(T element) {
        if (isEmpty() && head.info == element) {
            head = tail = null;
        }
        else {
            Node<T> trasCurrent = head;
            if (trasCurrent.info == element) {
                head.next = head;
            }
            else {
                Node<T> current;
                for (current = trasCurrent.next; current != tail; trasCurrent = current, current = current.next) {
                    if (current.info == element) {
                        trasCurrent.next = current.next;
                        size--;
                        break;
                    }
                }

                if (current == tail) {
                    return false;
                }

            }
        }
        return true;
    }

    public T deleteFirst() {
        T info = head.info;
        if (head == tail) {
            head = tail = null;
            size--;
        }
        else head = head.next;
        size--;
        return info;

    }
    public T deleteLast() {
        T info = tail.info;
        if (head == tail) {
            head = tail = null;
            size--;
        }
        else {
            Node<T> current;
            for (current = head; current.next != tail; current = current.next);
            tail = current;
            tail.next = null;
            size--;
        }
        return info;
    }

    public T get(int index) {
        Node<T> current = head;
        for ( int i = 0; i < index; i++){
            current = current.next;
        }
        return current.info;
    }

    public void setEmpty(){
        head = tail = null;
    }


}
