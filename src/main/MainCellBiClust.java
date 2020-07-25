package main;
import java.util.Map.Entry;
import java.util.Set;

import algorithm.CellBiClust;
import helper.Predict;

public class MainCellBiClust
{
	public static void main(String[] args) throws Exception
	{
		int k=2;
//		DataFrame TDB=new CSVReader().getDataFrame("D:\\work\\BiCluster\\Test_Cases\\HIV1.csv",",");
//		DataFrame TDB=new CSVReader().getDataFrame("D:\\work\\BiCluster\\Test_Cases\\Market.csv",",");
//		DataFrame TDB=new CSVReader().getDataFrame("D:\\work\\BiCluster\\Test_Cases\\Mangrove.csv",",");
//		DataFrame TDB=new CSVReader().getDataFrame("D:\\work\\BiCluster\\Test_Cases\\HIV2.csv",",");
//		DataFrame TDB=new CSVReader().getDataFrame("D:\\work\\BiCluster\\PPI_COVID_HUMAN.csv",",");
//		DataFrame TDB=new CSVReader().getDataFrame("D:\\work\\BiCluster\\PPI_COVID_HUMAN_T.csv",",");
//		DataFrame TDB=new CSVReader().getDataFrame("D:\\work\\BiCluster\\CH.csv",",");
//		DataFrame TDB=new CSVReader().getDataFrame("D:\\work\\BiCluster\\Test_Cases\\Test_100_100_20.csv",",");
//		System.out.println(TDB.convertToTr());
//		TDB.stream().forEach(i->System.out.println(i));
//		System.out.println(TDB.size());
//		CellBiClust ob=new CellBiClust(TDB,k,k,k);
//		Set<Entry<Set<Long>, Set<Long>>> output=ob.runAlgorithm();
//		output.clear();
//		System.out.println("Cluster count : "+output.size());
//		System.out.println(ob.getBiClustersAsString());
		CellBiClust ob=new CellBiClust(args[0],args[1],Double.parseDouble(args[2]),
				Long.parseLong(args[3]),Long.parseLong(args[4]));
		Set<Entry<Set<Long>, Set<Long>>> output=ob.runAlgorithm();
		System.out.println(ob.getBiClustersAsString());
		
		if(args[5].toLowerCase().equals("true"))
			new Predict(ob.getInputData(),output,Double.parseDouble(args[6])).getRules();
		
	}

}	