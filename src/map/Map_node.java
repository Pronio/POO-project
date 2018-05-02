package map;

public class Map_node implements IMap {
	
	final int x,y;
	Integer[] cost = new Integer[4];
	Map_node[] next = new Map_node[4];

	Map_node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public IMap nextNodeRandom(IMap node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPosX(IMap node) {
		return x;
	}

	@Override
	public int getPosY(IMap node) {
		return y;
	}

}
