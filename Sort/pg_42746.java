package org.example.Sort;

public class pg_42746 {

    public String findMax(String[] arr, int cur) {
        List<String>[] radix = new ArrayList[10];
        StringBuilder result = new StringBuilder();

        String equal = arr[0];
        int maxLen = 0;
        boolean equalCheck = false;
        for (String a : arr) {
            if (!equal.equals(a)) {
                equalCheck = true;
            }
            maxLen = Math.max(maxLen, a.length());
        }
        if (!equalCheck || cur >= (maxLen+maxLen)) { //
            for (String a : arr) {
                result.append(a);
            }
            return result.toString();
        }

        for (int i = 0; i < 10; i++) {
            radix[i] = new ArrayList<>();
        }

        for (String a : arr) {
            if (a.length()-1 < cur) {
                int idx = Integer.parseInt(String.valueOf((a.charAt(cur%a.length()))));
                radix[idx].add(a);
            } else {
                radix[Integer.parseInt(String.valueOf(a.charAt(cur)))].add(a);
            }
        }
        for (int i = 9; i >= 0; i--) {
            if (radix[i].size() == 1) {
                result.append(radix[i].get(0));
            } else if (radix[i].size() > 1) {
                result.append(findMax(radix[i].toArray(String[]::new), cur+1));
            }
        }
        return result.toString();
    }

    public String solution(int[] numbers) {
        String[] arr = Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);
        String answer = findMax(arr, 0);

        return (answer.charAt(0)=='0')?"0":answer;
    }
}