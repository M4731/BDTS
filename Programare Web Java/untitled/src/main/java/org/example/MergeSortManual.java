package org.example;

import java.util.Arrays;

public class MergeSortManual {

    public static void mergeSort(int[] array) {
        if (array.length <= 1) return;

        int mid = array.length/2;

        int[] left = Arrays.copyOfRange(array, 0 , mid);
        int[] right = Arrays.copyOfRange(array, mid , array.length);

        mergeSort(left);
        mergeSort(right);

        merge(left, right, array);
    }

    public static void merge(int[] left, int[] right, int[] result){
        int i = 0; int k = 0; int j = 0;

        while(i < left.length && j < right.length){
            if(left[i] > right[j]){
                result[k++] = right[j++];
            }
            else {
                result[k++] = left[i++];
            }
        }

        while(i<left.length){
            result[k++] = left[i++];
        }
        while(j<right.length){
            result[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 3};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
