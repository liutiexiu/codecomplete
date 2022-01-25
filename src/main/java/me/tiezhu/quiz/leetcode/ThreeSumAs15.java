package me.tiezhu.quiz.leetcode;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Given an array S of n integers, are there elements a, b, c in S
 * such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note: The solution set must not contain duplicate triplets.
 *
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *  [-1, 0, 1],
 *  [-1, -1, 2]
 * ]
 */
public class ThreeSumAs15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }

        Set<ResultTuple> result = new LinkedHashSet<>();
        Map<Integer, List<ValueAndIndex>> index = ValueAndIndex.buildIndex(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (j == i) {
                    continue;
                }
                int needValue = 0 - nums[i] - nums[j];
                List<ValueAndIndex> list = index.get(needValue);
                if (list != null) {
                    int fi = i;
                    int fj = j;
                    Optional<ValueAndIndex> found = list.stream().filter(v -> v.index != fi && v.index != fj).findAny();
                    if (found.isPresent()) {
                        result.add(new ResultTuple(nums[i], nums[j], found.get().value));
                    }
                }
            }
        }

        return result.stream().map(ResultTuple::toList).collect(toList());
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    private static class ValueAndIndex {
        private int index;
        private int value;

        public static Map<Integer, List<ValueAndIndex>> buildIndex(int[] nums) {
            Map<Integer, List<ValueAndIndex>> result = new HashMap<>();
            for (int i = 0;i < nums.length;i++) {
                ValueAndIndex v = new ValueAndIndex();
                v.index = i;
                v.value = nums[i];

                result.computeIfAbsent(nums[i], (idx) -> new ArrayList<>());
                result.get(nums[i]).add(v);
            }

            return result;
        }
    }

    private static class ResultTuple {
        private int a, b, c;
        private int[] input;

        public ResultTuple(int a, int b, int c) {
            this.input = new int[]{a, b, c};
            int[] array = Arrays.copyOf(input, input.length);
            Arrays.sort(array);
            this.a = array[0];
            this.b = array[1];
            this.c = array[2];
        }

        public List<Integer> toList() {
            return Arrays.stream(input).boxed().collect(Collectors.toList());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (!(o instanceof ResultTuple)) return false;

            ResultTuple that = (ResultTuple) o;

            return new EqualsBuilder().append(a, that.a).append(b, that.b).append(c, that.c).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(a).append(b).append(c).toHashCode();
        }
    }
}
