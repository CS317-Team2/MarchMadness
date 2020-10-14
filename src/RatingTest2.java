// Import Matrix
import Jama.Matrix;

/**
 * 
 * Using a pre-defined matrix to identify each teams rating. Matrices
 * were decided based on number of teams and each teams score differential.
 * A matrix will result with a rating for each team.
 * 
 * @author pengs
 *
 */
public class RatingTest2 {

	/**
	 * The main method runs the program. Two matrices are defined
	 * and the system is solved using the JAMA Matrix package.
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		double[][] teams = {{4, -1, -1, -1, -1},
				{-1, 4, -1, -1, -1}, 
				{-1, -1, 4, -1, -1}, 
				{-1, -1, -1, 4, -1}, 
				{1, 1, 1, 1, 1}};
		double[][] score = {{-40}, {28}, {15}, {103}, {0}};
		Matrix X = new Matrix(teams);
		X.print(10, 2);
		Matrix y = new Matrix(score);
		y.print(10, 2);
		Matrix r = X.solve(y);
		r.print(10, 2);
	}

}
