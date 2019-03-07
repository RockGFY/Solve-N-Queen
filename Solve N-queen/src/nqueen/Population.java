/* 
 * Project: Project 2
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	10/12/2018
 * Description:	Population class
 */

package nqueen;

import java.util.*;

public class Population {
	
	public int populationSize = 100; 	// control population size, right is 50
	public int mutateProb = 30; 	// control mutate probability, right is 30%
	
	private final int maxTry = 10000;		// control the number of step to get the solution.	

	public int searchCost = 0;	
	public int numOfSuc = 0;	

	Chromosome ch = new Chromosome();
	private int geneSize = ch.queenSize;	
	private int maxFn = (geneSize*(geneSize-1)) / 2;
	
	private ArrayList<Chromosome> pop;
	Random rand = new Random();
			
	public Population() {
		generatePopulation();
	}
	
	public void gaRunner() {
		for(int j = 0; j < maxTry; j++) {
			selection();
			searchCost++;
	        if(findSolution()) {	        	
	        	numOfSuc++;
	        	break;
	        } 	        	        			
		}						
	}
	
	private void generatePopulation() {
		pop = new ArrayList<Chromosome>();
		for (int i = 0; i < populationSize; i++) {
			pop.add(new Chromosome());			
		}		
		pop.sort(chromosomeComparator); // sort population from fitness low to high
	}
	
	public void selection() {			
		ArrayList<Chromosome> parent = getParent(); 
		// crossover
    	int crossPoint = rand.nextInt(geneSize) + 1;        
    	ArrayList<Chromosome> child = crossover(parent.get(0), parent.get(1), crossPoint);       
        //child.print();
        
        //mutation
    	int temp = rand.nextInt(100);
        if (temp < mutateProb) {        
        	mutate(child);
        }
       
     // remove worst parent and add the new child to the population
        pop.remove(parent.get(2));
        pop.remove(parent.get(3));
        pop.add(child.get(0));
        pop.add(child.get(1));
        pop.sort(chromosomeComparator);                 
	}
	//crossover
	private ArrayList<Chromosome> crossover(Chromosome parentOne, Chromosome parentTwo, int k) {
		ArrayList<Integer> childGeneOne = new ArrayList<>();
		ArrayList<Integer> childGeneTwo = new ArrayList<>();
		ArrayList<Chromosome> childChromosome = new ArrayList<>();
		for(int i = 0; i < geneSize; i++) {	
			if(i < k) {
				childGeneOne.add(i, parentOne.state.get(i));
				childGeneTwo.add(i, parentTwo.state.get(i));
			}
			else {
				childGeneOne.add(i, parentTwo.state.get(i));
				childGeneTwo.add(i, parentOne.state.get(i));
			}			
		}
		
		childChromosome.add(new Chromosome(validateC(childGeneOne)));
		childChromosome.add(new Chromosome(validateC(childGeneTwo)));	
		
		return childChromosome;		
	}
	
	//random pick two parents from top 10 best parents and two worst parents
	private ArrayList<Chromosome> getParent() {
		ArrayList<Chromosome> parentList = new ArrayList<>();
		int getGoodParent1, getGoodParent2;
		do {
			getGoodParent1 = rand.nextInt(10) + (populationSize-10);
			getGoodParent2 = rand.nextInt(10) + (populationSize-10);
			
		}while(getGoodParent1 == getGoodParent2);
		
		parentList.add(pop.get(getGoodParent1));
		parentList.add(pop.get(getGoodParent2));	
		//add two worst parents
		parentList.add(pop.get(0));	
		parentList.add(pop.get(1));	
		
		return parentList;
	}
	
	// remove a duplicate gene from child and give it a unused gene.
	private ArrayList<Integer> validateC(ArrayList<Integer> list) {		
		// test
//		System.out.print("+++");
//		for (int i = 0; i < list.size(); i++) {
//			System.out.print(list.get(i) + ",");
//		}
//		System.out.println();
		
		List<Integer> unused = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			unused.add(i);
		}
		Set<Integer> avail = new TreeSet<>(list);
		unused.removeAll(avail);
		ArrayList<Integer> resList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			int curVal = list.get(i);
			if (resList.contains(curVal)) {
				resList.set(resList.indexOf(curVal), unused.remove(0));
			}
			resList.add(curVal);
		}
		
		// test
//		System.out.print("---");
//		for (int i = 0; i < resList.size(); i++) {
//			System.out.print(resList.get(i) + ",");
//		}
//		System.out.println();
		return resList;
	}	
	
	public void printPop() {
		for(int i = 0; i < pop.size(); i++) {
			pop.get(i).print();			
			System.out.println("fn: " + pop.get(i).getFn());
		}
	}
	
    private void mutate(ArrayList<Chromosome> chromosome) {
        if (rand.nextDouble() < mutateProb) {
            for (Chromosome c : chromosome) {
                c.swap(rand.nextInt(geneSize), rand.nextInt(geneSize));
            }
        }
    }
    
    public boolean findSolution() {
    	for (int i = 0; i < populationSize; i++) {
    		if (pop.get(i).getFn() == maxFn) {
    			//pop.get(i).print();
    			//System.out.println("fn: " + pop.get(i).getFn());
        		return true;
        	} 
    	}    	
    	return false;
    }
    
	public int getNumberSucc() {
		return numOfSuc;
	}
	public int getSearchCost() {
		return searchCost;
	}
	public int getPopSize() {
		return populationSize;
	}
	public int getMutateProb() {
		return mutateProb;
	}
	    
    private Comparator<Chromosome> chromosomeComparator = Comparator.comparingInt(Chromosome::getFn);
    

}
