package helper;

import java.util.Comparator;
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
	 * */
	public void getRules()
	{
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
					String stemp="[";
					for(Long ii:tempi)
						stemp+=this.data.getCName((int)(long)ii-1)+" ";
					stemp+="]";
					System.out.println(stemp+"->"+this.data.getCName((int)(long)item-1)+" Conf : "+conf);
					stemp="[";
					for(Long ii:Sets.difference(tids,i.getValue()))
						stemp+=this.data.getRName((int)(long)ii-1)+" ";
					stemp+="]";
					System.out.println("Prediction : "+stemp+"<->["+this.data.getCName((int)(long)item-1)+"]\n");
				}
			}
		}
	}
}
