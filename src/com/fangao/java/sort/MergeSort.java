package com.fangao.java.sort;

import java.util.Arrays;

/**
 * Create by fanrui07
 * Date: 2024/7/5
 * Describe: 归并
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {9, 7, 5, 6, 2, 3, 1, 4, 8};
        int left = 0;
        int right = arr.length - 1;
        mergeSort(arr, left, right);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            //拆分左边数组，使左边的数组有序
            mergeSort(arr, left, mid);
            //拆分右边数组，使右边的数组有序
            mergeSort(arr, mid + 1, right);
            //比较合并两个有序的数组
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        //合并仅剩下的左侧数组
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        //合并仅剩下的右侧数组
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        System.out.println(Arrays.toString(temp));
        for (int k2 = 0; k2 < temp.length; k2++) {
            arr[left + k2] = temp[k2];
            System.out.println("left: " + left);
            System.out.println("k2: " +  k2);
            System.out.println("left + k2: " + (left + k2));
        }
    }

}
