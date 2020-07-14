package cs520.veb;

// This class is a supper class of VEBTree and NaiveHeap. This class includes all shared data and methods of its subclasses
// This class includes also abstract methods that have to be implemented in its child classes
public abstract class Heap {
	private int min;
	private int leftEnd;
	private int	rightEnd;
	
	public 		abstract boolean 	insert(int x);
	public 		abstract void 		printContents();
	protected 	abstract int 		deleteMin();

	protected Heap(int left, int right){
		setMin (-1);
		setLeftEnd(left);
		setRightEnd(right);
	}

	public void printSideHeap(){}
	
	public int extractMin() {
		if (isEmpty())
			return -1;
		int minimum = findMin();
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
		return isEmpty()?getMin():  getMin() + getLeftEnd();
	}

	/*==========================================================
	 * 		The following methods are set & get methods
	 ==========================================================*/
	protected int getMin() {
		return min;
	}

	protected void setMin(int min) {
		this.min = min;
	}

	protected int getLeftEnd() {
		return leftEnd;
	}

	protected void setLeftEnd(int leftEnd) {
		this.leftEnd = leftEnd;
	}

	protected int getRightEnd() {
		return rightEnd;
	}

	protected void setRightEnd(int rightEnd) {
		this.rightEnd = rightEnd;
	}	
}
