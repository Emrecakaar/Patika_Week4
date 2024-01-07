import java.util.Arrays;

public class MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public MyList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MyList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        this.array = new Object[capacity];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return array.length;
    }

    public void add(T data) {
        if (size == array.length) {
            ensureCapacity();
        }
        array[size++] = data;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return (T) array[index];
    }

    private void ensureCapacity() {
        int newCapacity = array.length * 2;
        array = Arrays.copyOf(array, newCapacity);
    }
}