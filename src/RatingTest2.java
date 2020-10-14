// Import Matrix
import Jama.Matrix;

/**
 * 
 * Using a second version of Massey's Method to 
 * find the rating.
 * 
 * @author pengs
 *
 */
public class RatingTest2 {

	/**
	 * 
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
