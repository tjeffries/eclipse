package athenahealth;

public class Haarstogram {
	
	private static int[][] grid = {{8,3,-2,8},{3,7,-2,0,},{3,8,9,3},{1,9,9,3}};
	public static void main(String args[]){
		System.out.println(solve(2,24));
	}
	
	private static int solve(int subSize, int target){
		int sols = 0;
		int last = 0;
		for(int offsety=0; offsety<grid.length+1-subSize; offsety++){
			for(int offsetx=0; offsetx<grid.length+1-subSize; offsetx++){
				int total = 0;
				if(offsetx==0){
					for(int y=0; y<subSize; y++){
						for(int x=0; x<subSize; x++){
							total += grid[y+offsety][x+offsetx];
							last = total;
						}
					}
				} else {
					total = last;
					for(int y=0; y<subSize; y++){
						total -= grid[y+offsety][offsetx-1];
						total += grid[y+offsety][offsetx+subSize-1];
					}
					last = total;
				}
				sols = (total==target) ? ++sols : sols;
			}
		}
				
		return sols;
	}
}
