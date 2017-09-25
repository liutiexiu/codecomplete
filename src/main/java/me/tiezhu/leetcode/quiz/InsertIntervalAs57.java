package me.tiezhu.leetcode.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 *
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 *
 */
public class InsertIntervalAs57 {

    // beat 8% only, seems insert should use binary search
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null) {
            return intervals;
        }
        if (intervals == null || intervals.size() == 0) {
            return Collections.singletonList(newInterval);
        }

        System.out.println("====\ninput:" + intervals + ", and " + newInterval);
        intervals = new ArrayList<>(intervals);

        int i = 0;
        boolean added = false;
        boolean changed = false;
        for (Interval current : intervals) {
            if (current.start > newInterval.end) {
                intervals.add(i, newInterval);
                added = true;
                break;
            }
            if (current.end < newInterval.start) {
                i++;
                continue;
            }

            current.start = current.start < newInterval.start ? current.start : newInterval.start;
            current.end = current.end > newInterval.end ? current.end : newInterval.end;
            changed = true;
            break;
        }

        System.out.println(intervals);

        if (!(added || changed) && intervals.get(intervals.size() - 1).end < newInterval.start) {
            intervals.add(newInterval);
        } else {
            ListIterator<Interval> it = intervals.listIterator();
            Interval current = it.next();
            while(it.hasNext()) {
                Interval next = it.next();
                if (current.end >= next.start) {
                    current.start = current.start < next.start ? current.start : next.start;
                    current.end = current.end > next.end ? current.end : next.end;
                    it.remove();
                } else {
                    current = next;
                }
            }
        }

        return intervals;
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return String.format("[%d,%d]", start, end);
        }
    }

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(new Interval(1,5), new Interval(6,8));
        System.out.println(new InsertIntervalAs57().insert(intervals, new Interval(5,6)));

        intervals = Arrays.asList(
                new Interval(1,2), new Interval(3,5), new Interval(6,7), new Interval(8,10), new Interval(12,16));
        System.out.println(new InsertIntervalAs57().insert(intervals, new Interval(4,9)));

        intervals = Arrays.asList(
                new Interval(2,4), new Interval(5,7), new Interval(8,10), new Interval(11,13));
        System.out.println(new InsertIntervalAs57().insert(intervals, new Interval(3,6)));

        intervals = Arrays.asList(
                new Interval(2,4), new Interval(5,7));
        System.out.println(new InsertIntervalAs57().insert(intervals, new Interval(0,1)));

        intervals = Arrays.asList(
                new Interval(2,4), new Interval(5,7));
        System.out.println(new InsertIntervalAs57().insert(intervals, new Interval(10,11)));

        intervals = Arrays.asList(
                new Interval(2,7), new Interval(8,9));
        System.out.println(new InsertIntervalAs57().insert(intervals, new Interval(4,5)));

        intervals = Arrays.asList(
                new Interval(1,5));
        System.out.println(new InsertIntervalAs57().insert(intervals, new Interval(1,7)));

        intervals = Arrays.asList(
                new Interval(3,5));
        System.out.println(new InsertIntervalAs57().insert(intervals, new Interval(1,5)));

        intervals = Arrays.asList(
                new Interval(3,5), new Interval(10,11));
        System.out.println(new InsertIntervalAs57().insert(intervals, new Interval(3,7)));
    }
}
