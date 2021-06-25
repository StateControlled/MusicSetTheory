package setTheory;
/**
 * For musical set theory. Most methods will not modify the arguments directly.
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
	 * @return retrograde
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
	 * @return retrogradeInverse
	 */
	public static int[] retrogradeInverse (int[] pitchSet) {
		validate(pitchSet);
		return retrograde(inverse(pitchSet));
	}
	
	/**
	 * Works like the inverse method, but will reflect the pitch set around a pitch other than the
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
	
	/**
	 * Intended only for sets with no duplicate pitches and with a length less than 12.
	 * The complement of a pitch set is a set that contains the pitch classes that are NOT in the primary set.
	 * @param pitchSet
	 * @return complementSet
	 */
	public static int[] complement (int[] pitchSet) {
		// TODO
		// {3, 6, 2, 1, 11}
		validate(pitchSet);
		int setLength = pitchSet.length;
		if (setLength >= 12) { throw new Error("It is not possible to create a complement to this set."); }
		//int[] t = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		int searchValue = 0;
		int h = 0;
		int[] complementSet = new int[12 - setLength];
		
		for (int i = 0; i < setLength; i++) {
			if (pitchSet[i] != searchValue) {
				for (int k = searchValue; k < pitchSet[i]; k++) {
					complementSet[h++] = k;
				}
			}
			searchValue = pitchSet[i] + 1;
		}
		return complementSet;
	}
	
	/**
	 * Will transpose all the pitches of a set via multiplication.
	 * @param pitchSet
	 * @param multiplier	The value by which to multiply.
	 * @return transposedSet
	 */
	public static int[] multiplicativeTransposition (int[] pitchSet, int multiplier) {
		validate(pitchSet);
		int setLength = pitchSet.length;
		int[] transposedSet = new int[setLength];
		
		for (int i = 0; i < setLength; i++) {
			transposedSet[i] = Math.floorMod( (pitchSet[i] * multiplier), 12 );
		}
		return transposedSet;
	}
	
	public static int[] normalForm (int[] pitchSet) {
		// TODO
		validate(pitchSet);
		int[] normal = removeDuplicates(pitchSet);
		return null;
	}
	
	public static int[] mergeSets (int[] set1, int[] set2) {
		// TODO
		int[] mergedSet = new int[set1.length + set2.length];
		return mergedSet;
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
	 * Entries in the pitch set cannot be negative or greater than 11
	 * (cannot be outside the twelve pitches of the western chromatic scale).
	 * @param pitchSet 
	 */
	private static void validate (int[] pitchSet) {
		for (int i = 0; i < pitchSet.length; i++) {
			if (pitchSet[i] < 0) { throw new Error("Pitch classes cannot be represented by a negative number"); }
			if (pitchSet[i] > 11) { throw new Error("Pitch classes must be an integer between 0 and 11"); }
		}
	}
	
	private static int[] adjustSize (int[] pitchSet) {
		// TODO
		return pitchSet;
	}
	
	/** TODO
	 * Removes duplicate entries from a set. Will sort the set first using the sortSet method.
	 * @param pitchSet
	 * @return noDuplicates	Sorted set with no duplicate entries.
	 */
	public static int[] removeDuplicates (int[] pitchSet) {
		// TODO
		// make private after complete
		int setLength = pitchSet.length;
		int[] t = sortSet(pitchSet);
		showSet(t);
		System.out.println();
		int[] noDuplicates = new int[setLength];

		
		return noDuplicates;
	}
	
	/**
	 * Will sort a given set in ascending order. Will not remove duplicates.
	 * @param pitchSet
	 * @return sortedSet	The set in ascending order.
	 */
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
		//int[] dup = {0, 1, 2, 3, 1, 2, 3, 4, 5, 1, 2, 3, 6, 7, 4, 5, 8};
		//showSet(removeDuplicates(dup));
		
		System.out.println();
		System.out.println("Test TRANSPOSE");
		int[] setOne = {0, 1, 3, 4, 6};
		int[] setTwo = {0, 11, 4, 5, 8, 6};
		int[] setThree = { 4, 8, 7, 1, 9, 11, 2 };
		
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
		
		System.out.println("\n");
		System.out.println("Test RETROGRADEINVERSE");
		
		showSet(setOne);
		System.out.println();
		showSet(retrogradeInverse(setOne));
		
		System.out.println();
		showSet(setTwo);
		System.out.println();
		showSet(retrogradeInverse(setTwo));
		
		System.out.println("\n");
		System.out.println("Test MULTIPLICATIVETRANSPOSITION");
		
		showSet(setOne);
		System.out.println();
		showSet(multiplicativeTransposition(setOne, 5));

		System.out.println();
		showSet(setTwo);
		System.out.println();
		showSet(multiplicativeTransposition(setTwo, 11));
	}
}
