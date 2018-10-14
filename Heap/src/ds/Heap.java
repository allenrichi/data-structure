package ds;

/**
 *
 * @author : Allen Richards
 * Create Date : 14/10/18
 **/
public class Heap<T extends Comparable<T>> {

  private T[] data;
  private int currHeapSize;

  /**
   *  {@param isMaxHeap} is used to determine whether to maintain max heap or min heap
   */
  private boolean isMaxHeap;

  public Heap(int max) {
    this.data = (T[]) new Comparable[max + 1];
  }

  public Heap(int max, boolean isMaxHeap) {
    this.data = (T[]) new Comparable[max + 1];
    this.isMaxHeap = isMaxHeap;
  }

  /**
   * Inserts the value to the Heap
   * @param value the value to be inserted
   * @return true if the value is inserted successfully, else false
   */
  public boolean push(T value) {
    if (currHeapSize == data.length - 1) {
      throw new UnsupportedOperationException("Heap size is full, cannot push data into the heap");
    }
    data[++currHeapSize] = value;
    if (isMaxHeap) {
      heapUp(currHeapSize);
    } else {
      heapDown(currHeapSize);
    }
    return true;
  }

  /**
   * Removes the max / min element from the heap based on {@param isMaxHeap}
   * @return the removed element
   */
  public T remove() {
    T value = null;
    if(currHeapSize > 1) {
      value = data[1];
      swap(1, currHeapSize--);
      sortHeap();
    }
    return value;
  }

  private void sortHeap() {
    for (int i = currHeapSize ; i > 0; i--) {
      if (isMaxHeap) {
        heapUp(i);
      } else {
        heapDown(i);
      }
    }
  }

  /**
   * This method helps to maintain the max heap property
   * @param index the index to start the max heapify
   */
  private void heapUp(int index) {
    int parent = (index) / 2;

    if (parent < 1) return;

    if (data[parent].compareTo(data[index]) < 0) {
      swap(parent, index);
      heapUp(parent);
    }
  }

  /**
   * This method helps to maintain the min heap property
   * @param index the index to start the min heapify
   */
  private void heapDown(int index) {
    int parent = (index) / 2;

    if (parent < 1) return;

    if (data[parent].compareTo(data[index]) > 0) {
      swap(parent, index);
      heapDown(parent);
    }
  }

  private void swap(int i, int j) {
    T temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("==========\n");
    for (int i = 1; i <= currHeapSize; i++) {
      sb.append(data[i]+"\n");
    }
    sb.append("==========");
    return sb.toString();
  }

  public static void main(String args[]) {
    Heap<Integer> heap = new Heap<>(10, true);
    heap.push(1);
    heap.push(4);
    heap.push(5);
    heap.push(7);
    heap.push(8);
    heap.push(15);
    heap.push(25);
    heap.push(35);
    heap.push(45);
    heap.push(55);
    System.out.println(heap.remove());
    System.out.println(heap.remove());
    System.out.println(heap.remove());
    System.out.println(heap);
  }

}
