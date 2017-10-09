package com.tristan.cracking.problems;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Chapter10 {
    static void bubbleSort(int[] array) {
        for(int i = 0; i  < array.length; i++) {
            for(int j = 0; j  < array.length - i - 1; j++) {
                if(array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    static void selectionSort(int[] array) {
        for(int i = 0; i < array.length - 1; i ++) {
            int smallest = array[i];
            int index = i;
            for(int j = i+1; j < array.length; j++) {
                if(array[j] < smallest){
                    smallest = array[j];
                    index = j;
                }
            }
            if(index != i) {
                int temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }
    }

    static void mergeSort(int[] array) {
        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] helper, int low, int high) {
        if(low < high) {
            int middle = (low + high) / 2;
            mergeSort(array, helper, low, middle);
            mergeSort(array, helper, middle+1, high);
            merge(array, helper, low, middle, high);
        }
    }

    private static void merge(int[] array, int[] helper, int low, int middle, int high) {
        System.arraycopy(array, low, helper, low, high - low + 1);

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        while(helperLeft <= middle && helperRight <= high) {
            if(helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else {
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        System.arraycopy(helper, helperLeft, array, current, middle - helperLeft + 1);
    }

    static void quickSort(int[] array){
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int low, int high) {
        int index = partition(array, low, high);
        if(low < index - 1) {
            quickSort(array, low, index - 1);
        }
        if(index < high) {
            quickSort(array, index, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[(low + high) / 2];
        while(low < high) {
            while(array[low] < pivot) {
                low++;
            }
            while(array[high] > pivot) {
                high--;
            }

            if(low <= high){
                int temp = array[low];
                array[low] = array[high];
                array[high] = temp;
                low++;
                high--;
            }


        }
        return low;
    }

    static void sortedMerge(int[] a, int[] b) {
        int bIndex = b.length - 1;
        int aIndex = a.length - b.length - 1;
        int currentIndex;
        for(currentIndex = a.length - 1; currentIndex >=0 && aIndex >=0 && bIndex >= 0; currentIndex--) {
            if(a[aIndex] > b[bIndex]) {
                a[currentIndex] = a[aIndex];
                aIndex--;
            } else {
                a[currentIndex] = b[bIndex];
                bIndex--;
            }
        }
        System.arraycopy(b, 0, a, 0, bIndex + 1);
    }

    static void groupAnagrams(String[] strings){
        Arrays.sort(strings, (a,b) -> Integer.compare(a.length(), b.length()));

        if(strings.length > 0) {
            for (int i = 0; i < strings.length; i++) {
                int currentLength = strings[i].length();
                int currentIndex = i;
                int sortIndex = i;
                while(currentIndex < strings.length && strings[currentIndex].length() == currentLength){
                    sortIndex = currentIndex;
                    currentIndex++;
                }

                Arrays.sort(strings, i, sortIndex + 1, (a,b) -> Integer.compare(charSum(a), charSum(b)));
                i = sortIndex;
            }
        }
    }

    static private int charSum(String s) {
        int sum = 0;
        for(Character c: s.toCharArray()){
            sum += c;
        }
        return sum;
    }

    static int binarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int mid;

        while(low <= high) {
            mid = (low + high) / 2;

            if(value < array[mid]) {
                high = mid - 1;
            } else if (value > array[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        throw new NoSuchElementException();
    }

    static int binarySearchOnRotatedSortedArray(int[] array, int left, int right, int value) {
        int mid = (left + right) / 2;
        if(array[mid] == value) {
            return mid;
        } else if (right < left) {
            throw new NoSuchElementException();
        }

        if(array[left] < array[mid]) {
            if(value >= array[left] && value < array[mid]) {
                return binarySearchOnRotatedSortedArray(array, left, mid - 1, value);
            } else {
                return binarySearchOnRotatedSortedArray(array, mid + 1, right, value);
            }
        } else if(array[left] > array[mid]) {
            if(value > array[mid] && value <= array[right]) {
                return binarySearchOnRotatedSortedArray(array, mid + 1, right, value);
            } else {
                return binarySearchOnRotatedSortedArray(array, left, mid - 1, value);
            }
        } else if (array[left] == array[mid]) {
            if(array[mid] != array[right]) {
                return binarySearchOnRotatedSortedArray(array, mid + 1, right , value);
            } else {
                try{
                    return binarySearchOnRotatedSortedArray(array, left, mid -1, value);
                } catch (NoSuchElementException e) {
                    return binarySearchOnRotatedSortedArray(array, mid+1, right, value);
                }
            }
        }

        throw new NoSuchElementException();
    }
}
