package main;
import algorithm.CellBiClust;

public class MainTest
{
	public static void main(String[] args) throws Exception
	{
//	   	ob.runAlgorithm("src","sample_output","true",0.7,0.75);
		CellBiClust ob=new CellBiClust("D:\\work\\BiCluster\\sample.csv",",",2,2);
		ob.runAlgorithm("D:/work/BiCluster/","Fish","true",0.7,0.75);
	}

}	