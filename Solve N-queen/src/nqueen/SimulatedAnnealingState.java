/* 
 * Project: Project 2
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	10/12/2018
 * Description:	SimulatedAnnealingState class extends Nqueen
 */
package nqueen;
import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealingState extends Nqueen{
		
	public SimulatedAnnealingState() {
		state = new ArrayList<Integer>();
		state = generateState(); // create a state = {0, 1, 2, .... , to queen size}
		shuffle();					// shuffle the state
		calculateFn();
	}

    public SimulatedAnnealingState(ArrayList<Integer> list) {
        this.state = list;
        calculateFn();
    }
	
	public SimulatedAnnealingState getNextState() {
		Random rand = new Random();
		ArrayList<Integer> newState = new ArrayList<>();
        int randIndex = rand.nextInt(state.size());
        int randValue = rand.nextInt(state.size());

        for (int i = 0; i < state.size(); i++) {
            if (randIndex == i)
            	newState.add(randValue);
            else
            	newState.add(state.get(i));
        }

        SimulatedAnnealingState s = new SimulatedAnnealingState(newState);
        return s;
        
		
	}

}
