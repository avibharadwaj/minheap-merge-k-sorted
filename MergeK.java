import java.util.*;

class Node{
	int element,i,j;
	
	public Node(int element,int i,int j){
		this.element=element;
		this.i=i;
		this.j=j;
	}
}

class MergeK{

	public void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the number of arrays to merge.");
		int n=sc.nextInt();
		System.out.println("Please enter the number of elements within each array.");
		int k=sc.nextInt();
		
		System.out.println("Please enter the elements within each array.");
		
		int arr [][]=new int [n][k];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<k;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		
		mergeArrays(arr,arr.length);
	}
	
	public void mergeArrays(int [][] arr, int k) {
		Node[] heap = new Node[k];
		int size=0;
		//filling the heap array with the first element from each array
		
		for(int i=0; i<arr.length;i++) {
			Node node = new Node(arr[i][0],i,1);
			heap[i]=node;
			size+=arr[i].length;
		}
		
		//to generate a min-heap from this heap array
		int j= (k-1)/2;
		while(j>=0) {
			heapify(heap,j,k);
			j--;
		}
		
		int[] result = new int[size];
		
		for(int i=0;i<size;i++) {
			Node root = getmin(heap,heap.length);
			result[i]=root.element;
			
			//to find the next element
			if(root.j<arr[root.i].length)
				root.element=arr[root.i][root.j++];
			else
				root.element=Integer.MAX_VALUE;
			
			replaceMin(heap,root,k);
		}
		
		for(int l:result) {
			System.out.print(l+" ");
			System.out.println();
		}
	}
	
	public void replaceMin(Node[] heap,Node root,int k) {
		heap[0]=root;
		heapify(heap,0,k);
	}
	
	public void heapify(Node[] heap,int i,int size) {
		int l = left(i); 
        int r = right(i); 
        int smallest = i; 
        if (l < size && heap[l].element < heap[i].element) 
            smallest = l; 
        if (r < size && heap[r].element < heap[smallest].element) 
            smallest = r; 
        if (smallest != i) 
        { 
            swap(heap, i, smallest); 
            heapify(heap,smallest,size); 
        } 
	}
	
	//get left child
	int left(int i) {
		return (2*i+1);
	}
	//get right child
	int right(int i) {
		return (2*i+2);
	}
	
	void swap(Node[] heap,int i,int j) {
		Node temp = heap[i];
		heap[i]=heap[j];
		heap[j]=temp;
	}
	
	//to get minimum element
	Node getmin(Node[] heap,int heap_size) {
		if(heap_size<=0) {
			System.out.println("Underflow.");
			return null;
		}
		return heap[0];
	}
}