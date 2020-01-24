import java.util.Random;

public class Checkpoint {
public static void main(String[] args) {
	int[][] checkpoint=new int[5][5];
	Random random=new Random();
	for (int i = 0; i < checkpoint.length; i++) {
		for (int j = 0; j < checkpoint[i].length; j++) {
			checkpoint[i][j]=random.nextInt(100);
		}
	}
	for (int i = 0; i < checkpoint.length; i++) {
		for (int j = 0; j < checkpoint[i].length; j++) {
			System.out.print(checkpoint[i][j]+" ");
		}
		System.out.println();
	}
}
}
