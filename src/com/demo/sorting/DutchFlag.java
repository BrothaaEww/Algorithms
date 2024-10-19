package com.demo.sorting;

import java.io.IOException;

public class DutchFlag {

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void dutchFlagSort(int[] arr) {
		int low = 0;
		int mid = 0;
		int high = arr.length - 1;

		while (mid <= high) {
			if (arr[mid] == 0) {
				swap(arr, low, mid);
				low++;
				mid++;
			} else if (arr[mid] == 1) {
				mid++;
			} else {
				swap(arr, mid, high);
				high--;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		int[] arr = { 1, 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 0, 1, 0, 2, 1, 0, 1, 0 };
		dutchFlagSort(arr);
		System.out.print("Sorted array: ");
		for (int num : arr) {
			System.out.print(num + " ");
		}
	}
}
