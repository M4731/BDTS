package org.example;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] array) {
        if (array.length <= 1) return;

        //System.out.println(Arrays.toString(array));
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        System.out.println("left "+Arrays.toString(left));
        System.out.println("right "+Arrays.toString(right));
        mergeSort(left);
        mergeSort(right);

        merge(array, left, right);
    }

    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
        System.out.println("result "+Arrays.toString(result));
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 3};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}