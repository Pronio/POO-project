package simulation;

import java.util.LinkedList;

import map.IMap_node;

public interface IIndividual {
	
	public boolean move();
	public IIndividual reproduction();
	public double Comfort();
	public void kill();
	//test
	public LinkedList<IMap_node> GetPath();
}
