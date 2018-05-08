package map;

public class Map implements IMap{
	private final int n, m, costMax;
	private final Map_node start;

	public Map(int n, int m, Obstacles[] obs, SpecialCostZones[] spz,int cmax ,int startx, int starty) {
		
		//Create a matrix with the size of the map
		Map_node[][] map= new Map_node[n][m];
		
		//Create Map_node to populate the matrix and connect them each other
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				//Creation of a new Map_node
				map[i][j] = new Map_node(i+1,j+1, n+m);
				map[i][j].cost[0] = 0;
				map[i][j].cost[1] = 0;
				map[i][j].cost[2] = 0;
				map[i][j].cost[3] = 0;
				
				//Connect node with position (i,j) to node with position (i-1,j) and vice-versa
				if(i!=0) {
					map[i][j].next[0] = map[i-1][j];
					map[i-1][j].next[2] = map[i][j];
					map[i][j].cost[0] = 1;
					map[i-1][j].cost[2] = 1;
				}
				
				//Connect node with position (i,j) to node with position (i,j-1) and vice-versa
				if(j!=0) {
					map[i][j].next[1] = map[i][j-1];
					map[i][j-1].next[3] = map[i][j];
					map[i][j].cost[1] = 1;
					map[i][j-1].cost[3] = 1;
				}
			}
		}
		
		//Create rectangular zones with higher connection cost
		if(spz!=null) {
			for(int i=0; i<spz.length; i++) {
				
				//Verify if the special cost zone is inside the map
				if((spz[i].xinitial > n) || (spz[i].yinitial > m) || (spz[i].xfinal > n) || (spz[i].yfinal > m)) {
					System.out.println("Error: A declared special cost zone is out of the map");
					System.exit(1);
				}
			
				//Convert from the notation where the lowest coordinate is (1,1) to the notation where the lowest coordinate is (0,0)
				int xinitial = spz[i].xinitial-1;
				int yinitial = spz[i].yinitial-1;
				int xfinal = spz[i].xfinal-1;
				int yfinal = spz[i].yfinal-1;
				
				//Change the cost of the edges along y
				for(int x=xinitial; x<xfinal; x++) {
					if(map[x][yinitial].cost[2] < spz[i].cost) 
						map[x][yinitial].cost[2] = spz[i].cost;
					if(map[x+1][yinitial].cost[0] < spz[i].cost) 
						map[x+1][yinitial].cost[0] = spz[i].cost;
					
					if(map[x][yfinal].cost[2] < spz[i].cost) 
						map[x][yfinal].cost[2] = spz[i].cost;
					if(map[x+1][yfinal].cost[0] < spz[i].cost) 
						map[x+1][yfinal].cost[0] = spz[i].cost;	
				}
				for(int x=xinitial; x>xfinal; x--) {
					if(map[x][yinitial].cost[0] < spz[i].cost) 
						map[x][yinitial].cost[0] = spz[i].cost;
					if(map[x-1][yinitial].cost[2] < spz[i].cost) 
						map[x-1][yinitial].cost[2] = spz[i].cost;
					
					if(map[x][yfinal].cost[0] < spz[i].cost) 
						map[x][yfinal].cost[0] = spz[i].cost;
					if(map[x-1][yfinal].cost[2] < spz[i].cost) 
						map[x-1][yfinal].cost[2] = spz[i].cost;
				}
				
				//Change the cost of the edges along x
				for(int y=yinitial; y<yfinal; y++) {
					if(map[xinitial][y].cost[3] < spz[i].cost) 
						map[xinitial][y].cost[3] = spz[i].cost;
					if(map[xinitial][y+1].cost[1] < spz[i].cost) 
						map[xinitial][y+1].cost[1] = spz[i].cost;
					
					if(map[xfinal][y].cost[3] < spz[i].cost) 
						map[xfinal][y].cost[3] = spz[i].cost;
					if(map[xfinal][y+1].cost[1] < spz[i].cost) 
						map[xfinal][y+1].cost[1] = spz[i].cost;
				}
				for(int y=yinitial; y>yfinal; y--) {
					if(map[xinitial][y].cost[1] < spz[i].cost) 
						map[xinitial][y].cost[1] = spz[i].cost;
					if(map[xinitial][y-1].cost[3] < spz[i].cost) 
						map[xinitial][y-1].cost[3] = spz[i].cost;
					
					if(map[xfinal][y].cost[1] < spz[i].cost) 
						map[xfinal][y].cost[1] = spz[i].cost;
					if(map[xfinal][y-1].cost[3] < spz[i].cost) 
						map[xfinal][y-1].cost[3] = spz[i].cost;
				}	
			}
		}
		
		//Remove nodes that are considered obstacles
		if(obs!=null) {
			for(int i=0; i<obs.length; i++) {
				
				//Verify if the obstacle is inside the map
				if((obs[i].x > n) || (obs[i].y > m)) {
					System.out.println("Error: A declared obstacle is out of the map");
					System.exit(1);
				}
				
				//Convert from the notation where the lowest coordinate is (1,1) to the notation where the lowest coordinate is (0,0)
				int obsx = obs[i].x-1;
				int obsy = obs[i].y-1;
				
				//Remove connection between node with position (obs.x,obs.y) and node with position (obs.x-1,obs.y) and vice-versa
				if(obsx != 0) {
					map[obsx][obsy].next[0] = null;
					map[obsx-1][obsy].next[2] = null;
				}
				
				//Remove connection between node with position (obs.x,obs.y) and node with position (obs.x,obs.y-1) and vice-versa
				if(obsy != 0) {
					map[obsx][obsy].next[1] = null;
					map[obsx][obsy-1].next[3] = null;
				}
				
				//Remove connection between node with position (obs.x,obs.y) and node with position (obs.x+1,obs.y) and vice-versa
				if(obsx != n-1) {
					map[obsx][obsy].next[2] = null;
					map[obsx+1][obsy].next[0] = null;
				}
				
				//Remove connection between node with position (obs.x,obs.y) and node with position (obs.x,obs.y+1) and vice-versa
				if(obsy != m-1) {
					map[obsx][obsy].next[3] = null;
					map[obsx][obsy+1].next[1] = null;
				}
					
			}
		}
		
		this.n = n;
		this.m = m;
		this.costMax = cmax;
		start = map[startx-1][starty-1];
		printMap(map);
	}
	
	public void printMap(Map_node[][] map) {
		
		for(int j=m-1; j>=0;j--) {
			for(int i=0; i<n; i++) {
				System.out.print(" ");
				

				System.out.print(map[i][j].cost[3]);

				
				System.out.print(" ");
			}
			System.out.println();
			for(int i=0; i<n; i++) {
				
		
				System.out.print(map[i][j].cost[0]);

				
				if(!((map[i][j].next[0]==null)&&(map[i][j].next[1]==null)&&(map[i][j].next[2]==null)&&(map[i][j].next[3]==null))) {
					System.out.print("+");
				}else {
					System.out.print("x");
				}
				
				System.out.print(map[i][j].cost[2]);


			}
			System.out.println();
			for(int i=0; i<n; i++) {
				System.out.print(" ");
				
	
				System.out.print(map[i][j].cost[1]);


				System.out.print(" ");
			}
			System.out.println();
		}
		return;
	}

	@Override
	public int getN() {
		return n;
	}

	@Override
	public int getM() {
		return m;
	}

	@Override
	public int getCostMax() {
		return costMax;
	}

	@Override
	public IMap_node getStart() {
		return start;
	}
	
}
