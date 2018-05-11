package simulation;

import map.IMap_node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Individual is the class that implements IIndividual interface, it overrides the interface methods stating how the actions should be delivered, each
 * new individual is an instance of this class described by:
 * <ul>
 * <li> cost - Accumulated cost by traveling along the map, since each edge has a cost of transversing associated;
 * <li> length - Number of edges traveled by this individual;
 * <li> death - Boolean value that indicates if the individual is already death or not 
 * <li> path - Map nodes transversed by this individual
 * <li> hs - HashSet used to store the Map nodes already transversed by this individual in this way the program could know efficiently if a node is already
 * on the list or not, saving time compared to searching all the list 
 * <li> sim - simulation associated with this individual  
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernandes (82414)
 */
public class Individual implements IIndividual {
	
	private int cost , length; 
	private boolean death; 
	LinkedList<IMap_node> path = null; 
	HashSet<IMap_node> hs = null;
	Simulation sim = null; 
	
	/**
	 * This class Individual constructor is used to generate a new individual, with only one node in its path, its generally used to
	 * populate the simulation in the beginning 
	 * @param start Map node (map location) where this individual has born and started is progress.
	 * @param sim Simulation where this individual is added, an individual cannot exist alone, an individual is always part of a simulation 
	 */
	public Individual (IMap_node start, Simulation sim){
		
		LinkedList<IMap_node> path = new LinkedList<IMap_node>();
		path.add(start);
		
		HashSet<IMap_node> hs = new HashSet<IMap_node>();
		hs.add(start);
			
		this.hs = hs;
		this.path = path; 
		this.sim = sim; 
		this.length = 0; 
		this.death = false;
		this.cost = 0;
		
		double conf;
		
		
		if((path.getLast().getPosX()==sim.finalx)&&(path.getLast().getPosY() == sim.finaly)) {
			if(!((sim.hit)&&(sim.best_cost<=cost))) {
				sim.best_path= (LinkedList<?>)path.clone();
				sim.hit = true;
				sim.best_cost = cost;
			}
		}else {
			if((!(sim.hit))&&(sim.best_cost<(conf=this.Comfort()))) {
				sim.best_path= (LinkedList<?>)path.clone();
				sim.hit = false;
				sim.best_cost = conf;
			}
		}
		
		sim.individuals.add(this);
		sim.size++;		
		
		if(sim.size>sim.maximum_individual)
			sim.epidemics();
	}
	
	/**
	 * This class Individual constructor is generally used to generate a new Individual from a parent
	 * @param path Is a linkedList with the path of the new individual usually a part of the parent path
	 * @param hs HashSet containing all the map nodes in the linkedList path, the HashSet is used for performance improvement 
	 * @param sim Simulation where this individual is added, an individual cannot exist alone, an individual is always part of a simulation
	 * @param cost Cost of the initial path given to this individual by its parent
	 */
	public Individual(LinkedList<IMap_node> path, HashSet<IMap_node> hs,Simulation sim, int cost) {
		
		this.hs = hs;
		this.path = path; 
		this.sim = sim; 
		this.length = path.size()-1; 
		this.death = false;
		this.cost = cost;
		
		double conf;
		
		
		if((path.getLast().getPosX()==sim.finalx)&&(path.getLast().getPosY() == sim.finaly)) {
			if(!((sim.hit)&&(sim.best_cost<=cost))) {
				sim.best_path= (LinkedList<?>)path.clone();
				sim.hit = true;
				sim.best_cost = cost;
			}
		}else {
			if((!(sim.hit))&&(sim.best_cost<(conf=this.Comfort()))) {
				sim.best_path= (LinkedList<?>)path.clone();
				sim.hit = false;
				sim.best_cost = conf;
			}
		}
		
		sim.individuals.add(this);
		sim.size++;
		
		if(sim.size>sim.maximum_individual)
			sim.epidemics();
	}

	
	/**
	 * Moves the individual to an adjacent position, and avoids loops in its path
	 * @return true if the move was successful, false if the individual is already death
	 */
	@Override
	public boolean move(){
		IMap_node next, node;
		double conf;
		
		if(this.death) {
			return false;
		}
		
		sim.nev++;
				
		next = path.getLast().nextNodeRandom(); 
		
		
		if(hs.add(next)){
			cost += path.getLast().getCost(next);	
			path.addLast(next);
			//System.out.println("MOVE: Next= "+next+" Cost to be added = "+cost);
			length++;
			if((path.getLast().getPosX()==sim.finalx)&&(path.getLast().getPosY() == sim.finaly)) {
				if(!(sim.hit)||(sim.hit&&(sim.best_cost>this.cost))){
					sim.best_path= (LinkedList<?>)path.clone();
					sim.hit = true;
					sim.best_cost = cost;
				}
			}else {
				if((!(sim.hit))&&(sim.best_cost<(conf=this.Comfort()))) {
					sim.best_path= (LinkedList<?>)path.clone();
					sim.hit = false;
					sim.best_cost = conf;
				}
			} 
		}else {
			for(Iterator<IMap_node> i = path.descendingIterator();(node = i.next())==next;){
				i.remove();
				hs.remove(node);
				length--;
				cost -= path.getLast().getCost(node);
			}
		}
		//System.out.println("MOVE: New Path: "+path+" Length: "+path.size()); 
		return true;
	}

	/**
	 * Generate a new individual, based on a parent individual, this new individual shares part of its path with his parent, the
	 * percentage of path inherited is calculated based on the parent comfort
	 * @return The newly generated individual 
	 */
	@Override
	public IIndividual reproduction(){
		
		if(death) {
			return null;
		}
		
		sim.nev++;
		
		IMap_node curr,prev=null;
		int index_copy, new_cost=0;
		LinkedList<IMap_node> new_path = new LinkedList<IMap_node>();
		HashSet<IMap_node> new_hs = new HashSet<IMap_node>();
		
		index_copy = (int) (Math.ceil(path.size()*(0.9+0.1*this.Comfort()))-1);
		//System.out.println("Comfort of the parent: "+this.Comfort()+" Parent path size: "+path.size()+" Index copy: "+index_copy);
		
		for(ListIterator<IMap_node> i = path.listIterator(); i.nextIndex() <= index_copy;) {
			curr = i.next();
			new_path.addLast(curr);
			new_hs.add(curr);
			if(prev!=null) {
				new_cost+=prev.getCost(curr);
				//System.out.println("New cost: "+new_cost);
			}
			prev = curr;
		}
		//System.out.println("REPRODUCTION: New Path: "+new_path+" Length: "+new_path.size()); 
		IIndividual child = new Individual(new_path, new_hs , this.sim, new_cost);  
		return child;
	}
	
	/**
	 * Calculate the individual comfort based on the map dimensions and characteristics, simulation characteristics, and path taken
	 * by the individual  
	 * @return Value of comfort
	 */
	@Override
	public double Comfort(){ 
		/*if(cost<length) {
			System.out.println("Cost: "+cost+" Length: "+length+" Path: "+path);
			System.exit(-3);
		}*/
		return Math.pow(1-((double)(this.cost-this.length+2))/((double)(this.sim.map.getCostMax()-1)*this.length+3), this.sim.comfort_param)*
				Math.pow(1-((double)Math.abs(this.sim.finalx - path.getLast().getPosX())+(double)Math.abs(this.sim.finaly - path.getLast().getPosY()))/
						(double)(this.sim.map.getM()+this.sim.map.getN()+1), this.sim.comfort_param);
	}
	
	/**
	 * Prints out if the individual is death or alive
	 * @return String describing the individual
	 */
	@Override
	public String toString() {
		return " is "+ (death? "dead": "alive");
		//return "comfort= "+this.Comfort()+" cost="+cost+", length="+length+", death="+death+", path="+path;
	}
	
	/**
	 * Kills the Individual, this is made by changing the flag death to true and removing it from the individuals list inside the simulation associated with this individual
	 */
	@Override
	public void kill(){
		
		if(this.death) {
			return;
		}
		
		sim.nev++;
		this.death = true; 
		this.sim.individuals.remove(this);
		sim.size--;
		return;
	}

	/**
	 * Setter used to set the flag death, one of the properties of the individual to true, this is used by the simulation epidemics
	 */
	void setDeath(boolean death) {
		this.death = death;
	}


}
