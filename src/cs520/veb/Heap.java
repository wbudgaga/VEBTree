package cs520.veb;

// It is a supper class of VEBTree and NaiveHeap. This class includes all shared data and methods of its subclasses
// This class includes also abstract methods that have to be implemented in its child classes
public abstract class Heap {
	private 	int min;
	private 	int max;
	private 	int leftEnd;
	private 	int rightEnd;
	
	public 		abstract boolean 	insert(int x);
	public 		abstract void 		printContents();
	protected 	abstract void 		deleteMin();
	public 		abstract int 		findNext(int value);

	protected Heap(int left, int right){
		setMinIndex (-1);
		setMaxIndex (-1);
		setLeftEnd(left);
		setRightEnd(right);
	}

	public void printSideHeap(){}
	
	protected boolean insertEmpty(int x){
		setMin(x);
		setMax(x);
		return true;
	}

	public int extractMin() {
		if (isEmpty())
			return -1;
		int minimum 	= findMin();
		deleteMin();
		return minimum;
	}

	protected int getSize(){
		return rightEnd - leftEnd + 1;
	}

	// convert the value of x into actual array index value. for example, the range is between 4 to 34,
	// if we want to insert 7 in the array, then we have to found the actual array index where the value must be stored
	// in this case, the actual index of 7 is 7 - 4 = 3
	public int getActualIndex(int x) {
		return x - getLeftEnd();
	}
	
	public int getIndexValue(int index) {
		return index + getLeftEnd();
	}

	protected boolean inRange(int i){
		if (i < getLeftEnd() || i > getRightEnd())
			return false;
		return true;
	}

	protected boolean isEmpty(){
		return min == -1;
	}

	// returns -1 if the heap is empty or the minimum value that is achieved by adding leftEnd to the value's array index
	protected int findMin() {
		return isEmpty()? getMinIndex():  getIndexValue(getMinIndex());
	}
	protected void setMin(int val) {
		this.min 	= getActualIndex(val);
	}
	
	protected int findMax() {
		return isEmpty()? -1:  getIndexValue(getMaxIndex());
	}
	
	protected void setMax(int val) {
		this.max 	= getActualIndex(val);
	}

	/*==========================================================
	 * 		The following methods are set & get methods
	 ==========================================================*/
	protected int getMaxIndex() {
		return max;
	}

	protected void setMaxIndex(int max) {
		this.max 	= max;
	}

	protected int getMinIndex() {
		return min;
	}

	protected void setMinIndex(int min) {
		this.min 	= min;
	}

	protected int getLeftEnd() {
		return leftEnd;
	}

	protected void setLeftEnd(int leftEnd) {
		this.leftEnd 	= leftEnd;
	}

	protected int getRightEnd() {
		return rightEnd;
	}

	protected void setRightEnd(int rightEnd) {
		this.rightEnd 	= rightEnd;
	}	
}
