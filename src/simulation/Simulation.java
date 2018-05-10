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
		//System.out.println("Epidemia Inicio: "+size + "Tamanho da lista: "+individuals.size());
		
		LinkedList<Individual> best_five = new LinkedList<Individual>();
		ListIterator<Individual> iter = individuals.listIterator();
		Individual aux;
		Individual bf_individual;
		Random rnd = new Random();
		
		while(iter.hasNext()){
			aux = iter.next();
			if(best_five.size() < 5){
				if(best_five.isEmpty()){
					best_five.add(aux);
					iter.remove();
				}
				else{
					boolean found = false;
					for(ListIterator<Individual> i = best_five.listIterator(); i.hasNext();){
						bf_individual = i.next();
						if(aux.Comfort() < bf_individual.Comfort()){
							i.previous();
							i.add(aux);
							iter.remove();
							found=true;
							break;
						}
					}
					if(!found){
						best_five.add(aux);
						iter.remove();
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
					boolean found = false;
					for(ListIterator<Individual> i = best_five.listIterator(); i.hasNext();){
						bf_individual = i.next();
						if(aux.Comfort() < bf_individual.Comfort()){
							i.previous();
							i.add(aux);
							iter.remove();
							found = true;
							break;
						}
					}
					if(!found){
						best_five.add(aux);
						iter.remove();
					}
					if(rnd.nextBoolean()) {
						best_five.get(0).setDeath(true);
						size--;
						best_five.remove(0);
					}else {
						iter.add(best_five.get(0));
						best_five.remove(0);
					}
				}
			}
		}
		
		for(ListIterator<Individual> i = best_five.listIterator(); i.hasNext();){
			bf_individual = i.next();
			individuals.addFirst(bf_individual);
		}
		//System.out.println("	Epidemia quantos sobraram: "+size + "Tamanho da lista: "+individuals.size());
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


