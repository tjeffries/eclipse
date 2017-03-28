
public class Change {
	
	private static int[] denominations =  {100,50,25,10,5,1};
	
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("invalid arguments. usage: Change changeAmount");
			System.exit(1);
		}
		
		makeChange(Integer.parseInt(args[1]), denominations);
		
	}
	
	private static int[] makeChange(int amount, int[] denoms){
		
		int counts[] = new int[denoms.length+1];
		
		//add denomination counts to array for return
		int[] tmp = new int[counts[counts.length-1]];
		int offset = 0;
		for(int i=0; i<counts.length-1; i++){
			while(counts[i] != 0){
				tmp[i+offset] = denoms[i];
				offset++;
			}
		}
		
		return tmp;
	}

}
