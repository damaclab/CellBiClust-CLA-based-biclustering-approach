package main;
import algorithm.CellBiClust;

public class MainCellBiClust
{
	public static void main(String[] args) throws Exception
	{
		CellBiClust ob=new CellBiClust(args[0],args[1],Double.parseDouble(args[3]),
				Long.parseLong(args[4]),Long.parseLong(args[5]));
		ob.runAlgorithm(args[2]);
		
		
//		if(args[5].toLowerCase().equals("true"))
//			new Predict(ob.getInputData(),output,Double.parseDouble(args[6])).getRules();
	}

}	