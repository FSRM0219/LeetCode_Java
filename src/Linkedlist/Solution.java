package Linkedlist;

import java.util.*;

public class Solution {


    public ListNode mergeKLists(ListNode[] lists) {
        // 合并k个升序链表
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {

            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            prev.next = new ListNode(node.val);
            prev = prev.next;
            if (node.next != null) {
                minHeap.add(node.next);
            }
        }
        return dummy.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // k个一组翻转链表
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p0 = dummy;
        while (count >= k) {
            count -= k;
            ListNode pre = null, curr = p0.next;
            for (int i = 0; i < k; i++) {
                ListNode nxt = curr.next;
                curr.next = pre;
                pre = curr;
                curr = nxt;
            }
            ListNode nxt = p0.next;
            p0.next.next = curr;
            p0.next = pre;
            p0 = nxt;
        }
        return dummy.next;
    }

    public Node copyRandomList(Node head) {
        // 随机链表的复制
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        Node pre = head;
        while (pre != null) {
            map.get(pre).next = map.get(pre.next);
            map.get(pre).random = map.get(pre.random);
            pre = pre.next;
        }
        return map.get(head);
    }
}

class LRUCache {
    // LRU
    private static class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Node dummy = new Node(0, 0);
    private final HashMap<Integer, Node> keyToNode = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.prev = dummy;
        dummy.next = dummy;
    }

    public int get(int key) {
        Node node = getNode(key);
        return node == null ? -1 : node.value;
    }

    public void put(int key, int value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
            return;
        }
        node = new Node(key, value);
        keyToNode.put(key, node);
        pushFront(node);
        if (keyToNode.size() > capacity) {
            Node last = dummy.prev;
            removeNode(last);
            keyToNode.remove(last.key);
        }
    }

    private Node getNode(int key) {
        if (!keyToNode.containsKey(key)) {
            return null;
        }
        Node node = keyToNode.get(key);
        removeNode(node);
        pushFront(node);
        return node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void pushFront(Node node) {
        node.prev = dummy;
        node.next = dummy.next;
        node.prev.next = node;
        node.next.prev = node;
    }
}