package com.xyds.studyblog.temp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

public class TempTest {
	
	public static void main(String[] args) {
		
		System.out.println(new Date().compareTo(new Date(2013,1,20)));
		long a = new Date().getTime();
		long b = new Date(2013,1,20).getTime();
		System.out.println((b-a)/(60*60*24*1000*365));
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		Iterator<Integer> iterator = list.iterator();
		if(iterator.hasNext()){
			System.out.println("*************");
		}
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
