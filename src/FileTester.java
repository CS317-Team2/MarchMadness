// Import File, FileNotFOundException
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
		
		ArrayList<String> date_of_game = new ArrayList<String>();
		ArrayList<String> team_1 = new ArrayList<String>();
		ArrayList<String> team_2 = new ArrayList<String>();
		ArrayList<Integer> team_1_score = new ArrayList<Integer>();
		ArrayList<Integer> team_2_score = new ArrayList<Integer>();
		
		sc.useDelimiter(",");
		
		try {
			while(sc.hasNext()) {
				//System.out.println(sc.next());
				
				// Date of the game
				String date = sc.next();
				date_of_game.add(date);
				System.out.println(date_of_game);
				
				// First team name
				String first_team_name = sc.next();
				team_1.add(first_team_name);
				System.out.println(team_1);
				
				// First team score
				int first_team_score = sc.nextInt();
				team_1_score.add(first_team_score);
				System.out.println(team_1_score);
				
				// Second team name
				String second_team_name = sc.next();
				team_2.add(second_team_name);
				System.out.println(team_2);
				
				// Second team score
				int second_team_score = sc.nextInt();
				team_2_score.add(second_team_score);
				System.out.println(team_2_score);
				
				String line = sc.next();
			}
		}
		
		catch(InputMismatchException ex) {
			System.out.println("Exception: Find the mismatch input!");
		}
		
		// Closes the scanner
		sc.close();		
		
	}

}
