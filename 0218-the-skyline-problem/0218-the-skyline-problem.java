class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]}); // building starts
            events.add(new int[]{b[1], b[2]});  // building ends
        }

        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        List<List<Integer>> ans = new ArrayList<>();

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);

        int prevHeight = 0;

        for (int[] event : events) {
            int x = event[0];
            int h = event[1];

            if (h < 0) {
                // Start of building
                map.put(-h, map.getOrDefault(-h, 0) + 1);
            } else {
                // End of building
                map.put(h, map.get(h) - 1);
                if (map.get(h) == 0) {
                    map.remove(h);
                }
            }

            int currHeight = map.lastKey();

            if (currHeight != prevHeight) {
                ans.add(Arrays.asList(x, currHeight));
                prevHeight = currHeight;
            }
        }

        return ans;
    }
}