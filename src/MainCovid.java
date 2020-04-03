import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;

public class MainCovid
{

	public static void main(String[] args) 
	{
		List<EvaluatedCandidate<Cell>> temp=new MainCovid().evolveParams(); 
		int k1=0,k2=0;
		for(EvaluatedCandidate<Cell> i:temp)
			if(i.getCandidate().type=='v')
				k1++;
			else if((i.getCandidate().type=='a'))
				k2++;
		System.out.println(k1+" "+k2);
	}
    public  List<EvaluatedCandidate<Cell>> evolveParams()
    {
    	String vrna="UUUACCUACCCAGGAAAAGCCAACCAACCUCGAUCUCUUGUAGAUCUGUUCUCUAAACGAACUUUAAAAUCUGUGUAGCUGUCGCUCGGCUGCAUGCCUAGUGCACCUACGCAGUAUAAACAAUAAUAAAUUUUACUGUCGUUGACAAGAAACGAG";
    	String vacrna="AAAUGGAUGGGUCCUUUUCGGUUGGUUGGAGCUAGAGAACAUCUAGACAAGAGAUUUGCUUGAAAUUUUAGACACAUCGACAGCGAGCCGACGUACGGAUCACGUGGAUGCGUCAUAUUUGUUAUUAUUUAAAAUGACAGCAACUGUUCUUUGCUC";
    	int popsize=10000;
    	double alpha=0.5;
    	List<Cell> seed=new ArrayList<Cell>();
    	for(int i=0;i<(int)(popsize*alpha);i++)
    		seed.add(new Cell(vrna,'v'));
    	for(int i=(int)(popsize*alpha);i<popsize;i++)
    		seed.add(new Cell(vacrna,'a'));
        CovidFactory factory = new CovidFactory(popsize,0.5, vrna, vacrna);
        List<EvolutionaryOperator<Cell>> operators = new ArrayList<EvolutionaryOperator<Cell>>();
        operators.add(new CovidMutation(2));
        operators.add(new CovidCrossover(3));
        EvolutionaryOperator<Cell> pipeline = new EvolutionPipeline<Cell>(operators);
        EvolutionEngine<Cell> engine = new GenerationalEvolutionEngine<Cell>(factory,
                                                                                 pipeline,
                                                                                 new CovidEvaluator(vrna.length()),
                                                                                 new RouletteWheelSelection(),
                                                                                 new MersenneTwisterRNG());
        engine.addEvolutionObserver(new EvolutionLogger());
   
        List<EvaluatedCandidate<Cell>> temp= engine.evolvePopulation(popsize,100,seed,new VirusNil(1000));
        int k1=0,k2=0;
		for(EvaluatedCandidate<Cell> i:temp)
			if(i.getCandidate().type=='v')
				k1++;
			else if((i.getCandidate().type=='a'))
				k2++;
		System.out.println("Vaccine Efficiency : "+(1-(k1/(double)popsize)));
		return temp;
    }
    public static class EvolutionLogger implements EvolutionObserver<Cell>
    {
        public void populationUpdate(PopulationData<? extends Cell> data)
        {
        	System.out.println("Generation "+data.getGenerationNumber()+" : "+data.getMeanFitness()+" "+data.getPopulationSize()+" "+data.getElapsedTime()+" "+data.getFitnessStandardDeviation());
        }
    }
}