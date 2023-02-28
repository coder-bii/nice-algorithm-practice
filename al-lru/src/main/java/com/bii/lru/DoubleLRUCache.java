package com.bii.lru;

import com.bii.lru.demo.LRUCache;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author bihaiyang
 * @desc
 * @since 2023/02/28
 */
public class DoubleLRUCache {
    
    /**
     * 虚拟头、尾节点
     */
    Node head, tail;
    /**
     * 最大容量
     */
    int capacity;
    /**
     * cache 的size
     */
    int size;
    
    Map<Integer, Node> map;
    
    public DoubleLRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = tail;
        this.tail.pre = head;
        map = new HashMap();
    }
    
    /**
     * 每次查询都需要将node移到第一位
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = removeToHead(map.get(key));
        map.put(key, node);
        return node.getVal();
    }
    
    public void put(int key, int val) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.setVal(val);
            map.put(key, removeToHead(node));
        } else if (size >= capacity) {
            removeLast();
            map.put(key, putToHead(new Node(key, val)));
        } else {
            map.put(key, putToHead(new Node(key, val)));
            size ++;
        }
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
    
    private Node removeToHead(Node node) {
        remove(node);
        return putToHead(node);
    }
    
    private void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    
    private Node removeLast() {
        if (head.next == tail) {
            return null;
        }
        Node node = tail.pre;
        node.pre.next = tail;
        tail.pre = node.pre;
        return node;
    }
    
    private Node putToHead(Node node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
        return node;
    }
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        LRUCache lruCache = new LRUCache(4);
        while (true){
            int key = scanner.nextInt();
            if (key == -1) break;
            int val = scanner.nextInt();
            lruCache.put(key, val);
            System.out.println(lruCache);
        }
    }
    
}


class Node {
    
    Node pre, next;
    int key, val;
    
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
    
    public Node() {
    }
    
    public int getKey() {
        return this.key;
    }
    
    public int getVal() {
        return this.val;
    }
    
    public void setVal(int val) {
        this.val = val;
    }
}