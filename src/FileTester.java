// Import File, FileNotFOundException
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
// Import Scanner

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
	public static void main(String[] args) {	
		File file = new File("data/mcb2019CSV.csv");
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			ArrayList<String> teams = new ArrayList<String>();
			ArrayList<String> winners = new ArrayList<String>();
			ArrayList<String> losers = new ArrayList<String>();
			ArrayList<Integer> difference = new ArrayList<Integer>();
			ArrayList<String> distinctTeams = new ArrayList<String>();
			ArrayList<Double> teamDifference = new ArrayList<Double>();
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				winners.add(values[1]);
				teams.add(values[1]);
				losers.add(values[3]);
				teams.add(values[3]);
				difference.add(Integer.parseInt(values[2]) - Integer.parseInt(values[4]));
			}
			br.close();
			for (int i = 0; i < teams.size(); i++) {
				String team = teams.get(i);
				if (team.startsWith("@")) {
					teams.set(i, team.substring(1));
				}
			}
			for (int i = 0; i < teams.size(); i++) {
				if(distinctTeams.contains(teams.get(i)) == false) {
					distinctTeams.add(teams.get(i));
				}
			}
			for (int i = 0; i < distinctTeams.size(); i++) {
				String team = distinctTeams.get(i);
				double diff = 0;
				for (int j = 0; j < difference.size(); j++) {
					if (team.equals(winners.get(j))){
						diff = diff - difference.get(j);
					}
					else if(team.equals(losers.get(j))) {
						diff = diff + difference.get(j);
					}
					else {
						diff += 0;
					}
				}
				teamDifference.add(diff);
			}
			for (int i = 0; i < distinctTeams.size(); i++){
				System.out.println(distinctTeams.get(i));
				System.out.println(teamDifference.get(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

}
}
