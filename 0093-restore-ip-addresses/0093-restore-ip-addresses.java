import java.util.*;

class Solution {

    List<String> ans = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0, 0, "");
        return ans;
    }

    private void backtrack(String s, int index, int parts, String curr) {

        if (parts == 4 && index == s.length()) {
            ans.add(curr.substring(0, curr.length() - 1));
            return;
        }

        if (parts == 4 || index == s.length())
            return;

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {

            String part = s.substring(index, index + len);

            if (part.length() > 1 && part.charAt(0) == '0')
                break;

            int num = Integer.parseInt(part);

            if (num <= 255) {
                backtrack(s, index + len, parts + 1, curr + part + ".");
            }
        }
    }
}