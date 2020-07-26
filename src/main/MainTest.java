package main;
import algorithm.CellBiClust;

public class MainTest
{
	public static void main(String[] args) throws Exception
	{
		CellBiClust ob=new CellBiClust("src/sample.csv",",",2,2,2);
		ob.runAlgorithm("src/output.txt","true",0.7);
		
		
//		if(args[5].toLowerCase().equals("true"))
//			new Predict(ob.getInputData(),output,Double.parseDouble(args[6])).getRules();
	}

}	