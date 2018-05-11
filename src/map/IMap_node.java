package map;

/**
 * IMap_node interface gives methods needed to navigate through the simulation
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernades (82414)
 */
public interface IMap_node {
	
	/**
	 * Choose an adjacent map node to "this" node and return it
	 * @return Adjacent node
	 */
	public IMap_node nextNodeRandom();
	
	/**
	 * Get the X coordinate of this map node
	 * @return X coordinate of the node
	 */
	public int getPosX();
	
	/**
	 * Get the Y coordinate of this map node
	 * @return Y coordinate of the node
	 */
	public int getPosY();
	
	/**
	 * Get the cost needed to reach one map node from other adjacent map node
	 * @param node Map node we want to reach
	 * @return Cost to reach the node given by the input parameter
	 */
	public int getCost(IMap_node node);
}
