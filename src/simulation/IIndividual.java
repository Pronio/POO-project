package simulation;

import java.util.LinkedList;

import map.IMap;

public interface IIndividual {
	
	public int move();
	public IIndividual reproduction();
	public double Comfort();
	public void kill();
	public boolean getDeath(); 
	public int getCost(); 
	//test
	public LinkedList<IMap> GetPath();
}
