package simulation;

import map.IMap;
import pec.Event;
import pec.IEvent;

import java.util.LinkedList;

public class Individual implements IIndividual {
	
	private int cost = Integer.MAX_VALUE, length = 0; 
	private boolean death = false; 
	LinkedList<IMap> path = null; 
	Simulation sim = null; 
	
	//Constructor
	public Individual (IMap start, Simulation sim){
		this.path = new LinkedList<IMap>();
		this.sim = sim; 
		this.path.add(start); 
	}
	public Individual(LinkedList<IMap> path, Simulation sim) {
		this.path = path; 
		this.sim = sim; 
	}
	
	//Getters and Setters for private attributes 
	@Override
	public int getCost(){
		return cost;
	}
	public int getLength(){
		return length;
	}
	public boolean getDeath(){
		return death;
	}

	@Override
	public int move(){
		IMap current, next;
		current = path.getLast();
		next = current.nextNodeRandom(); 
		this.path.addLast(next);
		//Missing the cost update
		if(next.getPosX() == sim.finalx && next.getPosY() == sim.finaly)
			sim.hit = true; 
		return 0;
	}

	@Override
	public IIndividual reproduction(){
		LinkedList<IMap> new_path = new LinkedList<IMap>(); 
		new_path = path;
		for(;new_path.size()<= Math.ceil(path.size()*0.90+0.1*1/this.Comfort()) && path.size()>1; new_path.removeFirst()); 
		System.out.println("Child path: "+new_path.toString()); 
		Individual child = new Individual(new_path, this.sim); 
		this.sim.addIndividual(child);
		return child;
	}
	

	@Override
	public double Comfort(){
		return Math.pow(1-(this.cost-this.length+2)/((this.sim.cmax-1)*this.length+3), this.sim.comfort_param)*
				Math.pow(1-Math.abs(this.sim.finalx - this.path.getLast().getPosX())+Math.abs(this.sim.finaly - this.path.getLast().getPosY())/
						(this.path.getFirst().getDim()+1), this.sim.comfort_param);
	}
	
	@Override
	public LinkedList<IMap> GetPath() {
		System.out.println("Path: "+path.toString());
		return path; 
	}
	@Override
	public void kill(){
		this.death = true; 
		this.sim.removeIndividual(this);
	}
	
	public int compareTo(IIndividual i){
		if(this.Comfort() > ((IIndividual)i).Comfort()) return 1;
		else if(this.Comfort() == ((IIndividual)i).Comfort()) return 0;
		else return -1;
	}

}
