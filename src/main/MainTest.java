package main;
import algorithm.CellBiClust;

public class MainTest
{
	public static void main(String[] args) throws Exception
	{
		CellBiClust ob=new CellBiClust("../sample.csv",",",10,2);
		ob.runAlgorithm("../sample.csv","sample","true",0.7,0.85);
		

	}

}	