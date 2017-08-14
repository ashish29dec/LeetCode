/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int start = 0;
        int end = n;
        int mid = n / 2;
        int result = guess(mid);
        while(result != 0) {
            if (result == -1) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = start + (end - start)/2;
            result = guess(mid);
        }
        
        return mid;
    }
}