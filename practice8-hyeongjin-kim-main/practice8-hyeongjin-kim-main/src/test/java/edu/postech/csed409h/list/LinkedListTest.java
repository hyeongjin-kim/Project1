package edu.postech.csed409h.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    /* An empty linked list */
    LinkedList<Object> list;
    @BeforeEach
    public void setUp() {
        // TODO: implement this code
        list = new LinkedList<>();
    }


    @Test
    void addFirst() {
        // TODO: implement this code
        list.addFirst(1);
        assert list.size() == 1;
        assertEquals(1,list.getFirst());
        assertEquals(1,list.getLast());
        list.addFirst(2);
        assert list.size() == 2;
        assertEquals(2,list.getFirst());
        assertEquals(1,list.getLast());

    }

    @Test
    void addLast() {
        // TODO: implement this code
        list.addLast(1);
        assert list.size() == 1;
        assertEquals(1,list.getFirst());
        assertEquals(1,list.getLast());
        list.addLast(2);
        assert list.size() == 2;
        assertEquals(1,list.getFirst());
        assertEquals(2,list.getLast());
    }

    @Test
    void getFirst() {
        // TODO: implement this code
        try{
            list.getFirst();
        }catch(NoSuchElementException e){
            list.addFirst(1);
            list.addFirst(2);
        }
        assert list.size() == 2;
        assertEquals(2,list.getFirst());

    }

    @Test
    void getLast() {
        // TODO: implement this code
        try{
            list.getLast();
        }catch(NoSuchElementException e){
            list.addFirst(1);
            list.addFirst(2);
        }
        assert list.size() == 2;
        assertEquals(1,list.getLast());

    }

    @Test
    void get() {
        // TODO: implement this code
        try{
            list.get(1);
        }catch(IndexOutOfBoundsException e){
            list.addFirst(1);
            list.addFirst(2);
        }
        assert list.size() == 2;
        assertEquals(1,list.get(1));
    }

    @Test
    void removeFirst() {
        // TODO: implement this code
        try{
            list.removeFirst();
        }catch(NoSuchElementException e){
            list.addFirst(1);
            list.addFirst(2);
        }
        assert list.size() == 2;
        list.removeFirst();
        assertEquals(1, list.getFirst());
        assertEquals(1, list.getLast());
    }

    @Test
    void clear() {
        // TODO: implement this code
        list.addFirst(1);
        list.addFirst(2);
        assertEquals(2, list.size());
        list.clear();
        assert list.isEmpty();
    }

    @Test
    void size() {
        // TODO: implement this code
        assertEquals(0, list.size());
        list.addFirst(1);
        assertEquals(1, list.size());
        list.removeFirst();
        assertEquals(0, list.size());
    }

    @Test
    void isEmpty() {
        // TODO: implement this code\
        assert list.isEmpty();
        list.addFirst(1);
        assert !list.isEmpty();
    }

    @Test
    void testToString() {
        // TODO: implement this code
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
    }
}