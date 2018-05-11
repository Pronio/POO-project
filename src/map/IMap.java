package map;

/**
 * IMap interface is used to get general properties from a certain map
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernades (82414)
 */
public interface IMap {
	
	/**
	 * Get the number of columns of the map
	 * @return Number of columns of the map
	 */
	public int getN();
	
	/**
	 * Get the number of rows of the map
	 * @return Number of rows of the map
	 */
	public int getM();
	
	/**
	 * Get the maximum cost possible of an edge of this map
	 * @return Maximum cost of an edge
	 */
	public int getCostMax();
	
	/**
	 * Get the start point of the map, usually used in simulations to point where the simulation is going to start
	 * @return Start Node of the map
	 */
	public IMap_node getStart();
}
