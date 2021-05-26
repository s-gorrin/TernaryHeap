/*\
 🌱 TernaryHeap
 🌱 Implement a ternary heap and heap sort with it
 🌱 Seth Gorrin
 🌱 CIS 27 Spring 2021
\*/

// theses are all to get the random input, and could be removed for other used of this class
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

// a utility class that converts a passed-in array to a ternary heap and sorts it
public class TernaryHeap {
    // from what I can tell, having a private constructor is what makes this a utility class
    private TernaryHeap() {
        System.out.println("don't");
        System.exit(-1);
    }

    // the only thing this class actually does is sort an array of comparables
    public static <T extends Comparable<T>> void heapSort(T[] inputArray){
        heapify(inputArray);
        sort(inputArray);
    }

    // swap two items in the heap by index
    private static <T extends Comparable<T>> void swap(T[] heap, int first, int second) {
        T temp = heap[first - 1];
        heap[first - 1] = heap[second - 1];
        heap[second - 1] = temp;
    }

    // get the parent of a node
    private static int getParent(int currentIndex) {
        if ((currentIndex + 1) % 3 == 0)
            return (currentIndex + 1) / 3;

        return currentIndex / 3;
    }

    // get children of a node
    private static int leftChild(int i) {
        return (i * 3) - 1;
    }
    private static int middleChild(int i) {
        return i * 3;
    }
    private static int rightChild(int i) {
        return (i * 3) + 1;
    }

    // check if a node has any larger children returns the index of the largest of the four items
    private static <T extends Comparable<T>> int getMax(T[] heap, int index, int N) {
        int max = index;

        if (heap[max - 1].compareTo(heap[leftChild(index) - 1]) < 0)
            max = leftChild(index);
        if (middleChild(index) <= N && heap[max - 1].compareTo(heap[middleChild(index) - 1]) < 0)
            max = middleChild(index);
        if (rightChild(index) <= N && heap[max - 1].compareTo(heap[rightChild(index) - 1]) < 0)
            max = rightChild(index);

        return max;
    }

    // heap up an item until it is in the correct place - unused, but retained for posterity
    private static <T extends Comparable<T>> void heapUp(T[] heap, int currentIndex) {
        while (currentIndex > 1 &&
                heap[getParent(currentIndex) - 1].compareTo(heap[currentIndex - 1]) < 0) { // parent is smaller
            swap(heap, currentIndex, getParent(currentIndex));
            currentIndex = getParent(currentIndex);
        }
    }

    // heap an item down until it is in the correct place
    private static <T extends Comparable<T>> void heapDown(T[] heap, int currentIndex, int N) {
        while (leftChild(currentIndex) <= N) { // if any children exist
            int largestChild = getMax(heap, currentIndex, N);
            if (currentIndex == largestChild)
                break;
            swap(heap, currentIndex, largestChild);
            currentIndex = largestChild;
        }
    }

    // convert an array to a ternary heap
    private static <T extends Comparable<T>> void heapify(T[] heap) {
        int currentIndex = (heap.length / 3) + 1;
        while (currentIndex > 0)
            heapDown(heap, currentIndex--, heap.length);

        /*
        // uncomment this to print the heaped input
        System.out.println("Heaped:");
        for (T item: heap) {
            System.out.print(item + " ");
        }
        System.out.println();
         */
    }

    // do the heap sort algorithm on a heap
    private static <T extends Comparable<T>> void sort(T[] heap) {
        int N = heap.length;
        while (N > 0) {
            swap(heap, 1, N--);
            heapDown(heap, 1, N);
        }
    }

    public static final int ARRAY_SIZE = 100;
    public static final int MAX_MEMBER_SIZE = 1000; // must be >= ARRAY_SIZE,
                                                    // will produce sorted set if equal

    public static void main(String[] args) {
        Set<Integer> inputSet = new HashSet<>();
        Integer[] inputArray = new Integer[ARRAY_SIZE];
        Random random = new Random();

        // generate 100 unique keys in random order
        while (inputSet.size() < ARRAY_SIZE) {
            inputSet.add(random.nextInt(MAX_MEMBER_SIZE));
        }

        System.out.println("Input:");
        int i = 0;
        for (Integer number:inputSet) {
            System.out.print(number + " ");
            inputArray[i++] = number;
        }
        System.out.println();

        TernaryHeap.heapSort(inputArray);

        System.out.println("Sorted:");
        for (Integer number:inputArray) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}