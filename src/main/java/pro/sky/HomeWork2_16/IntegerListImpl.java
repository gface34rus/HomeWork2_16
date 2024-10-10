package pro.sky.HomeWork2_16;

import pro.sky.HomeWork2_16.exception.BadIndexException;
import pro.sky.HomeWork2_16.exception.ElementNotFoundException;
import pro.sky.HomeWork2_16.exception.NullItemException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] elements;
    private int size;

    public IntegerListImpl() {
        elements = new Integer[10];
    }

    public IntegerListImpl(int capacity) {
        elements = new Integer[capacity];
    }

    @Override
    public Integer add(Integer item) {
        growIfNeeded();
        validateItem(item);
        elements[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        growIfNeeded();
        validateIndex(index);
        validateItem(item);

        if (index == size) {
            elements[size++] = item;
            return item;
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        elements[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Элемент не найден: " + item);
        }
        if (index != size) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = elements[index];
        if (index != size) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        size--;
        return item;

    }

    @Override
    public boolean contains(Integer item) {
        Integer[] elementscopy = toArray();
        sort(elementscopy);
        return binarySearch(elementscopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return elements[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new BadIndexException();
        }
    }


    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void growIfNeeded() {
        if (size == elements.length) {
            grow();
        }
    }

    private static void sort(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private void quickSort() {

    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void grow() {
        elements = Arrays.copyOf(elements, size + size / 2);
    }
}

