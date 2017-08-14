public class Solution {
    public String validIPAddress(String IP) {
        if (IP == null || IP.length() == 0) {
            return "Neither";
        }
        
        if (IP.indexOf('.') != -1 && IP.indexOf(':') != -1) {
            return "Neither";
        }
        
        if (IP.indexOf('.') == -1 && IP.indexOf(':') == -1) {
            return "Neither";
        }
        
        if (IP.indexOf('.') != -1 && (IP.length() < 7 || IP.length() > 15)) {
            return "Neither";
        }
        
        if (IP.indexOf(':') != -1 && (IP.length() < 15 || IP.length() > 39)) {
            return "Neither";
        }
        
        if (IP.indexOf('.') != -1) {
            // Possible IPv4 address
            if (IP.lastIndexOf('.') == IP.length() - 1 || IP.indexOf('.') == 0) {
                return "Neither";
            }
            
            int start = 0;
            int end = IP.indexOf('.');
            int numSections = 0;
            boolean isIPv4 = true;
            
            while(isIPv4) {
                numSections++;
                if (numSections == 5) {
                    isIPv4 = false;
                    continue;
                }
                isIPv4 = isSectionIPv4(IP.substring(start, end));
                if (!isIPv4) {
                    continue;
                }
                start = end + 1;
                if (start > IP.length()) {
                    break;
                }
                end = IP.indexOf('.', start);
                if (end == -1) {
                    end = IP.length();
                }
            }
            
            if (!isIPv4 || numSections < 4) {
                return "Neither";
            }
            return "IPv4";
        } else if (IP.indexOf(':') != -1) {
            // Possible IPv6 address
            if (IP.indexOf(':') == 0 || IP.lastIndexOf(':') == IP.length() - 1) {
                return "Neither";
            }
            
            int index = 0;
            int numSections = 0;
            int numCharsScanned = 0;
            boolean isIPv6 = true;
            while(isIPv6) {
                if (numCharsScanned > 4) {
                    isIPv6 = false;
                    continue;
                }
                if (index == IP.length()) {
                    numSections++;
                    break;
                }
                char c = IP.charAt(index);
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) {
                    numCharsScanned++;
                } else if (c == ':') {
                    if (numCharsScanned == 0) {
                        isIPv6 = false;
                        continue;
                    } else {
                        numSections++;
                        numCharsScanned = 0;
                        if (numSections == 8) {
                            isIPv6 = false;
                            continue;
                        }
                    }
                } else {
                    isIPv6 = false;
                    continue;
                }
                index++;
            }
            
            if (!isIPv6 || numSections < 8) {
                return "Neither";
            }
            return "IPv6";
        }
        return "Neither";
    }
    
    public boolean isSectionIPv4(String section) {
        if (section == null || section.length() == 0 || section.length() > 3) {
            return false;
        }
        
        if (section.charAt(0) == '-' || section.charAt(0) == '+') {
            return false;
        }
        
        if (section.length() > 1 && section.charAt(0) == '0') {
            return false;
        }
        
        int secNum = -1;
        try {
            secNum = Integer.parseInt(section);
        } catch (NumberFormatException nfe) {
            
        }
        
        if (secNum < 0 || secNum > 255) {
            return false;
        }
        return true;
    }
}