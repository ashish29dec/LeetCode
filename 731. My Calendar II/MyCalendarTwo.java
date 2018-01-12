import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class MyCalendarTwo {

    HashMap<Integer, Integer> firstBookingRange;
    HashMap<Integer, Integer> doubleBookingRange;

    ArrayList<Integer> fbList;
    
    public MyCalendarTwo() {
        firstBookingRange = new HashMap<>();
        doubleBookingRange = new HashMap<>();
        
        fbList = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        if (start < 0) {
            return false;
        }
        if (end <= start) {
            return false;
        }
        if (end >= 1000000000) {
            return false;
        }
        int actualEnd = end - 1;
        
        // Check if any slot in requested range is already double booked
        Set<Integer> doubleBookedStartSlotSet = doubleBookingRange.keySet();
        Iterator<Integer> doubleBookedStartSlotIterator = doubleBookedStartSlotSet.iterator();
        boolean isDoubleBooked = false;
        while(doubleBookedStartSlotIterator.hasNext() && !isDoubleBooked) {
            int startSlot = doubleBookedStartSlotIterator.next();
            int endSlot = doubleBookingRange.get(startSlot);
            if ((start >= startSlot && start <= endSlot) 
                || (actualEnd >= startSlot && actualEnd <= endSlot) 
                || (start < startSlot && actualEnd > endSlot)) {
                isDoubleBooked = true;
            }
        }
        
        if (isDoubleBooked) {
            return false;
        }
        
        // The requested range is not double booked. But we need to now book this slot and it might happen
        // that some slots may now be either: -
        // 1. Getting booked 1st time
        // 2. Getting double booked
        // Hence we need to move around slots/ranges between HashMaps
        if (fbList.size() == 0) {
            addToFirstBooking(start, actualEnd, 0);
            return true;
        }
        
        int numFBRanges = fbList.size();
        int i = 0;
        for (; i < numFBRanges; i++) {
            int startSlot = fbList.get(i);
            int endSlot = firstBookingRange.get(startSlot);
            
            if (start > endSlot) {
                continue;
            }
            if (start > startSlot) {
                firstBookingRange.put(startSlot, start-1);
                if (actualEnd < endSlot) {
                    addToFirstBooking(actualEnd+1, endSlot, i+1);
                    addToDoubleBooking(start, actualEnd);
                    return true;
                } else if (actualEnd == endSlot) {
                    addToDoubleBooking(start, actualEnd);
                    return true;
                } else {
                    addToDoubleBooking(start, endSlot);
                    start = endSlot+1;
                    continue;
                }
            }
            if (start == startSlot) {
                firstBookingRange.remove(startSlot);
                if (actualEnd < endSlot) {
                    fbList.set(i, actualEnd+1);
                    firstBookingRange.put(actualEnd+1, endSlot);
                    addToDoubleBooking(start, actualEnd);
                    return true;
                } else if (actualEnd == endSlot) {
                    fbList.remove(i);
                    addToDoubleBooking(start, actualEnd);
                    return true;
                } else {
                    fbList.remove(i);
                    numFBRanges--;
                    i--;
                    addToDoubleBooking(start, endSlot);
                    start = endSlot+1;
                    continue;
                }
            }
            if (start < startSlot) {
                if (actualEnd < startSlot) {
                    addToFirstBooking(start, actualEnd, i);
                    return true;
                } else if (actualEnd >= startSlot && actualEnd < endSlot) {
                    firstBookingRange.remove(startSlot);
                    firstBookingRange.put(actualEnd+1, endSlot);
                    fbList.set(i, actualEnd+1);
                    addToFirstBooking(start, startSlot-1, i);
                    addToDoubleBooking(startSlot, actualEnd);
                    return true;
                } else if (actualEnd == endSlot) {
                    firstBookingRange.remove(startSlot);
                    fbList.remove(i);
                    addToFirstBooking(start, startSlot-1, i);
                    addToDoubleBooking(startSlot, actualEnd);
                    return true;
                } else {
                    firstBookingRange.remove(startSlot);
                    fbList.remove(i);
                    addToFirstBooking(start, startSlot-1, i);
                    addToDoubleBooking(startSlot, actualEnd);
                    start = endSlot+1;
                    continue;
                }
            }
        }
        
        addToFirstBooking(start, actualEnd, i);
        return true;
    }
    
    public void addToFirstBooking(int start, int end, int position) {
        fbList.add(position, start);
        firstBookingRange.put(start, end);
    }
    
    public void addToDoubleBooking(int start, int end) {
        doubleBookingRange.put(start, end);
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */