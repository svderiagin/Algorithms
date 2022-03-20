package Algorithm;

import java.util.HashSet;

public class TwoSum {
    public static int[] loop(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == k) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        return new int[0];
    }

    public static int[] hashSet(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(k - nums[i])) {
                return new int[]{k - nums[i], nums[i]};
            } else set.add(nums[i]);
        }
        return new int[0];
    }

    public static int[] binarySearch(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int numToFind = k - nums[i];
            int l = i + 1;
            int r = nums.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] == numToFind) return new int[]{nums[i], nums[mid]};
                if (numToFind < nums[mid]) r = mid - 1;
                else l = mid + 1;
            }
        }
        return new int[0];
    }

    public static int[] twoPointers(int[] nums, int k) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum == k) return new int[]{nums[l], nums[r]};
            if (sum < k) l++; else r--;
        }
        return new int[0];
    }

    public static int[] twoPointersClosestValues(int[] nums, int k) {
        int[] result = new int[0];
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum == k) return new int[]{nums[l], nums[r]};
            result = new int[]{nums[l], nums[r]};
            if (sum < k) l++; else r--;
        }
        return result;
    }
}
