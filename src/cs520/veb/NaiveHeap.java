package cs520.veb;

public class NaiveHeap extends Heap{
	private int heap[];

	public NaiveHeap(int left, int right){
		super(left,right);
		heap 			= new int[getSize()];
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

		int arrayIndex 		= getActualIndex(x);
		if (isFound(arrayIndex)) // if already there return false
			return false;
		
		heap[arrayIndex]	= 1;

		if (isEmpty() || x < findMin())	
			setMinIndex(arrayIndex);
		if(x > findMax())
			setMax(x);
		return true;
	}
	
	@Override
	public void deleteMin() {
		if (isEmpty())
			return;
		int nextMin = getMinIndex() ;
		heap[nextMin]=0;
		if (getMaxIndex()==getMinIndex())
			setMaxIndex(-1);
		setMinIndex(-1);
		//closed loop: continue until one or both conditions can't be hold
		for (++nextMin; nextMin<heap.length && heap[nextMin]==0;++nextMin); 
		// heap is still not empty
		if(nextMin != heap.length){
			setMinIndex(nextMin);
			heap[nextMin]=0;
		}else
			setMinIndex(getMaxIndex());

	}
	
	public void printContents(){
		if (isEmpty())
			return;
		System.out.println(findMin());
		for (int i = getMinIndex(); i < heap.length; ++i)
			if (heap[i] == 1)
				System.out.println(getLeftEnd()+i);
	}
	
	private boolean isFound(int index){
		return heap[index] == 1;
	}

	@Override
	public int findNext(int value) {
		int valueIndex 		= getActualIndex(value);
		int nextValueIdx 	= valueIndex + 1;
		//closed loop: continue until one or both conditions can't be hold		
		for(;nextValueIdx < heap.length && heap[nextValueIdx] == 0; ++nextValueIdx); 
		if(nextValueIdx == heap.length) // nextValue does not exist in this heap
			return -1;
		return nextValueIdx + getLeftEnd();       
	}
}
