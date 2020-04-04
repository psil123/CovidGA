import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;

public class VirusNil implements TerminationCondition
{
	long sec;
	public VirusNil(long s)
	{
		sec=s;
	}
	@Override
	public boolean shouldTerminate(PopulationData<?> populationData)
	{
		if((populationData.getFitnessStandardDeviation()==0 && populationData.getGenerationNumber()!=0)||populationData.getElapsedTime()>=sec)
			return true;
		return false;
	}
	
}
