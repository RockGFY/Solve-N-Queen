/* 
 * Project: Project 2
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	10/12/2018
 * Description:	abstract Nqueen class
 */
package nqueen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public abstract class Nqueen implements Comparable<Nqueen>{
	
	public final int queenSize = 21; // control queen size
	
	public int fn;
	public int cost;
	public ArrayList<Integer> state;
	
	public ArrayList<Integer> arr;
	public int bestIndex;
	
	private final int maxFn = (queenSize*(queenSize-1)) / 2;
	
	public ArrayList<Integer> generateState() {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < queenSize ; i++) {
			list.add(i);
        }		
		return list;
	}
	
    public void shuffle() {
        Random rand = new Random();
        for (int i = state.size() - 1; i > 0 ; i--) {
            int j = rand.nextInt(i + 1);
            swap(i, j);
        }
    }
    public void swap(int i, int j) {
        int temp = state.get(i);
        state.set(i, state.get(j));
        state.set(j, temp);
    }
    
    public void calculateFn() {
        cost = 0;
        HashSet<Integer> set = new HashSet<>();
        
        for (int i = 0; i < state.size(); i++) {
            set.add(state.get(i));
            for (int j = i; j < state.size(); j++) {
                if (i != j) {
                    int deltaX = Math.abs(i - j);
                    int deltaY = Math.abs(state.get(i) - state.get(j));
                    if (deltaX == deltaY) {
                    	cost++;                  	                  	
                    }                      
                    
                }
            }           
        }
        cost += (state.size() - set.size());
        fn = maxFn - cost;
    }
          
    public int getFn() {
        return fn;
    }
    public int getCost() {
        return cost;
    }
    public int getBestCrossIndex() {
    	  int maxValue = arr.get(0);
    	  for(int i = 1; i < arr.size(); i++){
    	    if(arr.get(i) > maxValue){
    		  maxValue = arr.get(i);
    		  bestIndex = i;
    		}
    	  }   	
    	return bestIndex;
    }
    //for check
    public void print() {
        for (int i = 0; i < state.size(); i++) {
            int mark = state.get(i);          
            for (int j = 0; j < state.size(); j++) {
                if (j == mark) {
                	System.out.print("Q ");
                }                    
                else {
                	System.out.print("- ");
                }
                    
            }   
            System.out.println();
        }       
        System.out.println();        
    }
        
    public int compareTo(Nqueen q) {
        for (int i = 0; i < state.size(); i++) {
            if (!this.state.get(i).equals(q.state.get(i))) {
                return -1;
            }
        }
        return 0;
    }
    
}
