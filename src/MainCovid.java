import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

public class MainCovid
{

	public static void main(String[] args) 
	{
	     new MainCovid().evolveParams();
	}
    public  Cell evolveParams()
    {
    	String vrna="UUUACCUACCCAGGAAAAGCCAACCAACCUCGAUCUCUUGUAGAUCUGUUCUCUAAACGAACUUUAAAAUCUGUGUAGCUGUCGCUCGGCUGCAUGCCUAGUGCACCUACGCAGUAUAAACAAUAAUAAAUUUUACUGUCGUUGACAAGAAACGAG";
    	String vacrna="AAAUGGAUGGGUCCUUUUCGGUUGGUUGGAGCUAGAGAACAUCUAGACAAGAGAUUUGCUUGAAAUUUUAGACACAUCGACAGCGAGCCGACGUACGGAUCACGUGGAUGCGUCAUAUUUGUUAUUAUUUAAAAUGACAGCAACUGUUCUUUGCUC";
    	int popsize=10000;
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
        return engine.evolve(popsize, // 100 individuals in the population.
                             0, // 5% elitism.
                             new TargetFitness(1, true));
    }
    public static class EvolutionLogger implements EvolutionObserver<Cell>
    {
        public void populationUpdate(PopulationData<? extends Cell> data)
        {
            System.out.printf("Generation %d:%d\n",
                              data.getGenerationNumber(),data.getPopulationSize());
        }
    }
}