package map;

import java.util.Random;

public class Map_node implements IMap_node {
	

	final int x,y;
	Integer[] cost = new Integer[4];
	Map_node[] next = new Map_node[4];

	Map_node(int x, int y, int dim) {
		this.x = x;
		this.y = y;
	}
	

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
