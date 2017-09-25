package me.tiezhu.leetcode.quiz;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 *
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoArraysAs4 {
    // 当前算法，递归找第k个值, 66ms
    // 60ms算法，把小的nums放前边，减少切割次数
    // 55ms算法，merge之后再算中位数
    // 但是O(n)算法为啥比O(log(m+n))的快。。。猜测是因为集合不够大，函数调用耗费了时间，或许尾递归效果会更好
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return median(nums2);
        }
        if (nums2 == null || nums2.length == 0) {
            return median(nums1);
        }

        int totalLength = nums1.length + nums2.length;
        if ((totalLength & 0x1) == 1) {
            return findKth(totalLength / 2 + 1, nums1, 0, nums2, 0);
        } else {
            System.out.println("extra" + findKthTwoAverage(totalLength / 2, nums1, 0, nums2, 0));
            return (findKth(totalLength / 2, nums1, 0, nums2, 0) +
                    findKth(totalLength / 2 + 1, nums1, 0, nums2, 0)) / 2.0;
        }
    }

    // 每次比较各自前k/2个，舍弃小的那些
    public int findKth(int k, int[] nums1, int start1, int[] nums2, int start2) {
        if (start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }
        if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }
        if (k == 1) {
            return nums1[start1] < nums2[start2] ? nums1[start1] : nums2[start2];
        }

        int midPos1 = start1 + k / 2 - 1;
        int midPos2 = start2 + k / 2 - 1;

        int midVal1 = midPos1 < nums1.length ? nums1[midPos1] : Integer.MAX_VALUE;
        int midVal2 = midPos2 < nums2.length ? nums2[midPos2] : Integer.MAX_VALUE;

        if (midVal1 < midVal2) {
            return findKth(k - k/2, nums1, midPos1 + 1, nums2, start2);
        } else {
            return findKth(k - k/2, nums1, start1, nums2, midPos2 + 1);
        }
    }

    // 小优化一下，减少一次递归计算，但是效果一般，可见测试case真的不多。。。
    public double findKthTwoAverage(int k, int[] nums1, int start1, int[] nums2, int start2) {
        if (start1 >= nums1.length) {
            return (nums2[start2 + k - 1] + nums2[start2 + k]) / 2.0;
        }
        if (start2 >= nums2.length) {
            return (nums1[start1 + k - 1] + nums1[start1 + k]) / 2.0;
        }
        if (k == 1) {
            int val10 = nums1[start1];
            int val20 = nums2[start2];
            int val11 = start1 + 1 == nums1.length ? Integer.MAX_VALUE : nums1[start1 + 1];
            int val21 = start2 + 1 == nums2.length ? Integer.MAX_VALUE : nums2[start2 + 1];

            int val1 = val10 < val20 ? val10 : val20;
            int val2 = val10 > val20 ? val10 : val20;
            if (val11 < val20) {
                val2 = val11;
            }
            if (val21 < val10) {
                val2 = val21;
            }
            return (val1 + val2) / 2.0;
        }

        int midPos1 = start1 + k / 2 - 1;
        int midPos2 = start2 + k / 2 - 1;

        int midVal1 = midPos1 < nums1.length ? nums1[midPos1] : Integer.MAX_VALUE;
        int midVal2 = midPos2 < nums2.length ? nums2[midPos2] : Integer.MAX_VALUE;

        if (midVal1 < midVal2) {
            return findKthTwoAverage(k - k/2, nums1, midPos1 + 1, nums2, start2);
        } else {
            return findKthTwoAverage(k - k/2, nums1, start1, nums2, midPos2 + 1);
        }
    }

    public double median(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int odd  = nums.length & 0x1;
        if (odd == 1) {
            return nums[nums.length / 2];
        } else {
            return (nums[nums.length / 2] + nums[nums.length / 2 - 1]) / 2.0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MedianOfTwoArraysAs4().findMedianSortedArrays(null, new int[]{1,2,3}));
        System.out.println(new MedianOfTwoArraysAs4().findMedianSortedArrays(new int[]{1,2,3,4}, new int[0]));
        System.out.println(new MedianOfTwoArraysAs4().findMedianSortedArrays(new int[]{1}, new int[]{2}));
        System.out.println(new MedianOfTwoArraysAs4().findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        System.out.println(new MedianOfTwoArraysAs4().findMedianSortedArrays(new int[]{1,3}, new int[]{2,4}));
    }
}
