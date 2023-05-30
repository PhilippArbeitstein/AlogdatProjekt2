package ab2.impl.Nachnamen;

public class QuickSelectMedianOfMediansImpl {

    /**
     * Quickselect-Implementierung zur Bestimmung des i-größten Elements in
     * einem unsortierten Array.
     *
     * @param data Die zu durchsuchenden Daten in Array-Form
     * @param i Der Index i für das i-größte Element (beginnend bei "1" für
     * das größte Element im Array).
     *
     * @return das i-größte Element im Array
     *          oder -1 falls der input invalid ist
     */
    public static int quickSelectMedianOfMedians(int[] data, int i) {
        if (data == null || data.length < i || i < 0)
            return -1; // Invalid input, return -1

        return select(data, 0, data.length - 1, i);
    }

    /**
     * Quickselect-Implementierung zur Bestimmung des i-größten Elements in
     * einem unsortierten Array.
     * @param data Die zu durchsuchenden Daten in Array-Form
     * @param left der index des arrays ab dem gesucht werden soll
     * @param right der index des array bis zu dem gesucht werden soll
     * @param element das i-kleinste element (i aber umgenannt um nicht mit schleifen zu kollidieren)
     * @return das i-kleinste element des abschnittes eines arrays
     */
    private static int select(int[] data, int left, int right, int element) {
        if (left == right)
            return data[left]; // Base case: Only one element, so it's already the median

        int pivot = getPivot(data, left, right); // Choose a pivot element
        int pivotIndex = partition(data, left, right, pivot); // Partition the array around the pivot

        if (element == pivotIndex)
            return data[element]; // Found the desired element
        else if (element < pivotIndex)
            return select(data, left, pivotIndex - 1, element); // Recurse on the left subarray
        else
            return select(data, pivotIndex + 1, right, element); // Recurse on the right subarray
    }

    /**
     * Gibt den Median of Medians für das array zurück
     * @param data Die zu durchsuchenden Daten in Array-Form
     * @param left der index des arrays ab dem gesucht werden soll
     * @param right der index des array bis zu dem gesucht werden soll
     * @return den median of medians
     */
    private static int getPivot(int[] data, int left, int right) {
        if (right - left < 5)
            return medianOf5(data, left, right); // If there are fewer than 5 elements, find the median directly

        int[] medians = new int[(int) Math.ceil((right - left + 1) / 5.0)];
        for (int i = 0; i < medians.length; i++) {
            int subArrayLeft = left + i * 5;
            int subArrayRight = Math.min(subArrayLeft + 4, right);
            medians[i] = medianOf5(data, subArrayLeft, subArrayRight); // Find medians of subarrays
        }
        return getPivot(medians, 0, medians.length - 1); // Recursively find the median of medians
    }

    /**
     * Verschiebt alle elemente die größer sind als der pivot an die rechte seite von diesem
     * @param data Die zu durchsuchenden Daten in Array-Form
     * @param left der index des arrays ab dem gesucht werden soll
     * @param right der index des array bis zu dem gesucht werden soll
     * @param pivot das pivotelement
     * @return den index des pivotelements
     */
    private static int partition(int[] data, int left, int right, int pivot) {
        int partitionIndex = left;
        for (int i = left; i < right; i++) {
            if (data[i] == pivot) {
                swap(data, i, right); // Move the pivot element to the rightmost position
                break;
            }
        }
        for (int i = left; i < right; i++) {
            if (data[i] < pivot) {
                swap(data, i, partitionIndex); // Swap elements smaller than pivot to the left
                partitionIndex++;
            }
        }
        swap(data, partitionIndex, right); // Move the pivot element to its final sorted position
        return partitionIndex;
    }

    /**
     * Findet den Median eines arrays mit länge 5 oder weniger
     * Verwendet Donald Knuth's sortieralgorithmus um maximal 7 vergleiche zum Sortieren eines 5 stelligen Arrays zu brauchen
     * @param data Die zu durchsuchenden Daten in Array-Form
     * @param left der index des arrays ab dem gesucht werden soll
     * @param right der index des array bis zu dem gesucht werden soll
     * @return den Median des arrays
     */
    private static int medianOf5(int[] data, int left, int right) {
        switch (right - left + 1) {
            case 1:
                return data[left]; // Only one element, so it's already the median
            case 2:
                if (data[left + 1] < data[left]) {
                    swap(data, left, left + 1);
                }
                return data[left + 1]; // Return the middle element
            case 3:
                // Perform swaps to bring the middle element to the correct position
                if (data[left + 1] < data[left]) {
                    swap(data, left, left + 1);
                }
                if (data[left + 2] < data[left]) {
                    swap(data, left, left + 2);
                }
                if (data[left + 2] < data[left + 1]) {
                    swap(data, left + 1, left + 2);
                }
                return data[left + 1]; // Return the middle element
            case 4:
                // Perform swaps to bring the middle two elements to the correct positions
                if (data[left + 1] < data[left]) {
                    swap(data, left, left + 1);
                }
                if (data[right] < data[left + 2]) {
                    swap(data, left + 2, right);
                }
                if (data[left + 2] < data[left]) {
                    swap(data, left, left + 2);
                }
                if (data[right] < data[left + 1]) {
                    swap(data, left + 1, right);
                }
                if (data[right] < data[left + 2]) {
                    swap(data, left + 2, right);
                }
                return data[left + 2]; // Return the middle element
            case 5:
                // Perform swaps to bring the middle three elements to the correct positions
                if (data[left + 1] < data[left]) {
                    swap(data, left, left + 1);
                }
                if (data[right - 1] < data[left + 2]) {
                    swap(data, left + 2, right - 1);
                }
                if (data[left + 2] < data[left]) {
                    swap(data, left, left + 2);
                }
                if (data[right] < data[left + 2]) {
                    swap(data, left + 2, right);
                }
                if (data[right - 1] < data[left + 1]) {
                    swap(data, left + 1, right - 1);
                }
                if (data[right] < data[right - 1]) {
                    swap(data, right - 1, right);
                }
                if (data[right - 1] < data[left + 2]) {
                    swap(data, left + 2, right - 1);
                }
                return data[left + 2]; // Return the middle element
        }

        return -1; // This should never be reached
    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}
