package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class CONSTANT {
	
	public final static int singleChoiceNum = 5;
	public final static int multipleChoiceNum = 5;
	public final static int judgeNum = 5;
	
	public final static int pageNum = 5;
	public final static int pageSize = 3;
	
	public static HashMap<String,Integer> seq;
	public final static String[] alphas = {
		"a","b","c","d","e",
		"f","g","h","i","j",
		"k","l","m","n","o",
		"p","q","r","s","t",
		"u","v","w","x","y",
		"z"
	};
	
	public final static Collection<Integer> getRandomSeq(int _rzSize, ArrayList<Integer> _orlist){
		
		HashSet<Integer> rzlist = new HashSet<Integer>();
		Collection<Integer> result = new ArrayList<Integer>();
		Integer[] index = new Integer[_rzSize];
		
		while(rzlist.size()<_rzSize){  
			for(int i=0; i<_orlist.size(); i++){
				int id = (int) (Math.random() * _orlist.size());
				rzlist.add(_orlist.get(id));
				if(rzlist.size()==_rzSize)
						break;
			}  
		}  
		
		System.out.println("");
		for(Integer or:_orlist)		
			System.out.print(or+"::");
		System.out.println("");
		for(Integer rz:rzlist){			
			System.out.print(rz+"::");
			result.add(rz);
		}
		
		return result;
	}
	
	public final static Integer[] getRandomCol(int _rzSize, ArrayList<Integer> _orlist){
		
		HashSet<Integer> rzlist = new HashSet<Integer>();
		Integer[] index = new Integer[_rzSize];
		
		while(rzlist.size()<_rzSize){  
			for(int i=0; i<_orlist.size(); i++){
				int id = (int) (Math.random() * _orlist.size());
				rzlist.add(_orlist.get(id));
				if(rzlist.size()==_rzSize)
						break;
			}  
		}  
			
		int i=0;
		for(Integer rz:rzlist){			
			System.out.print(rz+"::");
			index[i]=rz;
			i++;
		}
		
		return index;
	}

	
	public static void main(String[] args){
		ArrayList<Integer> _orlist = new ArrayList<Integer>();
		for(int i=0; i<10; i++)
			_orlist.add(i);
		for(int i=0; i<_orlist.size(); i++)
			System.out.print(_orlist.get(i)+"::");
		System.out.println("\n");
//		ArrayList<Integer> rzlist = CONSTANT.getRandomSeq(2, _orlist);
		Integer[] index = CONSTANT.getRandomCol(judgeNum, _orlist);
		ArrayList<Integer> rzlist = new ArrayList<Integer>();
		Collection<Integer> rlist  = CONSTANT.getRandomSeq(2, _orlist);
		rzlist.addAll(rlist);
	}

}
