import java.util.*;

public class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(p -> p[0]));
        List<int[]> ans = new ArrayList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int[] interval : intervals) {
            if (interval[0] <= right) {
                right = Math.max(right, interval[1]);
            } else {
                ans.add(new int[]{left, right});
                left = interval[0];
                right = interval[1];
            }
        }
        ans.add(new int[]{left, right});
        return ans.toArray(new int[ans.size()][]);
    }

    public int maxSubArray(int[] nums) {
        int preSum = nums[0];
        int ans = preSum;
        for (int i = 1; i < nums.length; i++) {
            preSum = Math.max(preSum + nums[i], nums[i]);
            ans = Math.max(ans, preSum);
        }
        return ans;
    }

    public String minWindow(String s, String t) {
        int[] cntS = new int[128], cntT = new int[128];
        int ls = s.length();
        int tt = t.length();
        for (int i = 0; i < tt; i++) {
            cntT[t.charAt(i) - 'A']++;
        }
        int ansl = -1, ansr = ls;
        int left = 0;
        for (int right = 0; right < ls; right++) {
            cntS[s.charAt(right) - 'A']++;
            while (isCovered(cntS, cntT)) {
                if (right - left < ansr - ansl) {
                    ansr = right;
                    ansl = left;
                }
                cntS[s.charAt(left) - 'A']--;
                left++;
            }
        }
        if (ansl < 0) {
            return "";
        }
        return s.substring(ansl, ansr + 1);
    }

    private boolean isCovered(int[] cntS, int[] cntT) {
        for (int i = 0; i < 128; i++) {
            if (cntS[i] < cntT[i]) {
                return false;
            }
        }
        return true;
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!list.isEmpty() && nums[i] > nums[list.getLast()]) {
                list.removeLast();
            }
            list.add(i);
            if (i - list.getFirst() + 1 > k) {
                list.removeFirst();
            }
            if (i + 1 >= k) {
                ans[i - k + 1] = nums[list.getFirst()];
            }
        }
        return ans;
    }

    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int preSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            preSum += num;
            if (map.containsKey(preSum - k) && map.get(preSum - k) > 0) {
                result += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return result;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();
        if (sLen < pLen) {
            return new ArrayList<>();
        }
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; i++) {
            sCount[s.charAt(i) - 'a']++;
            pCount[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sCount, pCount)) {
            res.add(0);
        }
        for (int i = 0; i < sLen - pLen; i++) {
            sCount[s.charAt(i) - 'a']--;
            sCount[s.charAt(i + pLen) - 'a']++;
            if (Arrays.equals(sCount, pCount)) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0;
        int ans = 0;
        HashSet<Character> set = new HashSet<>();
        for (int right = 0; right < n; right++) {
            while (left < right && set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            set.add(s.charAt(right));
        }
        return ans;
    }

    public int trap(int[] height) {
        int ans = 0;
        int n = height.length;
        int[] preMax = new int[n];
        int[] folMax = new int[n];
        preMax[0] = height[0];
        folMax[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], height[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            folMax[i] = Math.max(folMax[i + 1], height[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            ans += Math.min(preMax[i], folMax[i]) - height[i];
        }
        return ans;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = len - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    int cur = j++;
                    while (j < k && nums[j] == nums[cur]) {
                        j++;
                    }
                    cur = k--;
                    while (k > j && nums[k] == nums[cur]) {
                        k--;
                    }
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }

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
