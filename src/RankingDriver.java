import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import Jama.Matrix;

public class RankingDriver {
	private static double[][] games;
	private static double[][] differences;
	private static ArrayList<String> allTeams;
	private static int rows;
	private static int columns;

	public static void main(String[] args) {
		readData("data/mcb2019CSV.csv");
		Matrix X = new Matrix(games);
		Matrix y = new Matrix(differences);
		Matrix Xt = X.transpose();
		Matrix M = Xt.times(X);
		Matrix p = Xt.times(y);
		for (int i = 0; i < columns; i++) {
			M.set(columns - 1, i, 1.0);
		}
		p.set(columns - 1, 0, 0.0);
		Matrix r = M.solve(p);
		Map<Double, String> finalRanks = new TreeMap<Double, String>();
		for (int i = 0; i < r.getRowDimension(); i++) {
			finalRanks.put(r.get(i, 0), allTeams.get(i));
		}
		for (Map.Entry<Double, String> entry : finalRanks.entrySet()) {
		     System.out.println("Rating: " + entry.getKey() + ". Team: " + entry.getValue());
		}
	}
	
	public static void readData(String filename) {
		File file = new File(filename);
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			ArrayList<String> teams = new ArrayList<String>();
			ArrayList<String> winners = new ArrayList<String>();
			ArrayList<String> losers = new ArrayList<String>();
			ArrayList<Double> difference = new ArrayList<Double>();
			ArrayList<String> distinctTeams = new ArrayList<String>();
			int totalGames = 0;
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				winners.add(values[1]);
				teams.add(values[1]);
				losers.add(values[3]);
				teams.add(values[3]);
				difference.add(Double.parseDouble(values[2]) - Integer.parseInt(values[4]));
				totalGames++;
			}
			br.close();
			for (int i = 0; i < teams.size(); i++) {
				String team = teams.get(i);
				if (team.startsWith("@")) {
					teams.set(i, team.substring(1));
				}
			}
			for (int i = 0; i < winners.size(); i++) {
				String team = winners.get(i);
				if (team.startsWith("@")) {
					winners.set(i, team.substring(1));
				}
			}
			for (int i = 0; i < losers.size(); i++) {
				String team = losers.get(i);
				if (team.startsWith("@")) {
					losers.set(i, team.substring(1));
				}
			}
			for (int i = 0; i < teams.size(); i++) {
				if(distinctTeams.contains(teams.get(i)) == false) {
					distinctTeams.add(teams.get(i));
				}
			}
			games = new double[totalGames][distinctTeams.size()];
			differences = new double[difference.size()][1];
			for (int i = 0; i < totalGames; i++) {
				String winner = winners.get(i);
				String loser = losers.get(i);
				games[i][distinctTeams.indexOf(winner)] = 1;
				games[i][distinctTeams.indexOf(loser)] = -1;
			}
			for (int i = 0; i < difference.size(); i++) {
				differences[i][0] = difference.get(i);
			}
			rows = totalGames;
			columns = distinctTeams.size();
			allTeams = distinctTeams;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
