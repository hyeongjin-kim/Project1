package edu.postech.csed409h.list;

import java.util.NoSuchElementException;

/**
 * A singly linked list.
 *
 */
public class LinkedList<T> {
    private ListElement<T> first;   // First element in list.
    private ListElement<T> last;    // Last element in list.
    private int size;               // Number of elements in list.

    /**
     * A list element.
     */
    private static class ListElement<T> {
        public T data;
        public ListElement<T> next;

        public ListElement(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Creates an empty list.
     */
    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Inserts the given element at the beginning of this list.
     *
     * @param element An element to insert into the list.
     */
    public void addFirst(T element) {
        ListElement<T> firstElement = new ListElement<T>(element);
        if (this.size == 0){
            this.first = firstElement;
            this.last = firstElement;
        }
        else{
            firstElement.next = this.first;
            this.first = firstElement;
        }
        this.size ++;
    }

    /**
     * Inserts the given element at the end of this list.
     *
     * @param element An element to insert into the list.
     */
    public void addLast(T element) {
        ListElement<T> lastElement = new ListElement<T>(element);
        if(this.size ==0){
            this.first = lastElement;
        }
        else{
            this.last.next = lastElement;
        }
        this.last = lastElement;
        this.size ++;
    }

    /**
     * @return The head of the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public T getFirst() {
        if (this.first != null){
            return this.first.data;
        }
        else{
            throw new NoSuchElementException();
        }
    }

    /**
     * @return The tail of the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public T getLast() {
        if(this.last != null){
            return this.last.data;
        }
        else{
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns an element from a specified index.
     *
     * @param index A list index.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public T get(int index) {
        if(index < 0|| index >= this.size){
            throw new IndexOutOfBoundsException();
        }
        else{
            ListElement<T>element = this.first;
            for(int i = 0; i < index; i++){
                element = element.next;
            }
            return element.data;
        }
    }

    /**
     * Removes the first element from the list.
     *
     * @return The removed element.
     * @throws NoSuchElementException if the list is empty.
     */
    public T removeFirst() {
        if(this.first != null || this.size != 0){
            ListElement<T> list = this.first;
            this.first = first.next;
            size --;
            return list.data;
        }
        else{
            throw new NoSuchElementException();
        }
    }

    /**
     * Removes all of the elements from the list.
     */
    public void clear() {
        this.first = null;
        this.last = null;
        this.size =0;
    }

    /**
     * @return The number of elements in the list.
     */
    public int size() {
        return this.size;
    }

    /**
     * Note that by definition, the list is empty if both first and last
     * are null, regardless of what value the size field holds (it should
     * be 0, otherwise something is wrong).
     *
     * @return <code>true</code> if this list contains no elements.
     */
    public boolean isEmpty() {
        return first == null && last == null;
    }

    /**
     * Creates a string representation of this list. The string
     * representation consists of a list of the elements enclosed in
     * square brackets ("[]"). Adjacent elements are separated by the
     * characters ", " (comma and space). Elements are converted to
     * strings by the method toString() inherited from Object.
     *
     * Examples:
     *  "[1, 4, 2, 3, 44]"
     *  "[]"
     *
     * @return A string representing the list.
     */
    public String toString() {
        ListElement<T> listofelements = this.first;
        String returnString = "[";
        while(listofelements != null) {
            returnString += listofelements.data;
            if(listofelements.next != null){
                returnString += ", ";
            }
            listofelements = listofelements.next;
        }
        returnString += "]";
        return returnString;
    }
}