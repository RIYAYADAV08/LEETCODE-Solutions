class Solution {

    Map<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {

        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {

            char ch = expression.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*') {

                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

                for (int a : left) {
                    for (int b : right) {

                        if (ch == '+') {
                            ans.add(a + b);
                        } else if (ch == '-') {
                            ans.add(a - b);
                        } else {
                            ans.add(a * b);
                        }
                    }
                }
            }
        }

        // Base case: only a number
        if (ans.isEmpty()) {
            ans.add(Integer.parseInt(expression));
        }

        memo.put(expression, ans);
        return ans;
    }
}