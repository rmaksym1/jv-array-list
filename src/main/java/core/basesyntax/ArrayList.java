package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void resize() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + oldCapacity / 2;
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = elementData[i];
        }
        elementData = newData;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        if (size == elementData.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        while (size + list.size() > elementData.length) {
            resize();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        final T removed = elementData[index];
        for (int j = index; j < size - 1; j++) {
            elementData[j] = elementData[j + 1];
        }

        elementData[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementData[i] == null) {
                    for (int j = i; j < size - 1; j++) {
                        elementData[j] = elementData[j + 1];
                    }
                    elementData[size - 1] = null;
                    size--;
                    return null;
                }
            } else {
                if (elementData[i] != null && elementData[i].equals(element)) {
                    final T removed = elementData[i];
                    for (int j = i; j < size - 1; j++) {
                        elementData[j] = elementData[j + 1];
                    }
                    elementData[size - 1] = null;
                    size--;
                    return removed;
                }
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
    }
}
