package helper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
/** 
 * 
 * This is used to predict rules from the given dataset and set of clusters given by CellBiClust
 * @author Pritam Sil
 */
public class Predict
{
	double threshold;//minimum threshold for confidence
	DataFrame data;//Initial Data
	Set<Entry<Set<Long>, Set<Long>>> map;//output map from CellBiClust
	/** 
	 * 
	 * This is used to store a Rule
	 * @author Pritam Sil
	 */
	class Rule
	{
		/**
		 * Constructor
		 * @param ant The antecedent of the rule
		 * @param conseq The consequent of the rule
		 * @param conf The confidence of the rule
		 * @param supp The support of the rule
		 * @param obj The rows supporting the above rule
		 * */
		public Rule(Set<String> ant, String conseq, double conf, int supp, Set<String> obj) {
			this.antecedent=new HashSet<String>();
			antecedent.addAll(ant);
			this.obj_list=new HashSet<String>();
			this.obj_list.addAll(obj);
			this.consequent=new String(conseq);
			this.conf=conf;
			this.sup=supp;
		}
		Set<String> antecedent;//The antecedent of the rule
		Set<String> obj_list;//The rows supporting the above rule
		String consequent;//The consequent of the rule
		double conf;//The confidence of the rule
		int sup;//The support of the rule
		public String toString()
		{
			return "\""+antecedent+"\","+consequent+","+conf+","+sup+",\""+obj_list+"\"\n";
		}
	}
	
	/** 
	 * 
	 * This is used to store a Prediction
	 * @author Pritam Sil
	 */
	class Prediction
	{
		/**
		 * Constructor
		 * @param ant The antecedent of the prediction
		 * @param conseq The consequent of the prediction
		 * @param conf The confidence of the prediction
		 * */
		public Prediction(String ant, String conseq, double conf) {
			this.ant=ant;
			this.conseq=new String(conseq);
			this.conf=conf;
		}
		String ant;//The antecedent of the prediction
		String conseq;//The consequent of the prediction
		double conf;//The confidence of the prediction
		public String toString()
		{
			return ant+","+conseq+","+conf+"\n";
		}
	}
	// a comparator to compare between two sets
	private Comparator<Set<Long>> SetLongComparator=new Comparator<Set<Long>>()
	{

		@Override
		public int compare(Set<Long> o1, Set<Long> o2)
		{
			if(o1.size()<o2.size())
			{
				return -1;
			}
			else if(Sets.symmetricDifference(o1,o2).isEmpty())
			{
				return 0;
			}
			return 1;
		}
		
	};
	
	/**
	 * Constructor
	 * @param df the original dataset converted to a DataFrame object
	 * @param i the output set from CellBiClust
	 * @param t the threshold value for confidence
	 */
	public Predict(DataFrame df,Set<Entry<Set<Long>, Set<Long>>> i,double t)
	{
		this.data=df;
		map=new TreeSet<Entry<Set<Long>, Set<Long>>>(new Comparator<Entry<Set<Long>, Set<Long>>>()
				{

					@Override
					public int compare(Entry<Set<Long>, Set<Long>> o1, Entry<Set<Long>, Set<Long>> o2) {
						if(o1.getKey().equals(o2.getKey()) && o1.getValue().equals(o2.getValue()))
							return 0;
						return 1;
					}
			
				});
		i.forEach(k->map.add(k));
		this.threshold=t;
	}
	
	/**
	 * Methods to geenrate the rules from the given output set.
	 * @param outPath the path for the output file
	 * @param fname the file name
	 * @throws IOException 
	 * */
	public void getRules(String outPath,String fname) throws IOException
	{
		Set<Prediction> pred=new HashSet<Prediction>();
		Set<Rule> rule=new HashSet<Rule>();
		
		Map<Set<Long>,Long> count=new TreeMap<Set<Long>,Long>(this.SetLongComparator);
		for(Entry<Set<Long>, Set<Long>> i:map)
		{
			count.put(i.getKey(),(long) i.getValue().size());
		}
		for(Entry<Set<Long>, Set<Long>> i:map)
		{
			List<Long> temp=i.getKey().stream().collect(Collectors.toList());
			long sup=i.getValue().size();
			for(int j=0;j<temp.size();j++)
			{
				Set<Long> tempi=new TreeSet<Long>();
				long item=temp.get(j);
				for(int k=0;k<temp.size();k++)
					if(k!=j)
						tempi.add(temp.get(k));
				Set<Long> tids=new TreeSet<Long>();
				for(Entry<Set<Long>, Set<Long>> ii:map)
				{
					if(Sets.difference(tempi,ii.getKey()).size()==0)
						tids.addAll(ii.getValue());
				}
				double conf=sup/(double)tids.size();
				if(conf!=1.0 && conf>=this.threshold)
				{
					//Rule: stemp,this.data.getCName((int)(long)item-1),conf,tids.size(),tids
					//Prediction : <Rule:(stemp,this.data.getCName((int)(long)item-1))>,stemp,this.data.getCName((int)(long)item-1),conf
					Set<String> stemp=new HashSet<String>();
					for(Long ii:tempi)
						stemp.add(this.data.getCName((int)(long)ii-1));
					
					Set<String> tidsnm=new HashSet<String>();
					for(Long ii:Sets.difference(tids,i.getValue()))
						tidsnm.add(this.data.getRName((int)(long)ii-1));
					
					Set<String> tidnm=new HashSet<String>();
					for(Long ii:tids)
						tidnm.add(this.data.getRName((int)(long)ii-1));
					
					rule.add(new Rule(stemp,this.data.getCName((int)(long)item-1),conf,tids.size(),tidnm));
					
					for(String ii:tidsnm)
						pred.add(new Prediction(ii,this.data.getCName((int)(long)item-1),conf));
				}
			}
		}
		
		Set<Prediction> tempp=new HashSet<Prediction>();
		boolean flag;
		for(Prediction i:pred)
		{
			flag=true;
			for(Prediction j:pred)
				if(i!=j && i.ant.equals(j.ant) && i.conseq.equals(j.conseq) && j.conf>=i.conf)
				{
					flag=false;
				}
			if(flag)
				tempp.add(i);
		}
		pred=tempp;
		
		Set<Rule> temppp=new HashSet<Rule>();
		for(Rule i:rule)
		{
			flag=true;
			for(Rule j:rule)
				if(i!=j && j.antecedent.containsAll(i.antecedent) && j.consequent.equals(i.consequent))
				{
					flag=false;
//					System.out.println(i+" covered by "+j);
				}
			if(flag)
				temppp.add(i);
		}
		rule=temppp;
		
		FileWriter outFile,outPred;
		if(outPath==null)
		{
			System.out.print("====Rules====\nAntecedent,Consequent,Confidence,Support,Object List\n");
			for(Rule i:rule)
				System.out.print(i.toString());
			System.out.print("====Predictions====\nAntecedent,Consequent,Confidence\n");
			for(Prediction i:pred)
				System.out.print(i.toString());
		}
		else
		{
			outFile = new FileWriter(outPath+"/"+fname+"_rule.csv");
			outFile.append("Antecedent,Consequent,Confidence,Support,Object List\n");
			for(Rule i:rule)
				outFile.append(i.toString());
			outFile.close();
			
			outPred = new FileWriter(outPath+"/"+fname+"_prediction.csv");
			outPred.append("Antecedent,Consequent,Confidence\n");
			for(Prediction i:pred)
				outPred.append(i.toString());
			outPred.close();
		}
	}
}
