package cs520.veb;

// Precondition: leftEnd >= 2

public class VEBTree extends Heap{
	private final int 	BASECASE_SIZE = 3; 	// Base case constant.  You can change it as you like. 
							// You can make it big as VEBtree size. In this case we will deal only with naive heap
	
	private NaiveHeap	sideHeap; 		// the sideHeap is defined as NaiveHeap so we can use the methods there
	private Heap 		heap[];
	
	public VEBTree(int left, int right){
		super(left,right);
		int subtreeSize 	= subtreeRange(); 					// size   of subtrees 
		int numberOfSubtrees	= rootDegree();   					// number of subtrees
		sideHeap 		= new NaiveHeap(0,numberOfSubtrees);
		heap 			= new Heap[numberOfSubtrees];				// the heap can be VEBTree or Naive heap(in case of base case)
			
		// creating of child heaps
		for (int i = 0; i < numberOfSubtrees ; ++i){
			int l 	= getLeftEnd() + i * subtreeSize; 				// calculate left range
			int r 	= (i == numberOfSubtrees -1)? getRightEnd(): 
				   getLeftEnd() + (i+1) * subtreeSize - 1; 			// calculate right range
			heap[i] = isBaseCase(l,r)? new NaiveHeap(l, r): new VEBTree(l,r);	// the heap can be VEBTree or Naive heap
		}
	}

	@Override
	public boolean insert(int x){
		if (!inRange(x))
			return false;

		int arrayIndex = getActualIndex(x);
		if (isEmpty() || x < findMin())
			setMin(arrayIndex);
		
		int bucketIndex = subtreeBucket(x);
		sideHeap.insert(bucketIndex);
		return heap[bucketIndex].insert(x);
	}
	
	@Override
	public int deleteMin() {
		int bucketIndex 	= subtreeBucket(findMin());
		int nextMin 		= heap[bucketIndex].deleteMin();
		
		if (nextMin == -1){
			int nextBucket 	= sideHeap.deleteMin();
			if(nextBucket != -1)
				nextMin = heap[nextBucket].findMin();
		}
		
		if (nextMin == -1){
			setMin(-1);
		}else
			setMin(getActualIndex(nextMin));
		
		return findMin();
	}
	
	public void printSideHeap(){
		sideHeap.printContents();
	}
		
	public void printContents(){
		if (isEmpty())
			return;
		for (int i = sideHeap.findMin(); i < rootDegree(); ++i)
			if (sideHeap.get(i)==1)
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
