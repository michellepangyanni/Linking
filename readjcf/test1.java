

/**
 * This class implements a relatively simple algorithm for computing (and printing) the
 * prime factors of a number.  At initialization, a list of primes is computed. Given a
 * number, this list is used to efficiently compute the prime factors of that number.
 */
public class PrimeFactorizer {
    /**
     * The maximum integer this primefactorizer allows to factorize.
     */
    private final int maxNumToFactorize;

    /**
     * Array of primes that we can use to factorize integers not exceeding maxNumToFactorize
     */
    private final int[] primeArray;

    /**
     * Constructor for a PrimeFactorizer object, which takes as its input a single integer
     * representing the maximum integer that this PrimeFactorizer will be capable of factorizing.
     *
     * @param maxNumToFactorize the maximum integer this primefactorizer allows to factorize.
     */
    public PrimeFactorizer(int maxNumToFactorize) {
        this.maxNumToFactorize = maxNumToFactorize;
        // Create an array with prime candidates
        int[] candidateFactorizer = computePrimeCandidates(this.maxNumToFactorize);
        // Set number of nonprime to be zero
        int numOfNonPrime = 0;
        // Check every non-zero candidates to cross out their multiples
        for (int i = 0; i < candidateFactorizer.length; i++){
            if (candidateFactorizer[i] != 0) {
                // Only need to check numbers to the right of the candidate we are examining
                for (int j = i + 1; j < candidateFactorizer.length; j++){
                    // If haven't been crossed out and is the multiples of our candidate, replace by 0
                    if (candidateFactorizer[j] != 0){
                        if (candidateFactorizer[j] % candidateFactorizer[i] == 0){
                            candidateFactorizer[j] = 0;

                            // increase numOfNonPrime by 1
                            numOfNonPrime++;
                        }
                    }
                }
            }
        }
        // Initialize a new array to store only the primes, since we know numOfNonPrime
        // we can directly set the size of the array
        this.primeArray = new int [candidateFactorizer.length - numOfNonPrime];
        int primeIdx = 0;
        // Put non-zero candidates to new array
        for (int j : candidateFactorizer) {
            if (j != 0) {
                this.primeArray[primeIdx] = j;
                primeIdx++;
            }
        }
    }

    /**
     * Computes and returns the prime factorization of the input integer, in the form of
     * an array of prime integers.
     *
     * @param numToFactorize the integer that we are going to factorize.
     * @return the array of factorizers of numToFactorize in non-decreasing order.
     */
    public int[] computePrimeFactorization(int numToFactorize) {
        // We can only compute prime factorization when
        // numToFactorize >= 2 & numToFactorize <= this.maxNumToFactorize
        if (numToFactorize >= 2 & numToFactorize <= this.maxNumToFactorize){
            // For a given number, the worst case is it's factorizers are all 2s,
            // since 2 is the smallest prime, so we can set the size to be
            // log(num) base 2 which enables us to store all possible primes.
            int primeLength = (int)(Math.log(numToFactorize) / Math.log(2));
            int[] factorizerResult = new int[primeLength];
            // j is the number of factorizers
            int j = 0;
            // iterate over all element in primeArray
            for(int i = 0; i < this.primeArray.length; ){
                if (numToFactorize % this.primeArray[i] == 0){
                    numToFactorize = numToFactorize / this.primeArray[i];
                    factorizerResult[j] = this.primeArray[i];
                    // if all factor out, break iteration
                    if(numToFactorize == 1){
                        j++;
                        break;
                    }
                    j++;
                }
                // Else, proceed to next iteration
                else{
                    i++;
                }
            }
            // if numToFactorize not equal 1, it is a factorizer.
            if (numToFactorize != 1){
                factorizerResult[j] = numToFactorize;
                j++;
            }
            // initialize an array finalPrimes with correct size to copy factorizers in
            int[] finalPrimes = new int[j];
            for (int k = 0; k < j; k++){
                finalPrimes[k] = factorizerResult[k];
            }
            return finalPrimes;
        }
        // if numToFactorize is either smaller than 2
        // or bigger that maxNumToFactorize, return null
        return null;
    }

    /**
     * Create an array of "prime candidates" containing all integers between 2 and
     * the square root of maxNumToFactorize
     *
     * @param maxNumToFactorize the maximum integer this primefactorizer allows to factorize.
     * @return an array of all integers between 2 and the square root of maxNumToFactorize
     */
    private int[] computePrimeCandidates(int maxNumToFactorize){
        // prime up to sqrt(maxNumToFactorize) is enough for us to compute factorization
        int maxCandidate = (int) Math.ceil(Math.sqrt(maxNumToFactorize));
        // Length is fixed: maxCandidate - 1
        int[] candidateArray = new int[maxCandidate - 1];
        // use iteration to build candidate array
        for (int i = 2; i <= maxCandidate; i++){
            candidateArray[i - 2] = i;
        }
        return candidateArray;
    }

}