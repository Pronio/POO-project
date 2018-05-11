package map;

/**
 * Class SpecialCostZones is used has a wrapper to store the special cost zones properties to be processed by the map constructor
 * <ul>
 * <li> xinitial - X coordinate of the initial point of the SCZ
 * <li> yinitial - Y coordinate of the initial point of the SCZ
 * <li> xfinal - X coordinate of the final point of the SCZ
 * <li> yfinal - Y coordinate of the final point of the SCZ
 * <li> cost - Cost of the SCZ
 * </ul>
 * @author Marta Marques (80882) 
 * @author Pedro Direita (81305)
 * @author Jose Fernandes (82414)
 */
public class SpecialCostZones {
public final int xinitial, yinitial, xfinal, yfinal, cost;
	
	/**
	 * Class SpecialCostZone constructor.
	 * @param xi X coordinate of the initial point of the SCZ
	 * @param yi Y coordinate of the initial point of the SCZ
	 * @param xf X coordinate of the final point of the SCZ
	 * @param yf Y coordinate of the final point of the SCZ
	 * @param c Cost of the SCZ
	 */
	public 	SpecialCostZones(int xi, int yi, int xf, int yf, int c) {
		xinitial = xi;
		yinitial = yi;
		xfinal = xf;
		yfinal = yf;
		cost = c;
	}

	@Override
	public String toString() {			
		return "Initial: ("+xinitial+", "+yinitial+") Final: ("+xfinal+", "+yfinal+") Cost: "+cost;
	}
	

}
