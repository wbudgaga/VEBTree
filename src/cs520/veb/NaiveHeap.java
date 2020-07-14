package cs520.veb;

public class NaiveHeap extends Heap{
	private int heap[];

	public NaiveHeap(int left, int right){
		super(left,right);
		heap = new int[getSize()];
	}
	
	protected int get(int i){
		if (inRange(i))
			return heap[i];
		return -1;
	}
	
	@Override
	public boolean insert(int x){
		if (!inRange(x))
			return false;

		int arrayIndex = getActualIndex(x);
		if (isFound(arrayIndex)) // if already there return false
			return false;
		
		heap[arrayIndex] = 1;

		if (isEmpty() || x < findMin())
			setMin(arrayIndex);
		return true;
	}
	
	@Override
	public int deleteMin() {
		heap[getMin()]=0;
		int nextMin = getMin() + 1;
		for (; nextMin<heap.length && heap[nextMin]==0;++nextMin); // this is closed loop. it will continue until one or both conditions can't be hold
		if(nextMin==heap.length) // heap is empty
			setMin(-1);
		else{
			setMin(nextMin);
		}
		return findMin();       // return the min value as real value by adding the left range
	}
	
	public void printContents(){
		if (isEmpty())
			return;
		for (int i=getMin(); i < heap.length; ++i)
			if (heap[i]==1)
				System.out.println(getLeftEnd()+i);
	}
	
	private boolean isFound(int index){
		return heap[index] == 1;
	}
}
