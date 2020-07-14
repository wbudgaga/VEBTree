package cs520.veb;

import java.util.Scanner;

public class VEBTest {

	public static void main(String args[]){
	       Scanner Input = new Scanner(System.in);
	        int leftEnd, rightEnd;
	        System.out.print("Left end of the tree's range:  ");
	        leftEnd = Input.nextInt();
	        System.out.println(leftEnd);
	        System.out.print("right of the tree's range:  ");
	   
	        rightEnd = Input.nextInt();
	        System.out.println(rightEnd);
	        
	        VEBTree heap = new VEBTree(leftEnd, rightEnd);
	//        NaiveHeap NH = new NaiveHeap(leftEnd, rightEnd);

	        int choice; // menu option choice

	        do {

	            System.out.print ("\n\n ------------------------------ \n");
	            System.out.print ("\n\n     MENU     \n\n");
	            System.out.println ("0. Exit");
	            System.out.println ("1. Insert an integer");
	            System.out.println ("2. Find min");
	            System.out.println ("3. Find max");
	            System.out.println ("4. Extract min");
	            System.out.println ("5. Find next");
	            System.out.println ("6. Print contents");
	            System.out.println ("7. Print side heap");
	            
	    
	            System.out.print ("\n\n     Which? ");
	            choice = Input.nextInt();
	            System.out.print ("\n");
	            if (choice == 1){
	                int newVal;

	                System.out.print ("\n\nInteger to insert: ");
	                newVal = Input.nextInt();
	                System.out.println("\n" + newVal);
	                if (heap.insert(newVal))
	                    System.out.println("Successful");
	                else
	                    System.out.println("Unsuccessful");
	            } 
	            else if (choice == 2)
	                System.out.println("Current min = " + heap.findMin());
	            else if (choice == 3)
	                System.out.println("Current max = " + heap.findMax());
	            else if (choice == 4)
	                System.out.println ("Extracted item: " + heap.extractMin());
	            else if (choice == 5){
	            	System.out.print ("\n\nInteger to find next: ");
	            	int value = Input.nextInt();
	            	System.out.println(heap.findNext(value));
	            }
	            else if (choice == 6)
	                heap.printContents();
	            else if (choice == 7)
	                heap.printSideHeap();

	        } while (choice != 0);

	}
}
