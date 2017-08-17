public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        
        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            int cur = flowerbed[i];
            if (cur == 0) {
                boolean avail = true;
                if (i > 0 && i < flowerbed.length) {
                    if (flowerbed[i-1] == 1) {
                        avail = false;
                    }
                }
                if (avail && i < flowerbed.length - 1) {
                    if (flowerbed[i+1] == 0) {
                        flowerbed[i] = 1;
                        n--;
                    }
                } else if (avail && i == flowerbed.length - 1) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }
        
        if (n == 0) {
            return true;
        }
        
        return false;
    }
}