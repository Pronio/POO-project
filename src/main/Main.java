package main;
import map.Map;
import map.Obstacles;
import map.SpecialCostZones;
import map.IMap;

public class Main {

	public static void main(String[] args) {
		
		SpecialCostZones[] spz= new SpecialCostZones[3];
		spz[0] = new SpecialCostZones(1,1,2,2,3);
		spz[1] = new SpecialCostZones(8,3,4,2,2);
		spz[2] = new SpecialCostZones(1,4,4,2,4);
		Obstacles[] obs = new Obstacles[5];
		obs[0] = new Obstacles(10,5);
		obs[1] = new Obstacles(4,2);
		obs[2] = new Obstacles(2,3);
		obs[3] = new Obstacles(2,5);
		obs[4] = new Obstacles(3,3);
		
		Map m = new Map(10,5,obs,spz,1,1);
		
		IMap node1,node2;
		
		node1 = m.start.nextNodeRandom();
		
		System.out.println(node1);
		
		node1 = node1.nextNodeRandom();
				
		System.out.println(node1);
		
		node1 = node1.nextNodeRandom();
		
		System.out.println(node1);
		
		node1 = node1.nextNodeRandom();
		
		System.out.println(node1);
		
		System.out.println();
		
		
		node2 = m.start.nextNodeRandom();
		
		System.out.println(node2);
		
		node2 = node2.nextNodeRandom();
		
		System.out.println(node2);
		
		node2 = node2.nextNodeRandom();
		
		System.out.println(node2);
		
		node2 = node2.nextNodeRandom();
		
		System.out.println(node2);
		
		return;
	}

}
