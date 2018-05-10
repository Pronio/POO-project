package simulation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

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

	/**
	 * This method is used to
	 */
	void epidemics() {
		LinkedList<Individual> best_five = new LinkedList<Individual>();
		Iterator<Individual> iter = individuals.iterator();
		Individual aux;
		Random rnd = new Random();
		
		//Substituir comparações por Comparator da classe Individual
		while(iter.hasNext()){
			aux = iter.next();
			if(best_five.size() < 5){
				if(best_five.isEmpty()){
					best_five.add(aux);
				}
				else{
					int i;
					boolean found = false;
					for(i=0; i<best_five.size();i++){
						if(aux.Comfort() < best_five.get(i).Comfort()){
							found = true;
							break;
						}
					}
					if(found){
						best_five.add(i, aux);
					}
					else{
						best_five.add(aux);
					}					
				}
			}
			else{
				if(aux.Comfort() <= best_five.get(0).Comfort()){
					if(rnd.nextBoolean()){
						aux.setDeath(true);
						iter.remove();
						size--; 
					}
				}
				else{
					int i;
					boolean found = false;
					for(i=1; i<best_five.size(); i++){
						if(aux.Comfort() < best_five.get(i).Comfort()){
							found = true;
							break;
						}
					}
					if(found){
						best_five.add(i, aux);
					}else{
						best_five.add(aux);
					}
					if(rnd.nextBoolean()) {
						best_five.get(0).setDeath(true);
						iter.remove();
						size--; 
					}
					best_five.remove(0);
				}
			}
		}		
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


