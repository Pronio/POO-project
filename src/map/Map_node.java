package map;

import java.util.Random;

public class Map_node implements IMap {
	

	final int x,y,dim;
	Integer[] cost = new Integer[4];
	Map_node[] next = new Map_node[4];

	Map_node(int x, int y, int dim) {
		this.x = x;
		this.y = y;
		this.dim = dim; 
	}
	
	@Override
	public int getDim() {
		return dim;
	}

	@Override
	public IMap nextNodeRandom(){
		
		int n_next = 0;
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
			return next[active[ran.nextInt(n_next)]];
		}

	}

	@Override
	public int getPosX() {
		return x;
	}

	@Override
	public int getPosY() {
		return y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
