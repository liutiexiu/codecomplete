package me.tiezhu.quiz.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MySolution {
    static String[] paginate(int num, String[] results) {
        if (num < 1) {
            return null;
        }
        if (results == null || results.length == 0) {
            return new String[0];
        }

        int length = results.length;
        int pageCount = length / num + (length % num == 0 ? 0 : 1);
        String[] output = new String[length + pageCount - 1];

        Map<String, List<LineObject>> hostMap = new HashMap<>();
        for (String result : results) {
            LineObject line = new LineObject(result);
            List<LineObject> list = hostMap.get(line.host);
            if (list == null) {
                list = new LinkedList<>();
                hostMap.put(line.host, list);
            }
            list.add(line);
        }

        int count = 0;
        Set<String> hostSet = new HashSet<>();
        hostSet.addAll(hostMap.keySet());
        for (int i = 0; i < output.length; i++) {
            if (count == num) {
                output[i] = "";
                count = 0;
                hostSet.clear();
                hostSet.addAll(hostMap.keySet());
                continue;
            }

            count++;

            LineObject max = null;
            List<LineObject> maxList = null;
            if (hostSet.isEmpty()) {
                for (List<LineObject> lineList : hostMap.values()) {
                    if (max == null || max.score < lineList.get(0).score) {
                        max = lineList.get(0);
                        lineList.remove(0);
                        if (lineList.isEmpty()) {
                            hostMap.remove(max.host);
                        }
                        break;
                    }
                }
            } else {
                for (List<LineObject> lineList : hostMap.values()) {
                    if (hostSet.contains(lineList.get(0).host) &&
                            (max == null || max.score < lineList.get(0).score)) {
                        max = lineList.get(0);
                        maxList = lineList;
                    }
                }

                hostSet.remove(max.host);
                maxList.remove(0);
                if (maxList.isEmpty()) {
                    hostMap.remove(max.host);
                }
            }

            output[i] = max.value;
        }

        return output;
    }

    static class LineObject {
        public final String value;
        public final String host;
        public final float score;

        public LineObject(String result) {
            this.value = result;
            String[] parts = result.split(",");
            this.host = parts[0];
            this.score = Float.parseFloat(parts[2]);
        }
    }

    public static void main(String[] args) {
        String[] results = new String[] {
                "2,0,9.2,abc",
                "1,0,100.5,abc",
                "1,0,99,abc",
                "2,0,9,abc",
        };
        System.out.println(Arrays.toString(MySolution.paginate(3, results)));
    }
}
