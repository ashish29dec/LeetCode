public class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        
        // Check if any characters except e are present
        int len = s.length();
        int numE = 0;
        for (int i = 0; i < len; i++) {
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) {
                if (s.charAt(i) != 'e') {
                    return false;
                } else if(numE == 0) {
                    numE++;
                } else {
                    return false;
                }
            }
        }
        
        // Check if more than 1 decimal points are present
        int numDecimal = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '.') {
                numDecimal++;
                if (numDecimal > 1) {
                    return false;
                }
            }
        }
        
        boolean hasNumberStarted = false;
        boolean isSignEncountered = false;
        boolean hasEFound = false;
        boolean hasNumAfterEFound = false;
        boolean hasSignAfterEFound = false;
        boolean hasSpaceEncountered = false;
        boolean hasDecimalFound = false;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            switch(c) {
                case '+':
                case '-':
                    if (hasDecimalFound && !hasEFound) {
                        return false;
                    }
                    if (isSignEncountered && !hasEFound) {
                        return false;
                    }
                    if (hasNumberStarted && !hasEFound) {
                        return false;
                    }
                    if (!hasNumberStarted){
                        isSignEncountered = true;
                        hasSpaceEncountered = false;
                    }
                    if(hasEFound && (hasSignAfterEFound || hasNumAfterEFound)) {
                        return false;
                    }
                    if (hasEFound) {
                        hasSignAfterEFound = true;
                    }
                    break;
                case ' ':
                    if (isSignEncountered && !hasNumberStarted) {
                        return false;
                    }
                    hasSpaceEncountered = true;
                    break;
                case 'e':
                    if (!hasNumberStarted) {
                        return false;
                    }
                    if (hasEFound) {
                        return false;
                    }
                    if (hasNumberStarted && hasSpaceEncountered) {
                        return false;
                    }
                    hasEFound = true;
                    break;
                case '.':
                    if (hasEFound) {
                        return false;
                    }
                    if ((isSignEncountered || hasNumberStarted) && hasSpaceEncountered) {
                        return false;
                    }
                    hasSpaceEncountered = false;
                    hasDecimalFound = true;
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    if (hasDecimalFound && hasSpaceEncountered) {
                        return false;
                    }
                    if ((isSignEncountered || hasNumberStarted) && hasSpaceEncountered) {
                        return false;
                    }
                    if (!hasNumberStarted) {
                        hasNumberStarted = true;
                        hasSpaceEncountered = false;
                    }
                    if (hasEFound && !hasNumAfterEFound) {
                        hasNumAfterEFound = true;
                    }
                    break;
            }
        }
        if (!hasNumberStarted) {
            return false;
        }
        
        if (hasEFound && !hasNumAfterEFound) {
            return false;
        }
        
        return true;
    }
}