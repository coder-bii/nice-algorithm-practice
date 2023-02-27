package com.bii.lru.demo;

/**
 * @author bihaiyang
 * @desc
 * @since 2023/02/26
 */
public class DoubleList {
    
    /**
     * 头节点、尾节点 head next指向第一个节点头节点 tail pre
     */
    Node head, tail;
    
    private int size;
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    
    
    public DoubleList() {
        size = 0;
        head = Node.emptyNode();
        tail = Node.emptyNode();
        head.next = tail;
        tail.pre = head;
    }
    
    public Node getFirst() {
        return head.next;
    }
    
    public Node getLast() {
        return tail.pre;
    }
    
    public Node addFirst(Node node) {
        head.next.pre = node;
        node.next = head.next;
        node.pre = head;
        head.next = node;
        size ++ ;
        return node;
    }
    
    public Node addLast(Node node) {
        tail.pre.next = node;
        node.pre = tail.pre;
        tail.pre = node;
        size ++;
        return node;
    }
    
    public Node remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.next = null;
        node.pre = null;
        size --;
        return node;
    }
    
    public Node removeLast() {
        if (tail.pre == head){
            return null;
        }
        Node last = tail.pre;
        last.pre.next = tail;
        tail.pre = last.pre;
        last.pre = null;
        last.next = null;
        size --;
        return last;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = head.next;
        while (node != null){
            sb.append(" ( ");
            sb.append(node.key);
            sb.append(" : ");
            sb.append(node.val);
            sb.append(" )   ; ");
            node = node.next;
        }
        return sb.toString();
    }
}

/**
 * 双链表
 */
class Node {
    
    Node pre, next;
    int key, val;
    
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
    
    private Node() {
        this.key = key;
        this.val = val;
    }
    
    public static Node emptyNode() {
        return new Node();
    }
    
    public Node getPre() {
        return pre;
    }
    
    public void setPre(Node pre) {
        this.pre = pre;
    }
    
    public Node getNext() {
        return next;
    }
    
    public void setNext(Node next) {
        this.next = next;
    }
    
    public int getKey() {
        return key;
    }
    
    public int getVal() {
        return val;
    }
    
    
}