package map;

public class SpecialCostZones {
public final int xinitial, yinitial, xfinal, yfinal, cost;
	
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
