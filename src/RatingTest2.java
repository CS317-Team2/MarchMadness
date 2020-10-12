import Jama.Matrix;

public class RatingTest2 {

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
