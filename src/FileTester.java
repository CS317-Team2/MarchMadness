// Import File, FileNotFOundException
import java.io.*;

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
		File file = new File("C:\\Users\\pengs\\eclipse-workspace\\MarchMadness\\data\\mcb2019CSV.csv");
		Scanner sc = new Scanner(file);
		sc.close();
		//TODO finish the file tester
	}

}
