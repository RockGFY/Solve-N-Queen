/* 
 * Project: Project 2
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	10/12/2018
 * Description:	Chromosome class extends Nqueen
 */
package nqueen;

import java.util.ArrayList;

public class Chromosome extends Nqueen{
	
	public Chromosome() {
		state = new ArrayList<Integer>();
		state = generateState();
		shuffle();
		calculateFn();
	}
	
    public Chromosome(ArrayList<Integer> genes) {
    	this.state = genes;   
    	calculateFn();
    }
    // get the index of gene
    public Integer getGeneAt(int index) {
        return state.get(index);
    }
}
