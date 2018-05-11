package map;

import java.util.Random;

/**
 * Map_node is the class that implements IMap_node. In this implementation each node of the map is represented by an object connect with the adjacent nodes, if there is an
 * obstacles the node where the obstacle is simply does not exist and the adjacent nodes to that obstacle don't have associations with the obstacle,
 * if there is a special cost zone the cost stored in each node to reach the next node is modified in order to make that special zone. Map_node has the following fields: 
 * <ul>
 * <li> x - X coordinate of this map node;
 * <li> y - Y coordinate of this map node;
 * <li> cost - Cost needed to reach an adjacent node
 * <li> next - Association to adjacent nodes
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernandes (82414)
 */
public class Map_node implements IMap_node {
	

	final int x,y;
	Integer[] cost = new Integer[4];
	Map_node[] next = new Map_node[4];

	Map_node(int x, int y, int dim) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Choose an adjacent map node to "this" node randomly with equal equal probability of choosing any of its adjacent nodes
	 * @return Adjacent node
	 */
	@Override
	public IMap_node nextNodeRandom(){
		
		int n_next = 0;
		int nextindex;
		Integer[] active = new Integer[4];
		Random ran = new Random();
		
		for(int i=0; i<4; i++) {
			if(next[i]!=null) {
				active[n_next] = i;
				n_next++;
			}
		}
		
		if(n_next == 0) {
			return this;
		}else {
			
			nextindex = active[ran.nextInt(n_next)];
			return next[nextindex];
		}

	}

	/**
	 * Get the X coordinate of this map node
	 * @return X coordinate of the node
	 */
	@Override
	public int getPosX() {
		return x;
	}

	/**
	 * Get the Y coordinate of this map node
	 * @return Y coordinate of the node
	 */
	@Override
	public int getPosY() {
		return y;
	}

	/**
	 * A Map node is represented by its coordinates
	 * @return String with Map node coordinates
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	/**
	 * Get the cost needed to reach one map node from other adjacent map node
	 * @param node Map node we want to reach
	 * @return Cost to reach the node given by the input parameter
	 */
	@Override
	public int getCost(IMap_node node) {
		
		//System.out.println("Actual: ("+x+","+y+") Next: ("+node.getPosX()+","+node.getPosY()+")");
		//System.out.println("Custos adjacentes: " + Arrays.deepToString(cost));
		
			if( this.x == ((Map_node)node).x) {
				
				if(this.y > ((Map_node)node).y) {
					return this.cost[1];
				}
				if(this.y < ((Map_node)node).y) {
					return this.cost[3];
				}
			}
			if( this.y == ((Map_node)node).y) {
				
				if(this.x > ((Map_node)node).x) {
					return this.cost[0];
				}
				if(this.x < ((Map_node)node).x) {
					return this.cost[2];
				}
			}
		
		return 0;
	}

}
