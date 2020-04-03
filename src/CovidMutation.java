import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;
public class CovidMutation implements EvolutionaryOperator<Cell> 
{
	private int R;
	public CovidMutation(int r)
	{
		R=r;
	}
	public List<Cell> apply(List<Cell> selectedCandidates, Random rng)
	{
		char amino[]= {'A','U','G','C'};
		for(Cell i:selectedCandidates)
		{
			char[] s=i.rnaseq.toCharArray();
			for(int k=0;k<R;k++)
			{
				s[(int)(Math.random()*(s.length-1))]=amino[(int)(Math.random()*3)];
			}
			i.rnaseq=s.toString();
		}
		return selectedCandidates;

	}
}