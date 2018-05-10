package simulation;

import map.IMap_node;
import map.IMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Individual implements IIndividual {
	
	private int cost , length; 
	private boolean death; 
	LinkedList<IMap_node> path = null; 
	HashSet<IMap_node> hs = null;
	Simulation sim = null; 
	
	//Constructor
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
				sim.best_path= (LinkedList<IMap_node>)path.clone();
				sim.hit = true;
				sim.best_cost = cost;
			}
		}else {
			if((!(sim.hit))&&(sim.best_cost<(conf=this.Comfort()))) {
				sim.best_path= (LinkedList<IMap_node>)path.clone();
				sim.hit = false;
				sim.best_cost = conf;
			}
		}
		
		sim.individuals.add(this);
		sim.size++;		
		
		if(sim.size>sim.maximum_individual)
			sim.epidemics();
	}
	
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
				sim.best_path= (LinkedList<IMap_node>)path.clone();
				sim.hit = true;
				sim.best_cost = cost;
			}
		}else {
			if((!(sim.hit))&&(sim.best_cost<(conf=this.Comfort()))) {
				sim.best_path= (LinkedList<IMap_node>)path.clone();
				sim.hit = false;
				sim.best_cost = conf;
			}
		}
		
		sim.individuals.add(this);
		sim.size++;
		
		if(sim.size>sim.maximum_individual)
			sim.epidemics();
	}

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
					sim.best_path= (LinkedList<IMap_node>)path.clone();
					sim.hit = true;
					sim.best_cost = cost;
				}
			}else {
				if((!(sim.hit))&&(sim.best_cost<(conf=this.Comfort()))) {
					sim.best_path= (LinkedList<IMap_node>)path.clone();
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
	
	@Override
	public String toString() {
		return " is "+ (death? "dead": "alive");
		//return "comfort= "+this.Comfort()+" cost="+cost+", length="+length+", death="+death+", path="+path;
	}
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
	
	public int compareTo(IIndividual i){
		if(this.Comfort() > i.Comfort()) return 1;
		else if(this.Comfort() == i.Comfort()) return 0;
		else return -1;
	}
	
	void setDeath(boolean death) {
		this.death = death;
	}

	//para efeitos de teste
	@Override
	public LinkedList<IMap_node> GetPath() {
		System.out.println("Path: "+path.toString());
		return path; 
	}

}
