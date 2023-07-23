package com.example.oracledbdemo;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestSolution {
    /**
     * 旋转数组
     * 
     * @param m int整型 右移距离
     * @param a int整型一维数组 给定数组
     * @return int整型一维数组
     */
    public int[] solve(int[] a, int m) {
        int n = a.length;
        int move = gcd(m, n);
        // use new array 时间O(n),空间(n)
        // int temp = a[0];
        // int[] temp1 = new int[move];
        // for (int i = 0; i < move; i++) {
        // temp1[i] = a[n - move + i];
        // }
        // for (int i = n - 1; i >= move; i--) {
        // a[i] = a[(i - move) % n];
        // }
        // for (int i = 0; i < move; i++) {
        // a[i] = temp1[i];
        // }

        // use new array 时间O(n),空间(1)
        for (int j = 0; j < move; j++) {
            int value = a[j];
            // log.info(j + "-round");
            for (int i = j + move; i < n; i = i + move) {
                int temp = value;
                value = a[i];
                a[i] = temp;
                // log.info("-" + i + "-");
                // log.info(i + "-" + value + "-" + a[i]);
                // log.info(Arrays.toString(a));
            }
            a[j] = value;
        }

        return a;

    }

    private int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }

    private void reverse(int[] list, int a, int b) {
        for (int i = 0; i < (b - a + 1) / 2; i++) {
            int temp = list[a + i];
            list[a + i] = list[b - i];
            list[b - i] = temp;
        }
        // log.info(Arrays.toString(list));

    }
    // public static void reverse(int[] nums, int start, int end) {
    // while (start < end) {
    // int temp = nums[start];
    // nums[start++] = nums[end];
    // nums[end--] = temp;
    // }
    // }

    private int[] rotate(int[] a, int m) {
        m %= a.length;
        reverse(a, 0, a.length - 1);
        // log.info(Arrays.toString(a));
        reverse(a, 0, a.length - m - 1);
        // log.info(Arrays.toString(a));
        reverse(a, a.length - m, a.length - 1);
        // log.info(Arrays.toString(a));
        return a;
    }

    /** 旋转数组 */
    @Test
    void testRotate() {
        // use
        // log.info(gcd(12, 8) + "");
        log.info(Arrays.toString(solve(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }, 8)));

        // reverse(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }, 6, 11);
        log.info(Arrays.toString(rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }, 8)));
    }

    private int[] reverse(int[] list2) {
        int[] list = Arrays.copyOf(list2, list2.length);
        if (list.length <= 1) {
            return list;
        // } else if (list.length == 2) {
        //     return new int[] { list[1], list[0] };
        } else {
            int[] sub = reverse(Arrays.copyOfRange(list, 0, list.length - 1));
            list[0] = list[list.length - 1];
            for (int i = 0; i < sub.length; i++) {
                list[i + 1] = sub[i];
            }
        }
        return list;
    }

    /** 递归翻转数组 */
    @Test
    void testReverse() {
        log.info(Arrays.toString(reverse(new int[] { 1, 2, 3 })));
        log.info(Arrays.toString(reverse(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12 })));
    }
}