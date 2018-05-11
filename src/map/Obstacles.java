package map;

/**
 * Class Obstacles is used has a wrapper to store the obstacles coordinates to be processed by the map constructor
 * <ul>
 * <li> x - X coordinate of the obstacle
 * <li> y - Y coordinate of the obstacle
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernandes (82414)
 */
public class Obstacles {
	public final int x,y;
	
	/**
	 * Class Obstacle constructor.
	 * @param x X coordinate of the obstacle.
	 * @param y Y coordinate of the obstacle
	 */
	public Obstacles(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Show the coordinates of a certain obstacle.
	 * @param String describing the obstacle.
	 */
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

}
