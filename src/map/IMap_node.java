package map;

public interface IMap_node {
	public IMap_node nextNodeRandom(Integer cost);
	public int getPosX();
	public int getPosY();
	public int getCost(IMap_node node);
}
