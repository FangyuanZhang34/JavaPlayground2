// Question: https://www.geeksforgeeks.org/longest-span-sum-two-binary-arrays/
// Given two binary arrays arr1[] and arr2[] of same size n. Find length of the longest common span(i, j), 
// where j >= i, that arr1[i] + arr1[i + 1] + ... + arr1[j] == arr2[i] + arr2[i + 1] + ... + arr2[j].
// Solutions:
// 1. The first solution is to start from index 0, get the sum of each subarray [0, j] for both two arrays, 
// then if they have the same sum, update the maxLen if it is larger then the current one
// otherwise just keep going.
// This solution will traverse through index 0 to n - 1, 
// and inside each loop, we have to traverse through i to n - 1.
// So the time complexity would be O(n^2), 
// and the space complexity is O(1), because only need to record the sum for each step.
// 2. The second solution need more investigation first. 
// We could see that for these two BINARY arrays,
// the largest sum for each array from start to end is n, and the smallest sum is 0, 
// then for these two arrays, the largest difference of the sum of span[i, j] is -n to n, 
// which is 2n + 1 possibilities in total.
// And, what's more, if the difference of sum of the span[0, i] is the same as span[0, j](i < j), 
// for example they are both 8, then I can say the span[i + 1, j] have the same sum for these two arrays.
// Then the solution is clear: if we call difference of the sum of span[0, i] as prefixSumDiff[i],
// we want to find the largest (j - i) that share the same prefixSumDiff, prefixSumDiff[i] == prefixSumDiff[j].
// So we only need to traverse the array once, and record the first index for each prefixSumDiff
// once the prefixSumDiff does not appear for the first time, we know there is a subarray span with same sum
// and do the update if the span is the longest one ever.
// Since the prefixSumDiff is from -n to n, we can shift this range to 0 to 2n.
// Then we could create a array of length 2n + 1, and record the first index for each prefixSumDiff

class LongestSpanWithSameSumTwoArrays {

	private static arr1[] = new int[] {0, 1, 0, 1, 1, 1, 1};
	private static arr2[] = new int[] {1, 1, 1, 1, 1, 0, 1};

	private int getLongestSpanLength() {
		int n = arr1.length;
		// create prefixSumDiff array to record first index, initialze to -1
		prefixSumDiffFirstIndex[] = new int[2*n + 1];
		Arrays.fill(prefixSumDiffFirstIndex, -1);

		// create int prefixSum1 for arr1 to calculate prefix sum at each index
		int prefixSum1 = 0;
		// create int prefixSum2 for arr2 to calculate prefix sum at each index
		int prefixSum2 = 0;
		// create int maxLen to record length of longest span so far
		int maxLen = 0;
		int prefixSumDiff = 0;


		for(int i = 0; i < n; i++) {
			prefixSum1 += arr1[i];
			prefixSum2 += arr2[i];
			prefixSumDiff = prefixSum1 - prefixSum2;
			if(prefixSumDiff == 0) {
				maxLen = i + 1; // if prefixSumDiff is 0, it is certainly the longest span
			} else {
				if(prefixSumDiffFirstIndex[prefixSumDiff] == -1) {
					prefixSumDiffFirstIndex[prefixSumDiff] = i;
				} else {
					int firstIndex = prefixSumDiffFirstIndex[prefixSumDiff];
					int spanLength = i - firstIndex;
					maxLen = maxLen < spanLength ? spanLength : maxLen;
				}
			}
		}

		return maxLen;
	}

 
}