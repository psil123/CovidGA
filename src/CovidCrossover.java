import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

public class CovidCrossover extends AbstractCrossover<Cell> 
{

	public CovidCrossover(int crossoverPoints)
	{
		super(crossoverPoints);
	}

	@Override
	protected List<Cell> mate(Cell parent1,Cell parent2, int numberOfCrossoverPoints,Random rng) 
	{
		List<Cell> offspring = null;//store the modified parent1 and parent2 here
		/*
		virus strand : AUGCAUGC and vaccine strand : CGU
		5:09
		find the longest common part of virus strand to which vaccine will bind nad remove it from virus strand
		5:09
		store the count in removed var
		5:10
		like vaccine strand will bind to GCA .... Hence virus strand 2 possible sites : AU<GCA>UGC or AUGCAU<GC>
		5:10
		here the first one is the longest
		5:10
		so that viral Cell object will now be modified in this manner ranseq="AUUGC" and removed=3
		5:11
		Cell class specs--
		String rnaseq;
		int removed;
		char type; ///'v' for virus and 'a' fir vaccine
		*/
		return offspring;
	}

}