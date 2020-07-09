package main;
import java.util.Map.Entry;
import java.util.Set;

import algorithm.CellBiClust;
import helper.CSVReader;
import helper.DataFrame;
public class MainCellBiClust
{
	public static void main(String[] args) throws Exception
	{
		DataFrame TDB=new CSVReader().getDataFrame("D:\\work\\BiCluster\\Test_Cases\\HIV1.csv",",");
		
//		System.out.println(TDB.convertToTr());
//		TDB.stream().forEach(i->System.out.println(i));
//		System.out.println(TDB.size());
		CellBiClust ob=new CellBiClust(TDB,3,3,3);
		Set<Entry<Set<Long>, Set<Long>>> output=ob.runAlgorithm();
//		output.clear();
//		System.out.println("Cluster count : "+output.size());
//		System.out.println(ob.getBiClustersAsString());
		
//		new Predict(TDB,output,0.75).getRules();
	}

}	