import java.util.Random;
import java.io.*;


public class Rand{
	public static void main(String[] args){
		Random r = new Random();
		int randCount = 100;
		if(args.length >= 1){
			randCount = Integer.parseInt(args[0]);
			System.out.println(args[0]);
		}
		long time = System.currentTimeMillis();
		try(FileWriter fw = new FileWriter(new File("out.txt")) ){
			for(int i=0; i<randCount; i++){
				fw.write(String.valueOf(r.nextInt(1000))+" ");
			}
		} catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}

		System.out.printf("time elapsed: %d\n", System.currentTimeMillis()-time);
	}
}
