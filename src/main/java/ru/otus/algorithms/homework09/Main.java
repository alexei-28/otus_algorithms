package ru.otus.algorithms.homework09;

import java.util.Optional;

public class Main {

    public static void main(String... arg) {
        System.out.println("Hello, OTUS Algorithms!\nJDK: " + System.getProperty("java.version"));
        HashTableChain hashTableChain = new HashTableChain();

        Node node = new Node();
        node.setKey("act"); // 1
        node.setValue(130);
        hashTableChain.put(node);

        Node node2 = new Node();
        node2.setKey("bat"); // 1
        node2.setValue(170);
        hashTableChain.put(node2);

        Node node3 = new Node();
        node3.setKey("cat");// 2
        node3.setValue(120);
        hashTableChain.put(node3);

        Node node4 = new Node();
        node4.setKey("dog"); // 4
        node4.setValue(150);
        hashTableChain.put(node4);

        Node node5 = new Node();
        node5.setKey("mouse"); // 3
        node5.setValue(170);
        hashTableChain.put(node5);

        Node node6 = new Node();
        node6.setKey("tiger"); // 4
        node6.setValue(180);
        hashTableChain.put(node6);

        Node node7 = new Node();
        node7.setKey("bird"); // 2
        node7.setValue(200);
        hashTableChain.put(node7);

        Node node8 = new Node();
        node8.setKey("drib"); // 2
        node8.setValue(201);
        hashTableChain.put(node8);


        Node node9 = new Node();
        node9.setKey("ibrd");
        node9.setValue(110);
        hashTableChain.put(node9);


        Node node10 = new Node();
        node10.setKey("bbbb");
        node10.setValue(111);
        hashTableChain.put(node10);


        Node node11 = new Node();
        node11.setKey("bbbc");
        node11.setValue(112);
        hashTableChain.put(node11);

        Node node12 = new Node();
        node12.setKey("bbcb");
        node12.setValue(212);
        hashTableChain.put(node12);

        Node node13 = new Node();
        node13.setKey("abcd");
        node13.setValue(312);
        hashTableChain.put(node13);

        Node node14 = new Node();
        node14.setKey("dabcc");
        node14.setValue(412);
        hashTableChain.put(node14);


        Node node15 = new Node();
        node15.setKey("abdc");
        node15.setValue(512);
        hashTableChain.put(node15);

        Optional<Node> findNode = hashTableChain.get("abdc");
        if (findNode.isPresent()) {
            System.out.println("Success, findNode = " + findNode.get());
        } else {
            System.out.println("Not_found, findNode = " + findNode);
        }

    }
}
