import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> ans = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int k = 0; k < size; k++) {

                String current = queue.poll();

                if (isValid(current)) {
                    ans.add(current);
                    found = true;
                }

                // Agar valid mil gaya, is level ke baad
                // aur parentheses remove nahi karne
                if (found) {
                    continue;
                }

                for (int i = 0; i < current.length(); i++) {

                    // Sirf parentheses remove karo
                    if (current.charAt(i) != '(' &&
                        current.charAt(i) != ')') {
                        continue;
                    }

                    String next =
                        current.substring(0, i) +
                        current.substring(i + 1);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            if (found) {
                break;
            }
        }

        return ans;
    }

    private boolean isValid(String s) {

        int balance = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                balance++;
            } 
            else if (c == ')') {
                balance--;
            }

            // ')' ke liye matching '(' nahi hai
            if (balance < 0) {
                return false;
            }
        }

        return balance == 0;
    }
}