package toneRows;

/**
 * Will generate a matrix for a given row. The row does not need to
 * be exactly 12 notes in length
 * @author William Berthouex
 *
 */
public class MatrixGenerator {
	public static void matrixGenerator(int[] row) {
		int[] p0 = row;
		int rowLength = p0.length;
		
		// print row P0
		// The P0 array is the prime form of the row
		System.out.println("P0");
		for (int note : p0) {
			System.out.format("%-3d", note);
		}
		
		// get row I0
		// The I0 array is the inverse of P0
		int[] i0 = new int[rowLength];
		i0[0] = p0[0];
		// intervals is an array that stores the intervallic distances between the notes of row P0
		int[] intervals = new int[rowLength - 1];
		for (int x = 0; x < rowLength - 1; x++) {
			int t = p0[x + 1] - p0[x];
			intervals[x] = t;
			i0[x + 1] = Math.floorMod( (i0[x] - t), 12 );
		}
		
		// print row I0
		System.out.println("\nI0");
		for (int note : i0) {
			System.out.format("%-3d", note);
		}
		System.out.println();
		
		// add a zero to the beginning of the intervals array
		// this allow the matrix to print properly by printing P0 first
		int[] tempIntervals = new int[rowLength];
		tempIntervals[0] = 0;
		for (int i = 1; i < rowLength; i++) {
			tempIntervals[i] = intervals[i - 1];
		}
		intervals = tempIntervals;
		
		// print the matrix
		System.out.println("\nMatrix");
		for (int k = 0; k < rowLength; k++) {
			for (int j = 0; j < rowLength; j++) {
				p0[j] = Math.floorMod( (p0[j] - intervals[k]), 12 );
				System.out.format("%-3d", p0[j]);
			}
		System.out.println();
		}
	}

	public static void main(String[] args) {
		int[] toneRow = {0, 1, 5, 4, 8, 3, 9, 2, 10, 11, 7, 6};
		matrixGenerator(toneRow);
		System.out.println();
		
		int[] fifteenToneRow = {2, 8, 1, 0, 5, 6, 11, 7, 4, 9, 10, 3, 2, 7, 1};
		matrixGenerator(fifteenToneRow);
		
		int[] sevenToneRow = {0, 10, 2, 3, 5, 9, 7, 8};
		matrixGenerator(sevenToneRow);
	}
}
