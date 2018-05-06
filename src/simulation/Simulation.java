package simulation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import map.IMap;

public class Simulation implements ISimulation {
	
	double best_cost;
	int size, cmax;
	boolean hit;
	LinkedList<IMap> best_path = new LinkedList<IMap>();
	LinkedList<Individual> individuals = new LinkedList<Individual>();
	final double tmax;
	final int maximum_individual, finalx, finaly, comfort_param, death_param, move_param, reproduction_param;

	@Override
	public void finalStats() {
		System.out.print("Path of the best fit individual = {");
		
		for(ListIterator<IMap> i = best_path.listIterator(); i.hasNext();) {
			System.out.print(i.next());
			if(i.nextIndex() != best_path.size()) {
				System.out.print(",");
			}
		}
		System.out.println("}");
	}

	@Override
	public void stats() {
		// TODO Auto-generated method stub

	}

	@Override
	public double getTmax() {
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
	@Override
	public int getCmax() {
		return cmax;
	}

	void epidemics() {
		
	}
	
	@Override
	public int getCparam() {
		return this.comfort_param;
	}
	
	@Override
	public String toString() {
		return "finalinst: "+tmax+" size: "+size+" maxpop: "+maximum_individual+" comfortsens: "+comfort_param;
	} 
	public Simulation(double tmax, int mind, int inipop, int fx, int fy, int cmax, int c_param, int d_param, int m_param, int r_param){
		this.tmax = tmax;
		maximum_individual = mind;
		finalx = fx;
		finaly = fy;
		comfort_param = c_param;
		death_param = d_param;
		move_param = m_param;
		reproduction_param = r_param;
		hit = false;
		size = inipop;
		best_cost = -1;
	}

}


