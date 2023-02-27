package com.bii.lru.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author bihaiyang
 * @desc
 * @since 2023/02/27
 */
public class LRUCache {
    
    private Map<Integer, Node> map;
    
    private DoubleList doubleList;
    
    private int cap;
    
    public LRUCache(int cap){
        this.cap = cap;
        map = new HashMap();
        doubleList = new DoubleList();
    }
    
    
    public void remove(Node node){
        doubleList.remove(node);
        map.remove(node.getKey());
    }
    
    public void removeLast(){
        Node node = doubleList.removeLast();
        map.remove(node.getKey());
    }
    
    public void addFirst(Node node){
        doubleList.addFirst(node);
        map.put(node.getKey(), node);
    }
    
    
    public void put(int key, int val){
        Node node = new Node(key, val);
        if (map.containsKey(key)){
            remove(map.get(key));
        }else if (doubleList.getSize() >= cap){
            removeLast();
        }
        addFirst(node);
    }
    
    public Node get(int key){
        if (map.containsKey(key)){
            Node node = map.get(key);
            doubleList.addFirst(node);
            doubleList.remove(node);
            return node;
        }
        return null;
    }
    
    @Override
    public String toString(){
        return doubleList.toString();
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
