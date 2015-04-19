package tool;

public class MinBinaryHeap <T extends Comparable<? super T>>
{
  private static final int DEFAULT_CAPACITY = 2000;
  private int currentSize;
  private T[] array;
 public MinBinaryHeap()
 {
	 array = (T[])new Comparable[DEFAULT_CAPACITY];
	 currentSize = 0;
 }
 
 public MinBinaryHeap(int capacity)
 {
	 array = (T[])new Comparable[capacity];
	 currentSize = 0;
 }
 public MinBinaryHeap(T[] items)
 {
	 currentSize = items.length;
	 array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];
	 int i = 0;
	 for (T item : items)
	 {
		 array[++i] = item;
	 }
	 buildHeap();
 }
 public void insert(T x)
 {
	 if (currentSize == array.length - 1)
		 enlargeArray(array.length * 2 + 1);
	 int hole = ++currentSize;
	 for (;hole>1 && x.compareTo(array[hole/2]) < 0; hole /= 2)
	 {
		 array[hole] = array[hole/2];
	 }
	 array[hole] = x;
 }
 public T findMin()
 {
	return array[1];
 }
 public T deleteMin()
 {
	 if (isEmpty())
		return null;
	 T minItem = findMin();
	 array[1] = array[currentSize--];
	 percolateDown(1);
	return minItem;
 }
 public boolean isEmpty()
 {
	 
	return currentSize == 0;
 }
 public void makeEmpty()
 {
	 currentSize = 0;
 }
 private void percolateDown(int hole)
 {
	 int child = 0;
	 T tmp = array[hole];
	 for (;hole * 2 <= currentSize; hole = child)
	 {
		 child = hole * 2;
		 if (child != currentSize &&
				 array[child + 1].compareTo(array[child])<0)
			 ++child;
		 if (array[child].compareTo(tmp) < 0)
		        array[hole] = array[child];
		 else 
			 break;
	 }
	 array[hole] = tmp;
 }
 private void buildHeap()
 {
	 for (int i = currentSize / 2; i > 0; --i)
	 {
        percolateDown(i);		
	 }
 }
 private void enlargeArray(int newSize)
 {
	 T[] temp = (T[]) new Comparable[newSize];
	 for (int i = 1; i <= currentSize; i++)
	 {
		 temp[i] = array[i];
	 }
	 array = temp;
	 
 }
}
