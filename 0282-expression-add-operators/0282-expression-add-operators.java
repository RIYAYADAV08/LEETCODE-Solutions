import java.util.*;

class Solution {
    List<String> ans = new ArrayList<>();

    public List<String> addOperators(String num, int target) {
        solve(num, target, 0, 0, "", 0);
        return ans;
    }

    void solve(String num, int target, int index,
               long value, String expression, long last) {

        if (index == num.length()) {
            if (value == target) {
                ans.add(expression);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {

            // Leading zero avoid karo
            if (i > index && num.charAt(index) == '0') {
                break;
            }

            String part = num.substring(index, i + 1);
            long current = Long.parseLong(part);

            if (index == 0) {
                solve(num, target, i + 1,
                     current, part, current);
            } else {

                // +
                solve(num, target, i + 1,
                     value + current,
                     expression + "+" + part,
                     current);

                // -
                solve(num, target, i + 1,
                     value - current,
                     expression + "-" + part,
                     -current);

                // *
                solve(num, target, i + 1,
                     value - last + last * current,
                     expression + "*" + part,
                     last * current);
            }
        }
    }
}