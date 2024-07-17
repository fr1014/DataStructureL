package com.fangao.java.sort;

import java.util.Arrays;

/**
 * Create by fanrui07
 * Date: 2024/7/5
 * Describe: 归并
 */
public class MergeSort {
    private static final int SOURCE_TYPE = 0;
    private static final int SOURCE_TYPE_LEFT = 1;
    private static final int SOURCE_TYPE_RIGHT = 2;

    public static void main(String[] args) {
        int[] arr = {9, 7, 5, 6, 2, 3, 1, 4, 8};
        int left = 0;
        int right = arr.length - 1;
        mergeSort(SOURCE_TYPE, arr, left, right);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 划分原始数据- left: 0 right: 8
     * 划分左侧数据- left: 0 right: 4
     * 划分左侧数据- left: 0 right: 2
     * 划分左侧数据- left: 0 right: 1
     * 划分左侧数据- left: 0 right: 0
     * 划分右侧数据- left: 1 right: 1
     * 合并- left: 0 mid: 0 right: 1
     * 合并- [7, 9]
     * 划分右侧数据- left: 2 right: 2
     * 合并- left: 0 mid: 1 right: 2
     * 合并- [5, 7, 9]
     * 划分右侧数据- left: 3 right: 4
     * 划分左侧数据- left: 3 right: 3
     * 划分右侧数据- left: 4 right: 4
     * 合并- left: 3 mid: 3 right: 4
     * 合并- [2, 6]
     * 合并- left: 0 mid: 2 right: 4
     * 合并- [2, 5, 6, 7, 9]
     * 划分右侧数据- left: 5 right: 8
     * 划分左侧数据- left: 5 right: 6
     * 划分左侧数据- left: 5 right: 5
     * 划分右侧数据- left: 6 right: 6
     * 合并- left: 5 mid: 5 right: 6
     * 合并- [1, 3]
     * 划分右侧数据- left: 7 right: 8
     * 划分左侧数据- left: 7 right: 7
     * 划分右侧数据- left: 8 right: 8
     * 合并- left: 7 mid: 7 right: 8
     * 合并- [4, 8]
     * 合并- left: 5 mid: 6 right: 8
     * 合并- [1, 3, 4, 8]
     * 合并- left: 0 mid: 4 right: 8
     * 合并- [1, 2, 3, 4, 5, 6, 7, 8, 9]
     * [1, 2, 3, 4, 5, 6, 7, 8, 9]
     *
     * 左侧的递归函数：mergeSort(SOURCE_TYPE_LEFT, arr, left, mid)先处理
     * 划分原始数据- left: 0 right: 8
     * 划分左侧数据- left: 0 right: 4
     * 划分左侧数据- left: 0 right: 2
     * 划分左侧数据- left: 0 right: 1
     * 划分左侧数据- left: 0 right: 0
     *
     * 之后
     * 右侧的递归函数：mergeSort(SOURCE_TYPE_RIGHT, arr, mid + 1, right)开始处理符合条件的入参
     * left: 0 right: 1
     * left: 0 right: 2
     * left: 0 right: 4
     * left: 0 right: 8
     *
     * 期间若有符合
     * 左侧的递归函数：mergeSort(SOURCE_TYPE_LEFT, arr, left, mid)处理条件的任然会进入处理
     *
     * 直到所有的递归函数都不满足执行条件为止
     *
     */
    public static void mergeSort(int sourceType, int[] arr, int left, int right) {

        if (sourceType == SOURCE_TYPE_LEFT) {
            System.out.println("划分左侧数据- left: " + left + " right: " + right);
        } else if (sourceType == SOURCE_TYPE_RIGHT) {
            System.out.println("划分右侧数据- left: " + left + " right: " + right);
        } else {
            System.out.println("划分原始数据- left: " + left + " right: " + right);
        }

        if (left < right) {
            int mid = (left + right) / 2;
            //拆分左边数组，使左边的数组有序
            mergeSort(SOURCE_TYPE_LEFT, arr, left, mid);
            //拆分右边数组，使右边的数组有序
            mergeSort(SOURCE_TYPE_RIGHT, arr, mid + 1, right);
            //比较合并两个有序的数组
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        // 一遍比较完成后，左侧或右侧可能还会剩余有序的数组
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
        System.out.println("合并- left: " + left + " mid: " + mid + " right: " + right);
        System.out.println("合并- " + Arrays.toString(temp));
        //由于i是从left开始开始的，k是从0开始的，所以temp[k] == arr[left + k]
        for (int k2 = 0; k2 < temp.length; k2++) {
            arr[left + k2] = temp[k2];
        }
    }

}
