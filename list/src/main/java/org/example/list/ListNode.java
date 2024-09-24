package org.example.list;

public class ListNode<T extends ListElement> {
    private T data;
    public ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public ListNode<T> next(){
        return this.next;
    }
}
