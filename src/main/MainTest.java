package main;
import algorithm.CellBiClust;

public class MainTest
{
	public static void main(String[] args) throws Exception
	{
		CellBiClust ob=new CellBiClust("D:\\work\\BiCluster\\Test_Cases\\Fish_Bio.csv",",",10,2);
		ob.runAlgorithm("D:/work/BiCluster/","Fish","true",0.7,0.85);
		

	}

}	