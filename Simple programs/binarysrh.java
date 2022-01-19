class binarysrh{ 		
	public int fun(int arr[],int val,int last){
		int first = 0;
		while (first <= last)
		{
			int m = first + (last - first) / 2;
        if (arr[m] == val)  
            return m;  
        if(arr[m] < val)  
			first = m + 1;
		else
			last = m - 1;
		}
		return -1;
		} 
    public static void main(String args[]){  
        int arr[] = {10,20,30,40,50};  
        int val = 30; 
		int last = arr.length-1;
		test obj =new test();
		int ans = obj.fun(arr,val,last);
		if(0<=ans)
		System.out.println("Number at "+ans);
		else 
		System.out.println("Number not present in array");
    }  
}  