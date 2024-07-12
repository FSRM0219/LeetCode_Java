import java.util.*;

public class Solution {
    public int maxArea(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
            ans = Math.max(ans, area);
        }
        return ans;
    }

    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int l = 0, r = 0;
        while (r < n) {
            if (nums[r] != 0) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
            }
            r++;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                ans[0] = map.get(target - nums[i]);
                ans[1] = i;
            } else {
                map.put(nums[i], i);
            }
        }
        return ans;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans;
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            map.computeIfAbsent(sorted, _ -> new ArrayList<>()).add(str);
        }
        ans = new ArrayList<>(map.values());
        return ans;
    }

    public int longestConsecutive(int[] nums) {
        int ans = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            int count = 0;
            if (set.contains(num)) {
                count++;
                set.remove(num);
                int curr = num - 1;
                while (set.contains(curr)) {
                    count++;
                    set.remove(curr);
                    curr--;
                }
                curr = num + 1;
                while (set.contains(curr)) {
                    count++;
                    set.remove(curr);
                    curr++;
                }
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
