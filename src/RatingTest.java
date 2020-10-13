import Jama.Matrix;

public class RatingTest {

		public static void main(String[] args) {
			double[][] games = {{0, 0, 1, 0, -1},
					{-1, 1, 0, 0, 0}, 
					{0, 1, 0, 0, -1}, 
					{-1, 0, 0, 1, 0}, 
					{0, 0, -1, 1, 0}, 
					{1, 0, 0, 0, -1}, 
					{1, 0, -1, 0, 0}, 
					{0, 1, 0, -1, 0}, 
					{0, 0, 0, 1, -1},
					{0, -1, 1, 0, 0}};
			double[][] score = {{32}, {8}, {25}, {49}, {14}, {7}, {10}, {2}, {42}, {7}};
			Matrix X = new Matrix(games);
			X.print(10, 2);
			Matrix y = new Matrix(score);
			y.print(10, 2);
			Matrix Xt = X.transpose();
			Xt.print(10, 2);
			Matrix M = Xt.times(X);

			Matrix p = Xt.times(y);

			M.set(4, 0, 1.0);
			M.set(4, 1, 1.0);
			M.set(4, 2, 1.0);
			M.set(4, 3, 1.0);
			M.set(4, 4, 1.0);
			p.set(4, 0, 0.0);
			M.print(10, 2);
			p.print(10, 2);

			Matrix r = M.solve(p);
			r.print(10, 2);
		}
}
