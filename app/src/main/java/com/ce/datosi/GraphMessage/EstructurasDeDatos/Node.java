package com.ce.datosi.GraphMessage.EstructurasDeDatos;

/**
 * Created by ggg on 04/11/2016.
 */

public class Node<T> {

    public T info;
    public Node<T> next;
    public Node(T i) {
        this(i, null);
    }
    public Node(T i, Node<T> n) {
    	super();
        info = i; next = n;
    }
}
