package control;

import java.util.ArrayList;

public class Test {
	public static void add(Integer n)
	{
		n++;
	}
	public static void main(String[] args) {
		
		ArrayList<Integer> arr=new ArrayList<Integer>(); 
		arr.add(3);
		arr.add(4);
		arr.add(5);
		for(int i=0;i<arr.size();++i)
		{
			add(arr.get(i));
			System.out.println(arr.get(i));
		}
		
	}

}
