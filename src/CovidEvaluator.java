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
    	return false;
    }

    public double getFitness(Cell candidate,List<? extends Cell> population)
    {
    	return candidate.removed/m;
    }

}