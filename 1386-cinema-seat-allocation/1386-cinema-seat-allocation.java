import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        // Store reserved seats row-wise
        for (int[] seat : reservedSeats) {
            map.putIfAbsent(seat[0], new HashSet<>());
            map.get(seat[0]).add(seat[1]);
        }

        // Rows without any reservation can have 2 groups
        int answer = (n - map.size()) * 2;

        // Check only rows having reservations
        for (HashSet<Integer> seats : map.values()) {

            boolean left = true;    // 2,3,4,5
            boolean middle = true;  // 4,5,6,7
            boolean right = true;   // 6,7,8,9

            for (int i = 2; i <= 5; i++) {
                if (seats.contains(i)) {
                    left = false;
                    break;
                }
            }

            for (int i = 4; i <= 7; i++) {
                if (seats.contains(i)) {
                    middle = false;
                    break;
                }
            }

            for (int i = 6; i <= 9; i++) {
                if (seats.contains(i)) {
                    right = false;
                    break;
                }
            }

            if (left && right) {
                answer += 2;
            } else if (left || middle || right) {
                answer += 1;
            }
        }

        return answer;
    }
}