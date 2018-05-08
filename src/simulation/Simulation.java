package simulation;

import java.util.LinkedList;
import java.util.ListIterator;

import map.IMap_node;
import map.IMap;

public class Simulation implements ISimulation {
	
	double best_cost;
	int size, nobs, nev;
	boolean hit;
	
	LinkedList<IMap_node> best_path;
	LinkedList<Individual> individuals = new LinkedList<Individual>();
	
	final IMap map;
	final double tmax;
	final int maximum_individual, finalx, finaly, comfort_param, death_param, move_param, reproduction_param;

	@Override
	public void finalStats() {
		System.out.print("Path of the best fit individual = {");
		
		for(ListIterator<IMap_node> i = best_path.listIterator(); i.hasNext();) {
			System.out.print(i.next());
			if(i.nextIndex() != best_path.size()) {
				System.out.print(",");
			}
		}
		System.out.println("}");
	}

	@Override
	public void stats(double t) {
		nobs++;
		System.out.println("Observation number: "+nobs+":"); 
		System.out.println("			Present Instant: "+t);
		System.out.println("			Number of realized events: "+nev); 
		System.out.println("			Population Size: "+size);
		System.out.println("			Final point has been hit: "+hit); 
		System.out.println("			Path of the best fit individual:"+best_path);
		System.out.println(hit ? "			Cost: "+best_cost: "			Comfort: "+best_cost);
	}
	
	
	@Override
	public double GetTmax() {
		return tmax; 
	}

	@Override
	public int GetMove() {
		return move_param;
	}
	@Override
	public int GetDeath() {
		return death_param;
	}
	@Override
	public int GetReproduction() {
		return reproduction_param;
	}

	void epidemics() {
		
	}
	
	@Override
	public String toString() {
		return "finalinst: "+tmax+" size: "+size+" maxpop: "+maximum_individual+" comfortsens: "+comfort_param;
	} 
	
	public Simulation(double tmax, int mind, IMap map,int fx, int fy, int c_param, int d_param, int m_param, int r_param){
		this.tmax = tmax;
		maximum_individual = mind;
		this.map = map;
		finalx = fx;
		finaly = fy;
		comfort_param = c_param;
		death_param = d_param;
		move_param = m_param;
		reproduction_param = r_param;
		hit = false; 
		size = 0;
		best_cost = -1;
		nobs= 0; 
		nev = 0;  
	}


}


