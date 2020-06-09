package com.company;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{96,5,255,6,39,66,256,18,34,3};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static LinkedBlockingQueue<Integer>[] countSort(int[] arr, int digit) {
        LinkedBlockingQueue<Integer>[] lst = new LinkedBlockingQueue[10];
        for (int i = 0; i < lst.length; i++) {
            lst[i] = new LinkedBlockingQueue<>();
        }
        for (int value : arr) {
            int num = value / (int) Math.pow(10, digit) % 10;
            lst[num].add(value);
        }
        return lst;
    }

    public static void radixSort(int[] arr) {
        int max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxDigits = (int)Math.ceil(Math.log10(max));

        for(int i = 0; i < maxDigits; i++) {
            LinkedBlockingQueue<Integer>[] lst = countSort(arr, i);
            int count = 0;
            for(LinkedBlockingQueue<Integer> queue : lst) {
                while(!queue.isEmpty()) {
                    arr[count] = queue.poll();
                    count++;
                }
            }
        }
    }
}
