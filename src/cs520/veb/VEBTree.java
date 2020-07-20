package cs520.veb;

// Precondition: leftEnd >= 2

public class VEBTree extends Heap{
	// base case constant you change as you like.
	private final int 	BASECASE_SIZE = 3; 	 
										
	private Heap		sideHeap; 			
	private Heap 		heap[];
	
	public VEBTree(int left, int right){
		super(left,right);
		int subtreeSize 	= subtreeRange(); 	// size   of subtrees 
		int numberOfSubtrees 	= rootDegree();   	// number of subtrees
		// the heap and sideHeap can be VEBTree or Naive heap
		if (isBaseCase(0, numberOfSubtrees-1))
			sideHeap	= new NaiveHeap(0,numberOfSubtrees-1);
		else
			sideHeap	= new VEBTree(0,numberOfSubtrees-1);
		
		heap 			= new Heap[numberOfSubtrees];						
			
		// creating of child heaps
		for (int i = 0; i < numberOfSubtrees ; ++i){
			// calculate left range
			int l 		= getLeftEnd() + i * subtreeSize; 	
			// calculate right range
			int r 		= (i == numberOfSubtrees -1)? getRightEnd(): 
						getLeftEnd() + (i+1) * subtreeSize - 1; 				
			heap[i] 	= isBaseCase(l,r)? new NaiveHeap(l, r): new VEBTree(l,r);
		}
	}
	
	private int swapMin(int newMin){
		int oldMin 		= findMin();
		setMin(newMin);
		return oldMin;
	}
	
	@Override
	public boolean insert(int x){
		if (!inRange(x) || x == findMin())
			return false;
		
		if (isEmpty())
			return insertEmpty(x);
		
		boolean result 		= false;
		if (x < findMin())
			x = swapMin(x);
		
		int bucketIndex 	= subtreeBucket(x);
		
		if (heap[bucketIndex].isEmpty()){
			sideHeap.insert(bucketIndex);
			result 		= heap[bucketIndex].insertEmpty(x);
		}else 
			if (heap[bucketIndex].findMin()!= x)
				result 	= heap[bucketIndex].insert(x);
					
		if (result && x > findMax())
			setMax(x);
		return result;
	}
	
	@Override
	public void deleteMin() {
		if (getMinIndex() == getMaxIndex())
			setMaxIndex(-1);
		setMinIndex(-1);
		int firstNoneEmptyCluster = sideHeap.findMin();
		if (firstNoneEmptyCluster ==-1 ){
			setMinIndex(getMaxIndex());
			return;
		}
		setMin(heap[firstNoneEmptyCluster].findMin());
		//If heap becomes empty after deleteMin then this recurrence takes O(1)
		heap[firstNoneEmptyCluster].deleteMin();

		//The second recurrence is executed only if the cluster becomes empty,
		if (heap[firstNoneEmptyCluster].isEmpty()){
			heap[firstNoneEmptyCluster].setMinIndex(heap[firstNoneEmptyCluster].getMaxIndex());
			if (heap[firstNoneEmptyCluster].isEmpty())
				sideHeap.deleteMin();
		}
	}

	@Override
	public int findNext(int value) {
		if (value < findMin())
			return findMin();
		if (value >= findMax())
			return -1;
		
		int valueBucket 	= subtreeBucket(value);
		
		int minInHisBucket 	= heap[valueBucket].findMin();
		int maxInHisBucket 	= heap[valueBucket].findMax();
		
		if (value < minInHisBucket)
			return minInHisBucket;
		
		if (value < maxInHisBucket)
			return  heap[valueBucket].findNext(value);	
		else{
			int nextBucket 	= sideHeap.findNext(valueBucket);
			if (nextBucket!=-1)
				return heap[nextBucket].findMin();
			else
				return findMax();
		}	
	}

	
	public void printSideHeap(){
		if (isEmpty())
			return;
		int i = subtreeBucket(findMin());
		System.out.println(i);
		for (++i; i < rootDegree() && i!=-1; ++i)
			if (!heap[i].isEmpty())
				System.out.println(i);

	}
		
	public void printContents(){
		if (isEmpty())
			return;
		System.out.println(findMin());
		for (int i = 0; i < rootDegree() && i!=-1; ++i)
				heap[i].printContents();
	}

	private boolean isBaseCase(int l, int r){
		if ((r-l + 1) > BASECASE_SIZE)
			return false;
		return true;
	}
	

	//return the number of subtrees
	private int rootDegree(){
		return (int) Math.ceil( getSize() / (float)subtreeRange() );
	}

	// return the size of subtrees
	private int subtreeRange(){
		return (int) Math.sqrt(getRightEnd() -getLeftEnd() + 1.0);
	}
	
	// returns the bucket index where i can be stored
	private int subtreeBucket(int i){
		return (i-getLeftEnd())/subtreeRange();
	}
}
