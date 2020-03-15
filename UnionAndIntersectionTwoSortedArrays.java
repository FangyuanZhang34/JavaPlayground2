// Question: https://www.geeksforgeeks.org/union-and-intersection-of-two-sorted-arrays-2/
// Given two sorted arrays, find their union and intersection
// Example:
// arr1[] = {1, 3, 4, 5, 7}
// arr2[] = {2, 3, 5, 6}
// Union : {1, 2, ,3 ,4, 5, 6, 7}
// Intersection : {3, 5}
// Solution:
// Use "two pointers" to do it, set i, and j for arr1, and arr2 respectively.
// i and j both intialized as 0, 
// set two arrays union[] and intersection[] of length "m + n"
// from index 0 to some index "unionEnd" / "intersectionEnd".
// set current index "unionIndex", "intersectionIndex" for union[] and intersection[]

// for Union:
// 0. while i < m and j < n, 
// 1. if arr1[i] < arr2[j], union[unionIndex++] = arr1[i], i++
// 2. else if arr1[i] > arr2[j], union[unionIndex++] = arr2[j], j++
// 3. else if arr1[i] == arr2[j], union[unionIndex++] = arr1[i], i++, j++
// 4. end of the loop
// 5. if i < m, add the elements for [i, m - 1] into union, unionIndex += (m - i)
// 6. else if j < n, add the elements for [j, n - 1] into union, unionIndex += (n - j)
// 7. resultArray = subArray(0, unionIndex)

// for Intersection:
// 0. while i < m and j < n,
// 1. if arr1[i] < arr2[j], i++
// 2. else if arr1[i] > arr2[j], j++
// 3. else if arr1[i] == arr2[j], intersection[intersectionIndex] = arr1[i]
// end of the loop
// resultArray = subArray(0, intersectionIndex)

class UnionAndIntersectionTwoSortedArrays {


	private static int[] getUnion(int[] arr1, int[] arr2) {
		if(arr1 == null || arr1.length == 0) {
			return arr2;
		}

		if(arr2 == null || arr2.length == 0) {
			return arr1;
		}

		int m = arr1.length;
		int n = arr2.length;

		int[] union = new int[m + n];

		int i = 0, j = 0, unionIndex = 0;

		while(i < m && j < n) {
			if(arr1[i] < arr2[j]) {
				union[unionIndex++] = arr1[i++];
			} else if(arr1[i] > arr2[j]) {
				union[unionIndex++] = arr2[j++];
			} else {
				union[unionIndex++] = arr1[i];
				i++;
				j++;
			}
		}
		while(i < m) {
			union[unionIndex++] = arr1[i++];
		}
		while(j < n) {
			union[unionIndex++] = arr2[j++];
		}
		int[] resultArray = copyOfRange(union, 0, unionIndex);
		return resultArray;
	}

	private static int[] getIntersection(int[] arr1, int[] arr2) {
		if(arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) {
			return new int[0];
		}

		int m = arr1.length;
		int n = arr2.length;

		int[] intersection = new int[m + n];

		int i = 0, j = 0, intersectionIndex = 0;

		while(i < m && j < n) {
			if(arr1[i] < arr2[j]) {
				i++;
			} else if(arr1[i] > arr2[j]) {
				j++;
			} else {
				intersection[intersectionIndex++] = arr1[i];
				i++;
				j++;
			}
		}
		int[] resultArray = copyOfRange(intersection, 0, intersectionIndex);
		return resultArray;
	}

	private static int[] copyOfRange(int[] arr, int start, int end) {
		int len = end - start;
		int[] result = new int[len];
		for(int i = 0; i < len; i++) {
			result[i] = arr[i];
		}
		return result;
	}


	public static void main(String args[]) {
		int arr1[] = {1, 3, 4, 5, 7};
		int arr2[] = {2, 3, 5, 6};
		// Union:
		System.out.println("Union:");
		int[] union = getUnion(arr1, arr2);
		for(int val : union) {
			System.out.print(val + " ");
		}
		System.out.println("");

		// Intersection:
		System.out.println("Intersection:");
		int[] intersection = getIntersection(arr1, arr2);
		for(int val : intersection) {
			System.out.print(val + " ");
		}
		System.out.println("");
	}
}
