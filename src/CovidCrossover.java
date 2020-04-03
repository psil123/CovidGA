import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

public class CovidCrossover extends AbstractCrossover<Cell> 
{

	public CovidCrossover(int crossoverPoints)
	{
		super(crossoverPoints);
	}
    int LCSubStr(char X[], char Y[], int m, int n)  
    { 
        // Create a table to store lengths of longest common suffixes of 
        // substrings. Note that LCSuff[i][j] contains length of longest 
        // common suffix of X[0..i-1] and Y[0..j-1]. The first row and 
        // first column entries have no logical meaning, they are used only 
        // for simplicity of program 
        int LCStuff[][] = new int[m + 1][n + 1]; 
        int result = 0;  // To store length of the longest common substring 
          
        // Following steps build LCSuff[m+1][n+1] in bottom up fashion 
        for (int i = 0; i <= m; i++)  
        { 
            for (int j = 0; j <= n; j++)  
            { 
                if (i == 0 || j == 0) 
                    LCStuff[i][j] = 0; 
                else if (X[i - 1] == Y[j - 1]) 
                { 
                    LCStuff[i][j] = LCStuff[i - 1][j - 1] + 1; 
                    result = Integer.max(result, LCStuff[i][j]); 
                }  
                else
                    LCStuff[i][j] = 0; 
            } 
        } 
        return result; 
    } 
	@Override
	protected List<Cell> mate(Cell parent1,Cell parent2, int numberOfCrossoverPoints,Random rng) 
	{
		List<Cell> offspring = new ArrayList<Cell>();
		if((parent1.type=='v' && parent2.type=='v') || (parent1.type=='a' && parent2.type=='a'))
		{
			offspring.add(parent1);
			offspring.add(parent2);
		}
		else
		{
			String s1,s2;
			if(parent1.type=='v')
			{
				s1=parent1.rnaseq;
				s2=parent2.rnaseq;
			}
			else
			{
				s2=parent1.rnaseq;
				s1=parent2.rnaseq;
			}
			int c=this.LCSubStr(s1.toCharArray(),s2.toCharArray(),s1.length(),s2.length());
			Cell temp=new Cell(s1, 'v');
			temp.removed=c;
			offspring.add(temp);
			temp=new Cell(s2, 'a');
			offspring.add(temp);
		}
		return offspring;
	}

}