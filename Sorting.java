import Plotter.Plotter;

import java.util.Random;


public class Sorting {

    final static int SELECT_VS_QUICK_LENGTH = 12;
    final static int MERGE_VS_QUICK_LENGTH = 10;
    final static int COUNTING_VS_QUICK_LENGTH = 16;
    final static int BUBBLE_VS_MERGE_LENGTH = 12;
    final static int MERGE_VS_QUICK_SORTED_LENGTH = 11;
    final static double T = 600.0;

    /**
     * Sorts a given array using the quick sort algorithm.
     * At each stage the pivot is chosen to be the rightmost element of the subarray.
     * <p>
     * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
     *
     * @param arr - the array to be sorted
     */
    public static void quickSort(double[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(double[] arr, int p, int r) {
        if (p < r - 2) {
            int q = partition(arr, p, r);
            quickSort(arr, p, q - 1);
            quickSort(arr, q + 1, r);
        } else {
            simpleSort(arr, p, r);
        }

    }
    public static void simpleSort(double[] arr, int p, int r) {
        for (int i = p; i <= r; i++) {
            int min = i;
            for (int k = i + 1; k <= r; k++) {
                if (arr[k] < arr[min]) {
                    min = k;
                }
            }
            swap(arr, min, i);
        }
    }


    public static int partition(double[] arr, int p, int r) {
        double x = arr[r];
        int j = r;
        int i = p - 1;
        while (true) {
            while (true) {
                j--;
                if (j < p || arr[j] < x) {
                    break;
                }
            }
            while (true) {
                i++;
                if (i > r || arr[i] >= x) {
                    break;
                }
            }
            if (i < j) {
                swap(arr, i, j);
            } else {
                swap(arr, j + 1, r);
                return j + 1;
            }
        }

    }

    /**
     * Given an array arr and an index i returns the the i'th order statstics in arr.
     * In other words, it returns the element with rank i in the array arr.
     * <p>
     * At each stage the pivot is chosen to be the rightmost element of the subarray.
     * <p>
     * Should run in average complexity of O(n), and worst case complexity of O(n^2)
     **/
    public static double QuickSelect(double[] arr, int i) {
        return QuickSelect(arr, 0, arr.length - 1, i);
    }

    public static double QuickSelect(double[] arr, int p, int r, int i) {
        if (p == r) {
            return arr[p];
        }
        int q = partition(arr, p, r);
        int m = q - p + 1;
        if (i == m) {
            return arr[q];
        }
        if (i < m) {
            return QuickSelect(arr, p, q - 1, i);
        }
        return QuickSelect(arr, q + 1, r, i - m);
    }


    public static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Sorts a given array using the merge sort algorithm.
     * <p>
     * Should run in complexity O(nlog(n)) in the worst case.
     *
     * @param arr - the array to be sorted
     */

    public static void mergeSort(double[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(double[] arr, int p, int r) {
        int q = 0;
        if (p < r) {
            q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    public static void merge(double[] arr, int p, int q, int r) {
        int size1 = q - p + 1;
        int size2 = r - q;
        double[] leftSubArr = new double[size1 + 1];
        double[] rightSubArr = new double[size2 + 1];
        for (int i = 0; i < size1; i++) {
            leftSubArr[i] = arr[p + i];
        }
        for (int i = 0; i < size2; i++) {
            rightSubArr[i] = arr[q + 1 + i];
        }
        leftSubArr[size1] = Double.POSITIVE_INFINITY;
        rightSubArr[size2] = Double.POSITIVE_INFINITY;
        int i = 0;
        int j = 0;
        for (int k = p; k <= r; k++) {
            if (leftSubArr[i] <= rightSubArr[j]) {
                arr[k] = leftSubArr[i];
                i++;
            } else {
                arr[k] = rightSubArr[j];
                j++;
            }
        }

    }


    /**
     * Sorts a given array using bubble sort.
     * <p>
     * The algorithm should run in complexity O(n^2).
     *
     * @param arr - the array to be sorted
     */
    public static void bubbleSort(double[] arr) {
        bubbleSort(arr, arr.length - 1);
    }

    public static void bubbleSort(double[] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }


    /**
     * Sorts a given array, using the counting sort algorithm.
     * You may assume that all elements in the array are between 0 and k (not including k).
     * <p>
     * Should run in complexity O(n + k) in the worst case.
     *
     * @param arr - an array with possitive integers
     * @param k   - an upper bound for the values of all elements in the array.
     */
    public static void countingSort(int[] arr, int k) {
        int[] arr2 = new int[arr.length];
        int[] arr3 = new int[k];
        for (int i = 0; i < arr.length; i++) {
            arr3[arr[i]]++;
        }
        for (int i = 1; i < k; i++) {
            arr3[i] = arr3[i] + arr3[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            arr2[arr3[arr[i]] - 1] = arr[i];
            arr3[arr[i]]--;
        }
    }


    public static void main(String[] args) {
        //countingVsQuick();
        //mergeVsQuick();
        //mergeVsQuickOnSortedArray();
        //mergeVsBubble();
        QuickSelectVsQuickSort();
    }


    private static void countingVsQuick() {
        double[] quickTimes = new double[COUNTING_VS_QUICK_LENGTH];
        double[] countingTimes = new double[COUNTING_VS_QUICK_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < COUNTING_VS_QUICK_LENGTH; i++) {
            long sumQuick = 0;
            long sumCounting = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                int[] b = new int[size];
                for (int j = 0; j < a.length; j++) {
                    b[j] = r.nextInt(size);
                    a[j] = b[j];
                }
                startTime = System.currentTimeMillis();
                quickSort(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                countingSort(b, size);
                endTime = System.currentTimeMillis();
                sumCounting += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            countingTimes[i] = sumCounting / T;
        }
        Plotter.plot("Counting sort on arrays with elements < n", countingTimes, "Quick sort on arrays with elements < n", quickTimes);

    }


    /**
     * Compares the merge sort algorithm against quick sort on random arrays
     */
    public static void mergeVsQuick() {
        double[] quickTimes = new double[MERGE_VS_QUICK_LENGTH];
        double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
            long sumQuick = 0;
            long sumMerge = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSort(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                mergeSort(b);
                endTime = System.currentTimeMillis();
                sumMerge += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            mergeTimes[i] = sumMerge / T;
        }
        Plotter.plot("quick sort on random array", quickTimes, "merge sort on random array", mergeTimes);
    }

    /**
     * Compares the merge sort algorithm against quick sort on pre-sorted arrays
     */
    public static void mergeVsQuickOnSortedArray() {
        double[] quickTimes = new double[MERGE_VS_QUICK_SORTED_LENGTH];
        double[] mergeTimes = new double[MERGE_VS_QUICK_SORTED_LENGTH];
        long startTime, endTime;
        for (int i = 0; i < MERGE_VS_QUICK_SORTED_LENGTH; i++) {
            long sumQuick = 0;
            long sumMerge = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = j;
                    b[j] = j;
                }
                startTime = System.currentTimeMillis();
                quickSort(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                mergeSort(b);
                endTime = System.currentTimeMillis();
                sumMerge += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            mergeTimes[i] = sumMerge / T;
        }
        Plotter.plot("quick sort on sorted array", quickTimes, "merge sort on sorted array", mergeTimes);
    }

    /**
     * Compares merge sort and bubble sort on random arrays
     */
    public static void mergeVsBubble() {
        double[] mergeTimes = new double[BUBBLE_VS_MERGE_LENGTH];
        double[] bubbleTimes = new double[BUBBLE_VS_MERGE_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < BUBBLE_VS_MERGE_LENGTH; i++) {
            long sumMerge = 0;
            long sumBubble = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                mergeSort(a);
                endTime = System.currentTimeMillis();
                sumMerge += endTime - startTime;
                startTime = System.currentTimeMillis();
                bubbleSort(a);
                endTime = System.currentTimeMillis();
                sumBubble += endTime - startTime;
            }
            mergeTimes[i] = sumMerge / T;
            bubbleTimes[i] = sumBubble / T;
        }
        Plotter.plot("merge sort on random array", mergeTimes, "bubble sort on random array", bubbleTimes);
    }


    /**
     * Compares the quick select algorithm with a random rank, and the quick sort algorithm.
     */
    public static void QuickSelectVsQuickSort() {
        double[] QsortTimes = new double[SELECT_VS_QUICK_LENGTH];
        double[] QselectTimes = new double[SELECT_VS_QUICK_LENGTH];
        Random r = new Random();
        long startTime, endTime;
        for (int i = 0; i < SELECT_VS_QUICK_LENGTH; i++) {
            long sumQsort = 0;
            long sumQselect = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSort(a);
                endTime = System.currentTimeMillis();
                sumQsort += endTime - startTime;
                startTime = System.currentTimeMillis();
                QuickSelect(b, r.nextInt(size) + 1);
                endTime = System.currentTimeMillis();
                sumQselect += endTime - startTime;
            }
            QsortTimes[i] = sumQsort / T;
            QselectTimes[i] = sumQselect / T;
        }
        Plotter.plot("quick sort with an arbitrary pivot", QsortTimes, "quick select with an arbitrary pivot, and a random rank", QselectTimes);
    }


}
