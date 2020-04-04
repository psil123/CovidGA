import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;
public class CovidEvaluator implements FitnessEvaluator<Cell> 
{

	private double m;
	public CovidEvaluator(double maxl)
	{
		m=maxl;
	}
    public boolean isNatural()
    {
    	return true;
    }

    public double getFitness(Cell candidate,List<? extends Cell> population)
    {
//    	if((candidate.type=='v' && candidate.removed==0)||(candidate.type=='a'))
//    		return 1.0;
    	if(candidate.type=='a')
    		return 1.0;
    	else if(candidate.type=='v' && (candidate.removed==0 ||candidate.removed/m<0.0167))
    		return 1.0;
    	return 0.0;
    }

}