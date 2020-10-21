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
import java.text.DecimalFormat;
// Import Array list
import java.util.ArrayList;

// Import Map
import java.util.Map;
import java.util.NavigableMap;
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
	
	// games is the total amount games played
	private static double[][] games;
	
	// differences is the score difference of each game 
	private static double[][] differences;
	
	// allTeams is all unique teams
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
		final long START = System.currentTimeMillis();
		
		// The method, readData, is called and given a string parameter for it to read the common singular value file
		readData("data/mcb2019CSV.csv");
		
		// Matrix X is created which contains the games 
		Matrix X = new Matrix(games);
		
		// Matrix Y is created which contains the differences of each game
		Matrix y = new Matrix(differences);
		
		// Matrix Xt is a transpose matrix (flipping the matrix diagonally) of X
		Matrix Xt = X.transpose();
		
		Matrix M = Xt.times(X);
		Matrix p = Xt.times(y);
		
		// Set bottom row of matrix to all 1's
		for (int i = 0; i < columns; i++) {
			M.set(columns - 1, i, 1.0);
		}
		//set bottom spot of matrix to 0
		p.set(columns - 1, 0, 0.0);
		
		Matrix r = M.solve(p);
		
		// finalRanks is a Map for each teams ranking 
		Map<Double, String> finalRanks = new TreeMap<Double, String>();
		
		// Loop through Matrix r and append the final rankings to finalRanks
		for (int i = 0; i < r.getRowDimension(); i++) {
			finalRanks.put(r.get(i, 0), allTeams.get(i));
		}
		
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		// Loop and print out the final rankings
		NavigableMap<Double, String> reversed = ((TreeMap<Double, String>) finalRanks).descendingMap();
		int count = 1;
		for (Map.Entry<Double, String> entry : reversed.entrySet()) {
		     System.out.println(count + ". " + entry.getValue() + ". Rating: " + numberFormat.format(entry.getKey()));
		     count++;
		}
		final long END = System.currentTimeMillis();
		System.out.print("Runtime: " + (END - START) + " milliseconds");
		
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
			
			// Array list for winning team of each game
			ArrayList<String> winners = new ArrayList<String>();
			
			// Array list for losing team of each game
			ArrayList<String> losers = new ArrayList<String>();
			
			// Array list for the score difference of each game
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
			
			// Loop through the entire teams to remove any @ symbols
			for (int i = 0; i < teams.size(); i++) {
				String team = teams.get(i);
				if (team.startsWith("@")) {
					teams.set(i, team.substring(1));
				}
			}
			
			// Loop through the entire winners list to remove any @ symbols
			for (int i = 0; i < winners.size(); i++) {
				String team = winners.get(i);		
				if (team.startsWith("@")) {
					winners.set(i, team.substring(1));
				}
			}
			
			// Loop through the entire losers list to remove any @ symbols
			for (int i = 0; i < losers.size(); i++) {
				String team = losers.get(i);
				if (team.startsWith("@")) {
					losers.set(i, team.substring(1));
				}
			}
			
			// Loop through the entire team and only add to distinctTeams 
			//if the team doesn't already exist
			for (int i = 0; i < teams.size(); i++) {
				if(distinctTeams.contains(teams.get(i)) == false) {
					distinctTeams.add(teams.get(i));
				}
			}
			//set games instance to a totalGames by totalTeams matrix
			games = new double[totalGames][distinctTeams.size()];
			//set differences instance to a totalGames by 1 matrix
			differences = new double[difference.size()][1];			
			// Loop through all games and add 1 for winner and -1 for loser
			for (int i = 0; i < totalGames; i++) {
				String winner = winners.get(i);
				String loser = losers.get(i);
				games[i][distinctTeams.indexOf(winner)] = 1;
				games[i][distinctTeams.indexOf(loser)] = -1;
			}
			// Loop through all games and add score difference to differences matrix
			for (int i = 0; i < difference.size(); i++) {
				differences[i][0] = difference.get(i);
			}
			//set rows, columns, and allTeams instances
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
