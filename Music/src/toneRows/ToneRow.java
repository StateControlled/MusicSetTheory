package toneRows;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Will generate a random tone row, that is, a non-repeating ordering of a set of pitch-classes.
 * In this instance, it is the twelve notes of the chromatic scale.
 * Each pitch is represented by a number, 0 - 11, with 0 representing "C", 1 representing
 * "C#", etc.
 * @author William Berthouex
 */
public class ToneRow {
	/**
	 * Will generate a random tone row.
	 */
	public static void toneRow() {
		int[] p0 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		shuffleArray(p0);
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
		// intervals is an array that stores the intervallic distance between the notes of row P0
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

	/**
	 * Will randomly re-order the elements of the given tone row
	 * @param row
	 */
	private static void shuffleArray(int[] row) {
		Random randomNumber = ThreadLocalRandom.current();
		for (int i = row.length - 1; i > 0; i--) {
			int index = randomNumber.nextInt(i + 1);
			int a = row[index];
			row[index] = row[i];
			row[i] = a;
		}
	}

	public static void main(String[] args) {
		toneRow();
	}
}
