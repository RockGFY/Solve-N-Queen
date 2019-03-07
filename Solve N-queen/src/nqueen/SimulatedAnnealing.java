/* 
 * Project: Project 2
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	10/12/2018
 * Description:	SimulatedAnnealing class
 */
package nqueen;

import java.util.Random;

public class SimulatedAnnealing {
			
	public final double MAGIC = 60000;	// control the probability of accepting bad moves.
	public int temperature = 30000;		// control the temperature;
	
	public int searchCost = 0;	
    public int findSolution() {
    	SimulatedAnnealingState solState = getSolutionState();
        if (solState.getCost()== 0) {
            //solState.print();        	
            return 1;               
        }   
        return 0;
    }
	
    private SimulatedAnnealingState getSolutionState() {
    	SimulatedAnnealingState curState = new SimulatedAnnealingState();

        while (temperature-- > 0 && curState.getCost() > 0) {
        	searchCost++;
            // find a neighbor
        	SimulatedAnnealingState nextState = curState.getNextState();

            double delta = nextState.getCost() - curState.getCost();

            // make decision whether take neighbor move
            if (delta <= 0) {    // if next move is better
                curState = nextState;
            }
            else {
            	// agent's greediness to control the probability of accepting bad moves. 
            	// MAGIC is larger, the probability of accepting bad moves is smaller.  
                //decrease the probability
                double prob = Math.exp(-1.0 * MAGIC * delta / temperature);
                double randomNum = new Random().nextDouble();
                if (randomNum <= prob)
                    curState = nextState;
            }
        }
        return curState;
    }
	
	public int getSearchCost() {
		return searchCost;
	}
	public double getMagicNumber() {
		return MAGIC;
	}
	public int getTemperature() {
		return temperature;
	}
}