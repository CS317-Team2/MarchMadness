// Import Buffered Reader
import java.io.BufferedReader;

//Import File
import java.io.File;

//Import File Not Found Exception
import java.io.FileNotFoundException;

//Import File Reader
import java.io.FileReader;

// Import I/O Exception
import java.io.IOException;

// Import Array list
import java.util.ArrayList;

// Import Map
import java.util.Map;

// Import Tree Map
import java.util.TreeMap;

// Import Matrix
import Jama.Matrix;

/**
 * 
 * The class ranks each team through Massey's Method.
 * 
 * @author pengs
 *
 */
public class RankingDriver {
	
	// games is the total amount games and each distinct team
	private static double[][] games;
	
	// differences is the difference between each teams' ranking 
	private static double[][] differences;
	
	// allTeams is the entire team
	private static ArrayList<String> allTeams;
	
	// rows is the amount rows in the Matrix
	private static int rows;
	
	// columns is the amount columns in the Matrix
	private static int columns;
	
	/**
	 * 
	 * The main method to do the calculation
	 * for Massey's Method.
	 * 
	 * @param args There is not controller input
	 */
	public static void main(String[] args) {
		
		// The method, readData, is called and given a string parameter for it to read the common singular value file
		readData("data/mcb2019CSV.csv");
		
		// Matrix X is created which contains the games 
		Matrix X = new Matrix(games);
		
		// Matrix Y is created which contains the differences of each team
		Matrix y = new Matrix(differences);
		
		// Matrix Xt is a transpose matrix (flipping the matrix diagonally) of X
		Matrix Xt = X.transpose();
		
		Matrix M = Xt.times(X);
		Matrix p = Xt.times(y);
		
		// Loop through Matrix M to set it up
		for (int i = 0; i < columns; i++) {
			M.set(columns - 1, i, 1.0);
		}
		
		p.set(columns - 1, 0, 0.0);
		
		Matrix r = M.solve(p);
		
		// finalRanks is a Map for each teams ranking 
		Map<Double, String> finalRanks = new TreeMap<Double, String>();
		
		// Loop through Matrix r and append the final rankings to finalRanks
		for (int i = 0; i < r.getRowDimension(); i++) {
			finalRanks.put(r.get(i, 0), allTeams.get(i));
		}
		
		// Loop and print out the final rankings
		for (Map.Entry<Double, String> entry : finalRanks.entrySet()) {
		     System.out.println("Rating: " + entry.getKey() + ". Team: " + entry.getValue());
		}
	}
	
	/**
	 * 
	 * The method to read data from the file.
	 * 
	 * @param filename is the name of the file
	 */
	public static void readData(String filename) {
		
		File file = new File(filename);
		
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			// Array list for teams
			ArrayList<String> teams = new ArrayList<String>();
			
			// Array list for winners
			ArrayList<String> winners = new ArrayList<String>();
			
			// Array list for losers
			ArrayList<String> losers = new ArrayList<String>();
			
			// Array list for the difference
			ArrayList<Double> difference = new ArrayList<Double>();
			
			// Array list for distinct teams
			ArrayList<String> distinctTeams = new ArrayList<String>();
			
			int totalGames = 0;
			
			// Loop through the list
			while((line = br.readLine()) != null) {
				
				// Splits the line from the comma (,)
				String[] values = line.split(",");
				winners.add(values[1]);
				teams.add(values[1]);
				losers.add(values[3]);
				teams.add(values[3]);
				
				// The values within the list of index 2 and index 4 are subtracted
				difference.add(Double.parseDouble(values[2]) - Integer.parseInt(values[4]));
				
				// The total amount of games
				totalGames++;
			}
			
			// Close the Buffered Reader
			br.close();
			
			// Loop through the entire team
			for (int i = 0; i < teams.size(); i++) {
				String team = teams.get(i);
				
				// Find if there are any teams that have @
				if (team.startsWith("@")) {
					teams.set(i, team.substring(1));
				}
			}
			
			// Loop through the entire winners
			for (int i = 0; i < winners.size(); i++) {
				String team = winners.get(i);
				
				if (team.startsWith("@")) {
					winners.set(i, team.substring(1));
				}
			}
			
			// Loop through the entire losers
			for (int i = 0; i < losers.size(); i++) {
				String team = losers.get(i);
				
				if (team.startsWith("@")) {
					losers.set(i, team.substring(1));
				}
			}
			
			// Loop through the entire team
			for (int i = 0; i < teams.size(); i++) {
				
				// Boolean if any team are distinct from each other
				if(distinctTeams.contains(teams.get(i)) == false) {
					distinctTeams.add(teams.get(i));
				}
			}
			
			games = new double[totalGames][distinctTeams.size()];
			
			differences = new double[difference.size()][1];
			
			// Loop through the entire game and append the winners and losers in games
			for (int i = 0; i < totalGames; i++) {
				String winner = winners.get(i);
				String loser = losers.get(i);
				games[i][distinctTeams.indexOf(winner)] = 1;
				games[i][distinctTeams.indexOf(loser)] = -1;
			}
			
			// Loop through the entire game and append the differences
			for (int i = 0; i < difference.size(); i++) {
				differences[i][0] = difference.get(i);
			}
			
			rows = totalGames;
			columns = distinctTeams.size();
			allTeams = distinctTeams;
		} 
		
		// Catch if the file is not found
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		// Catch if I/O is incorrect
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
