package simulation;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import map.IMap;

/**
 * Simulation is the class that implements ISimulation. This implementation is used to simulate the development of a nomad population in a cyclic epidemic environment, every instance of this class stores:  
 * <ul>
 * <li> best_cost - Comfort of the most succeeded individual;
 * <li> best_path - Path taken by the most succeeded individual;
 * <li> size - Size of the population;
 * <li> nobs - Number of observations done;
 * <li> nev - Number of events executed;
 * <li> hit - If any individual has reached the final position;
 * <li> individuals - List of individuals currently alive;
 * <li> map - Map used by the nomad population during the simulation;
 * <li> tmax - Final instant of the simulation;
 * <li> maximum_individual - Maximum number of individuals before a epidemic;
 * <li> finalx - X coordinate of the point we want to reach on the end of the simulation (final point);
 * <li> finaly - Y coordinate of the point we want to reach on the end of the simulation (final point);
 * <li> confort_param - value of the sensibility used to calculate the comfort of an individual;
 * <li> death_param - value of the sensibility used to calculate new time stamps for death events;
 * <li> move_param - value of the sensibility used to calculate new time stamps for move events;
 * <li> reproduction_param - value of the sensibility used to calculate new time stamps for reproduction events;
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author José Heraldo (82414)
 */
public class Simulation implements ISimulation {
	
	double best_cost;
	int size, nobs, nev;
	boolean hit;
	
	LinkedList<?> best_path;
	LinkedList<Individual> individuals = new LinkedList<Individual>();
	
	final IMap map;
	final double tmax;
	final int maximum_individual, finalx, finaly, comfort_param, death_param, move_param, reproduction_param;

	/**
	 * Print to terminal the path of the best fit individual
	 */
	@Override
	public void finalStats() {
		System.out.print("Path of the best fit individual = {");
		
		for(ListIterator<?> i = best_path.listIterator(); i.hasNext();) {
			System.out.print(i.next());
			if(i.nextIndex() != best_path.size()) {
				System.out.print(",");
			}
		}
		System.out.println("}");
	}
	
	/**
	 * Print to terminal an intermediate report with:
	 * <ul>
	 * <li> Observation number;
	 * <li> Present instant;
	 * <li> Population size;
	 * <li> If the final point has been hit;
	 * <li> Path of the most succeeded individual so far;
	 * <li> Cost of the path of the most succeeded individual so far if it has reached the final point or the comfort of the individual case contrary
	 * </ul>
	 * @param t 
	 * Time-stamp to be printed as the present instant 
	 */
	@Override
	public void stats(double t) {
		nobs++;
		System.out.println("Observation "+nobs+":"); 
		System.out.println("		Present Instant: "+t);
		System.out.println("		Number of realized events: "+nev); 
		System.out.println("		Population Size: "+size);
		System.out.println("		Final point has been hit: "+hit); 
		System.out.println("		Path of the best fit individual:"+best_path);
		System.out.println(hit ? "		Cost: "+best_cost: "		Comfort: "+best_cost);
	}
	
	/**
	 * Get the value of the final instant 
	 * @return final instant 
	 */
	@Override
	public double GetTmax() {
		return tmax; 
	}

	/**
	 * Get the value of the sensibility used to calculate new time stamps for move events 
	 * @return move sensibility constant 
	 */
	@Override
	public int GetMove() {
		return move_param;
	}
	
	/**
	 * Get the value of the sensibility used to calculate new time stamps for death events 
	 * @return death sensibility constant 
	 */
	@Override
	public int GetDeath() {
		return death_param;
	}
	
	/**
	 * Get the value of the sensibility used to calculate new time stamps for reproduction events 
	 * @return reproduction sensibility constant 
	 */
	@Override
	public int GetReproduction() {
		return reproduction_param;
	}

	/**
	 * This method is used to
	 */
	void epidemics() {
		
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
					if(rnd.nextDouble() > aux.Comfort()){
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
					if(rnd.nextDouble() > best_five.get(0).Comfort()) {
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
	}
	
	/**
	 * Class Simulation constructor to be called when we want to start a new simulation.
	 * @param tmax Final instant of the simulation.
	 * @param mind Maximum numbers of individuals before an epidemics.
	 * @param map Map used by the nomad population to move around.
	 * @param fx X coordinate of the final point, the point we want the nomad individuals to reach
	 * @param fy Y coordinate of the final point, the point we want the nomad individuals to reach
	 * @param c_param Parameter used for individual comfort calculation
	 * @param d_param Parameter used for calculation of death time-stamps, controlling the mean number of the deaths along time
	 * @param m_param Parameter used for calculation of move time-stamps, controlling the mean number of the moves along time
	 * @param r_param Parameter used for calculation of reproduction time-stamps, controlling the mean number of the reproductions along time
	 */
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


