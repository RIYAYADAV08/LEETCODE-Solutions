class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> ans = new ArrayList<>();
        if (!dict.contains(endWord)) return ans;
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> level = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        level.put(beginWord, 0);
        while (!q.isEmpty()) {
            String word = q.poll();
            int currLevel = level.get(word);
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char old = arr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    arr[i] = c;
                    String next = new String(arr);
                    if (!dict.contains(next)) continue;
                    if (!level.containsKey(next)) {
                        level.put(next, currLevel + 1);
                        q.offer(next);
                        map.putIfAbsent(next, new ArrayList<>());
                        map.get(next).add(word);
                    } else if (level.get(next) == currLevel + 1) {
                        map.get(next).add(word);
                    }
                }
                arr[i] = old;
            }
        }
        if (!level.containsKey(endWord)) return ans;
        List<String> path = new ArrayList<>();
        dfs(endWord, beginWord, map, path, ans);
        return ans;
    }
    private void dfs(String word, String beginWord, Map<String, List<String>> map, List<String> path, List<List<String>> ans) {
        path.add(word);
        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            ans.add(temp);
        } else {
            if (map.containsKey(word)) {
                for (String prev : map.get(word)) {
                    dfs(prev, beginWord, map, path, ans);
                }
            }
        }
        path.remove(path.size() - 1);
    }
}