package main;
import algorithm.CellBiClust;

public class MainTest
{
	public static void main(String[] args) throws Exception
	{
//		CellBiClust ob=new CellBiClust("src/sample.csv",",",2,2);
//		ob.runAlgorithm("src","sample_output","true",0.7);
		CellBiClust ob=new CellBiClust("D:\\work\\BiCluster\\PPI_COVID_HUMAN.csv",",",2,2);
		ob.runAlgorithm("D:/work/BiCluster/","HC","true",0.7);
	}

}	