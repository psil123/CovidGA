import java.util.Random;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

public class CovidFactory extends AbstractCandidateFactory<Cell>
{

	private int count,vc;
	private double alpha;
	Cell virus,vaccine;
	public CovidFactory(int s,double R,String vrna,String vacrna)
	{
		count=s;
		alpha=R;
		virus=new Cell(vrna,'v');
		vaccine=new Cell(vacrna,'a');
		vc=(int)Math.ceil(count*alpha);
	}

	@Override
	public Cell generateRandomCandidate(Random rng) 
	{
		return virus.clone();
		
	}
}