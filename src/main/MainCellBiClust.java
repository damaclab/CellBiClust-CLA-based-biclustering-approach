package main;
import algorithm.CellBiClust;

public class MainCellBiClust
{
	public static void main(String[] args) throws Exception
	{
		try {
			CellBiClust ob=new CellBiClust(args[0],args[1],
				Long.parseLong(args[3]),Long.parseLong(args[4]));
				ob.runAlgorithm(args[2],args[6].toLowerCase(),Double.parseDouble(args[7]),args[5]);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Please check your options");
		}
		
	}
	
}	