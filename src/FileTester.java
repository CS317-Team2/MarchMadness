// Import File, FileNotFOundException
import java.io.*;
import java.util.ArrayList;
// Import Scanner
import java.util.Scanner;

/**
 * 
 * This is a test to manipulate the data
 * 
 * @author pengs
 *
 */
public class FileTester {

	/**
	 * 
	 * Scanning the file to create it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException{
		
		// File name
		File file = new File("C:\\Users\\pengs\\eclipse-workspace\\MarchMadness\\data\\mcb2019CSV.csv");
		
		Scanner sc = new Scanner(file);
		
		ArrayList<String> team_1 = new ArrayList<String>();
		ArrayList<String> team_2 = new ArrayList<String>();
		ArrayList<Double> team_1_score = new ArrayList<Double>();
		ArrayList<Double> team_2_score = new ArrayList<Double>();
		
		sc.useDelimiter(",");
		
		while(sc.hasNext()) {
			team_1.add(sc.nextLine());
			team_2.add(sc.nextLine());
			//team_1_score.add(sc.nextDouble());
			//team_2_score.add(sc.nextDouble());
		}
		
		System.out.println(team_1);
		System.out.println(team_2);
		// Closes the scanner
		sc.close();
		
	}

}
