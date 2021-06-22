package setTheory;
/**
 * For musical set theory.
 * Assumes a western equal-tempered tuning system (12-TET). Pitch-class sets must be entered as integer arrays using numbers 0 - 11.
 * @author William Berthouex
 *
 */
public class SetTheoryOperations {
	/**
	 * Transposes a given pitch set up or down by a given interval. In mathematical terms this 
	 * operation is equivalent to a translation.
	 * @param pitchSet	The set of pitch classes to be transposed.
	 * @param interval	The interval used to modify the set. May be positive or negative.
	 * @return transposedSet
	 */
	public static int[] transpose (int[] pitchSet, int interval) {
		validate(pitchSet);
		int setLength = pitchSet.length;
		int[] transposedSet = new int[setLength];
		
		for (int i = 0; i < setLength; i++) {
			transposedSet[i] = Math.floorMod( (pitchSet[i] + interval), 12 );
		}
		
		return transposedSet;
	}
	
	/**
	 * Will invert a given pitch set around the first pitch-class of the set.
	 * In mathematical terms this operation is equivalent to a reflection.
	 * @param pitchSet	The set of pitch classes to be inverted.
	 * @return inverse	The inverse of the pitch set.
	 */
	public static int[] inverse (int[] pitchSet) {
		validate(pitchSet);
		int setLength = pitchSet.length;

		int[] inverse = new int[setLength];
		inverse[0] = pitchSet[0];
		for (int m = 1; m < setLength; m++) {
			int t = pitchSet[m] - pitchSet[m - 1];
			inverse[m] = Math.floorMod( inverse[m - 1] - t, 12);
		}

		return inverse;
	}
	
	/**
	 * Will return the retrograde (reverse) of a given pitch set.
	 * @param pitchSet
	 * @return
	 */
	public static int[] retrograde (int[] pitchSet) {
		validate(pitchSet);
		int setLength = pitchSet.length;
		int[] retrograde = new int[setLength];
		int index = 0;
		for (int k = setLength - 1; k > 0; k--) {
			retrograde[index++] = pitchSet[k];
		}
		return retrograde;
	}
	
	/**
	 * The inverse of the pitch set in reverse order.
	 * @param pitchSet
	 * @return retrograde-inverse
	 */
	public static int[] retrogradeInverse (int[] pitchSet) {
		validate(pitchSet);
		return retrograde(inverse(pitchSet));
	}
	
	/**
	 * Works like the invert method, but will reflect the pitch set around a pitch other than the
	 * first pitch of the set. Maintains inversional equivalence.
	 * @param pitchSet
	 * @param pivotNote	The note to use as the center of the reflection represented as an integer.
	 * @return
	 */
	public static int[] inversionAround (int[] pitchSet, int pivotNote) {
		validate(pitchSet);
		int[] inversion = inverse(pitchSet);
		int distance = Math.floorMod(pitchSet[0] - pivotNote, 12);
		inversion = transpose(inversion, 2 * distance);
		return inversion;
	}
	
	public static int[] complement (int[] pitchSet) {
		// TODO
		validate(pitchSet);
		return null;
	}
	
	public static int[] multiply (int[] pitchSet, int multiplier) {
		// TODO
		validate(pitchSet);
		return null;
	}
	
	public static int[] normalForm (int[] pitchSet) {
		// TODO
		validate(pitchSet);
		return null;
	}
	
	public static int[] normalOrder (int[] pitchSet) {
		// TODO
		validate(pitchSet);
		return null;
	}
	
	/**
	 * Prints the set.
	 * @param pitchSet
	 */
	public static void showSet(int[] pitchSet) {
		System.out.print("< ");
		for (int n = 0; n < pitchSet.length; n++) { System.out.print(pitchSet[n] + " "); }
		System.out.print(">");
	}
	
	/**
	 * Checks to see if a given pitch set is valid.
	 * Entries in the pitch set cannot be negative.
	 * @param pitchSet 
	 */
	private static void validate (int[] pitchSet) {
		for (int i = 0; i < pitchSet.length; i++) {
			if (pitchSet[i] < 0) { throw new Error("Pitch classes cannot be represented by a negative number"); }
			if (pitchSet[i] > 11) { throw new Error("Pitch classes must be an integer between 0 and 11"); }
		}
	}
	
	private static int[] removeDuplicates (int[] pitchSet) {
		int setLength = pitchSet.length;
		int[] noDuplicates = new int[setLength];
		for (int i = 0; i < setLength; i++) {
			for (int k = 0; k < setLength; k++) {
				if (noDuplicates[i] != pitchSet[k]) {
					noDuplicates[i] = pitchSet[i];
				}
			}
		}
		return noDuplicates;
	}
	
	private static int[] sortSet (int[] pitchSet) {
		int setLength = pitchSet.length;
		int[] sortedSet = new int[setLength];
		for (int i = 0; i < setLength; i++) { sortedSet[i] = pitchSet[i]; }
		
		int t = 0;
		for (int p = 0; p < setLength; p++) {
			for (int q = 1; q < setLength - 1; q++) {
				if (sortedSet[q - 1] > sortedSet[q]) {
					t = sortedSet[q - 1];
					sortedSet[q - 1] = sortedSet[q];
					sortedSet[q] = t;
				}
			}
		}
		return sortedSet;
	}
	
	/****************************************************************/
	public static void main(String[] args) {
		//System.out.println("\n");
		System.out.println("Test TRANSPOSE");
		int[] setOne = {0, 1, 3, 4, 6};
		int[] setTwo = {0, 11, 4, 5, 8, 6};
		
		showSet(setOne);
		
		System.out.println();
		showSet(setTwo);
		
		System.out.println();
		showSet(transpose(setOne, 7));
		System.out.println();
		showSet(transpose(setTwo, 7));
		
		System.out.println();
		showSet(transpose(setOne, -7));
		System.out.println();
		showSet(transpose(setTwo, -7));
		
		System.out.println();
		showSet(transpose(setOne, -2));
		System.out.println();
		showSet(transpose(setTwo, -2));

		
		System.out.println("\n");
		System.out.println("Test INVERSE");
		
		showSet(setOne);
		System.out.println();
		showSet(inverse(setOne));
		
		System.out.println();
		showSet(setTwo);
		System.out.println();
		showSet(inverse(setTwo));
		
		System.out.println("\n");
		System.out.println("Test RETROGRADE");
		
		showSet(setOne);
		System.out.println();
		showSet(retrograde(setOne));
		
		System.out.println();
		showSet(setTwo);
		System.out.println();
		showSet(retrograde(setTwo));

	}
}
