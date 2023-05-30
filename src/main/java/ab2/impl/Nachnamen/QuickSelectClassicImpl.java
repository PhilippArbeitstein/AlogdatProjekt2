package ab2.impl.Nachnamen;

public class QuickSelectClassicImpl {

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
    public static int findISmallestElement(int[] data, int i) {
        if (data == null || data.length < i || i < 0)
            return -1; // Invalid input, return -1
        buildHeap(data); // Build the heap initially

        for (int j = 0; j < i - 1; j++) {
            swap(data, 0, data.length - 1 - j); // Swap the root (maximum) i with the last i
            heapify(data, 0, data.length - 2 - j); // Re-heapify the remaining elements
        }

        return data[0]; // Return the i-smallest i (root of the heap)
    }


    /**
     * Baut einen Max-Heap aus den Array
     * @param data Die zu durchsuchenden Daten in Array-Form
     */
    private static void buildHeap(int[] data) {
        int n = data.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(data, i, n - 1); // Heapify each subtree starting from the bottom and going up
    }

    /**
     * Stellt die Heapcondition für alle Elemente die nach den rootindex kommen her
     * @param data Die zu durchsuchenden Daten in Array-Form
     * @param rootIndex der Index ab den die Heapcondition erfüllt werden soll
     * @param lastIndex der Index bis zu dem die Heapcondition erfüllt werden soll
     */
    private static void heapify(int[] data, int rootIndex, int lastIndex) {
        int leftChild = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;
        int largest = rootIndex;

        if (leftChild <= lastIndex && data[leftChild] > data[largest])
            largest = leftChild; // Check if the left child is larger than the current largest

        if (rightChild <= lastIndex && data[rightChild] > data[largest])
            largest = rightChild; // Check if the right child is larger than the current largest

        if (largest != rootIndex) {
            swap(data, rootIndex, largest); // Swap the largest element with the root
            heapify(data, largest, lastIndex); // Recursively heapify the affected subtree
        }
    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
