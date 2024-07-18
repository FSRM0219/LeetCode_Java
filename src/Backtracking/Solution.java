package Backtracking;

import java.util.*;

public class Solution {
    private String s;
    private final List<String> path = new ArrayList<>();
    private final List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        this.s = s;
        dfs(0);
        return ans;
    }

    public boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public void dfs(int i) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(i, j)) {
                path.add(s.substring(i, j + 1));
                dfs(j + 1);
                path.removeLast();
            }
        }
    }
}
