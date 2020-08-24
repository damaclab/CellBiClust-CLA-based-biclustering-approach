package main;
import algorithm.CellBiClust;

public class MainCellBiClust
{
	public static void main(String[] args) throws Exception
	{
		try {
//			Arrays.stream(args).forEach(i->System.out.println(i));
			
			CellBiClust ob=new CellBiClust(args[0],args[1],
				Long.parseLong(args[4]),Long.parseLong(args[5]));
				
				if(args[6]==null || !args[6].toLowerCase().equals("true"))
					ob.runAlgorithm(args[2],args[3],"false",1.0,1.0);
				else
					ob.runAlgorithm(args[2],args[3],args[6].toLowerCase(),
						Double.parseDouble(args[7]),Double.parseDouble(args[8]));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Please check your options");
		}
		
	}
	
}	