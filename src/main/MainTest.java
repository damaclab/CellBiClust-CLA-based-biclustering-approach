package main;
import algorithm.CellBiClust;

public class MainTest
{
	public static void main(String[] args) throws Exception
	{
		CellBiClust ob=new CellBiClust("src/sample.csv",",",2,2);
		ob.runAlgorithm("src","sample_output","true",0.7);
	}

}	