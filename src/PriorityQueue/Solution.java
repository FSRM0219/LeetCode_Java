package PriorityQueue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> dict = new HashMap<>();
        for (int num : nums) {
            dict.put(num, dict.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(m -> m[1]));
        for (Map.Entry<Integer, Integer> entry : dict.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (minHeap.size() == k) {
                if (minHeap.peek()[1] < count) {
                    minHeap.poll();
                    minHeap.offer(new int[]{num, count});
                } else {
                    minHeap.offer(new int[]{num, count});
                }
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = minHeap.poll()[0];
        }
        return res;
    }
}
