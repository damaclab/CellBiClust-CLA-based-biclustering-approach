package main;
import java.util.Map.Entry;
import java.util.Set;

import algorithm.CellBiClust;

public class MainTest
{
	public static void main(String[] args) throws Exception
	{
//	   	ob.runAlgorithm("src","sample_output","true",0.7);
		CellBiClust ob=new CellBiClust("D:\\work\\BiCluster\\Test_Cases\\Fish_Bio.csv",",",2,2);
		Set<Entry<Set<Long>, Set<Long>>> ii=ob.runAlgorithm("D:/work/BiCluster/","Fish","true",0.7);
		ii.forEach(i->System.out.println(i));
	}

}	