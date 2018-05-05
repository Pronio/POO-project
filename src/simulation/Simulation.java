package simulation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import map.IMap;

public class Simulation implements ISimulation {
	
	double best_cost;
	int size;
	boolean hit;
	LinkedList<IMap> best_path = new LinkedList<IMap>();
	LinkedList<Individual> individuals = new LinkedList<Individual>();
	final double tmax;
	final int maximum_individual, finalx, finaly, confort_param, death_param, move_param, reproduction_param;

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
	
	public Simulation(double tmax, int mind, int fx, int fy, int c_param, int d_param, int m_param, int r_param){
		this.tmax = tmax;
		maximum_individual = mind;
		finalx = fx;
		finaly = fy;
		confort_param = c_param;
		death_param = d_param;
		move_param = m_param;
		reproduction_param = r_param;
		hit = false;
		size = 0;
		best_cost = -1;
		
	}

}
