/* 
 * Project: Project 2
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	10/12/2018
 * Description:	Main class: to run the program.
 */

package nqueen;

import java.text.DecimalFormat;

public class Main {

	public static void main(String[] args) {
		
		int numberOfRuns = 500; // control the number of n-queens instances. 
		
		SimulatedAnnealingAlgorithm(numberOfRuns);
		
		System.out.println();
				
		geneticAlgorithm(numberOfRuns);

	}		
		
	
	public static void SimulatedAnnealingAlgorithm(int numberOfRuns) {
		SimulatedAnnealing sa =  new SimulatedAnnealing();
		
		System.out.println("For solving " + numberOfRuns + " instances of 21-Queen with Simulated Annealing Algorithm: ");
		System.out.println("Temperature is: " + sa.getTemperature() + " \t Magic number: " + sa.getMagicNumber());
				
		int totalNumSucc = 0;
		int totalSearchCost = 0;	
		
		double saAvePercent = 0;		
	    long totalTime = 0, startTime, endTime;
	    
	    DecimalFormat df = new DecimalFormat("##.##");
	    
        for (int i = 0; i < numberOfRuns; i++) { 	
        	startTime = System.currentTimeMillis();
        	if(sa.findSolution() == 1) {
        		totalNumSucc++;      		
        	}
        	
        	totalSearchCost += sa.getSearchCost();
        	endTime = System.currentTimeMillis();
        	totalTime += (endTime - startTime);   
        	
        	sa = new SimulatedAnnealing();
        }        
        
        saAvePercent = ((double)totalNumSucc / (double)numberOfRuns) * 100;
        
        System.out.println("SA succeeded: " + totalNumSucc + " out of " +numberOfRuns
				+ " (" + df.format(saAvePercent)  + "%)");
		System.out.println("SA Average Search Cost: " + totalSearchCost/numberOfRuns);
		System.out.println("SA total running time: " + totalTime);
		System.out.println("SA Average running time: " + totalTime / (long)numberOfRuns);
        
	}
	
	public static void geneticAlgorithm(int numberOfRuns) {		
		Population pop = new Population();
		System.out.println("For solving " + numberOfRuns + " instances of 21-Queen with Genetic algorithm : ");
		System.out.println("Population size: " + pop.getPopSize() + ". \t Mutate Probability: " + pop.getMutateProb() + "%.");
		
		int totalNumSucc = 0;
		int totalSearchCost = 0;	
		
		double gaAvePercent = 0;		
	    long totalTime = 0, startTime, endTime;
	    
	    DecimalFormat df = new DecimalFormat("##.##"); 
		
		for(int i = 0; i < numberOfRuns; i++) {
			startTime = System.currentTimeMillis();
			pop.gaRunner();
			endTime = System.currentTimeMillis();
						
			totalNumSucc += pop.getNumberSucc();
			totalSearchCost += pop.getSearchCost();
			totalTime += (endTime - startTime);
			
			pop = new Population();
		}
		gaAvePercent = ((double)totalNumSucc / (double)numberOfRuns) * 100;
		
		
		System.out.println("GA succeeded: " + totalNumSucc + " out of " +numberOfRuns
				+ " (" + df.format(gaAvePercent)  + "%)");
		System.out.println("GA Average Search Cost: " + totalSearchCost/numberOfRuns);
		System.out.println("GA total running time: " + totalTime);
		System.out.println("GA Average running time: " + totalTime / (long)numberOfRuns);
	}

}
