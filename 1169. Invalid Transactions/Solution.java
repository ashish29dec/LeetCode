class Solution {
    
    class Transaction {
        int amount;
        int time;
        String city;
        String name;
            
        public Transaction(String name, int amount, int time, String city) {
            this.name = name;
            this.amount = amount;
            this.time = time;
            this.city = city;
        }
    }

    public List<String> invalidTransactions(String[] transactions) {        
        List<String> invalid = new ArrayList<>();
        
        HashMap<Integer, String> indexToCityMap = new HashMap<>();
        HashSet<Integer> invalidIndexes = new HashSet<>();
        HashMap<String, ArrayList<Integer>[]> nameToTimesMap = new HashMap<>();
        
        for (int i = 0; i < transactions.length; i++) {
            Transaction t = getTransactionObject(transactions[i]);
            if (t.amount > 1000) {
                invalidIndexes.add(i);
            }
            indexToCityMap.put(i, t.city);
            ArrayList<Integer>[] timesArr = nameToTimesMap.get(t.name);
            if (timesArr == null) {
                timesArr = new ArrayList[1001];
                nameToTimesMap.put(t.name, timesArr);
            }
            for (int j = t.time; j <= (t.time+60) && j < timesArr.length; j++) {
                ArrayList<Integer> indexes = timesArr[j];
                if (indexes == null) {
                    indexes = new ArrayList<>();
                    timesArr[j] = indexes;
                }
                indexes.add(i);
            }
        }
        
        // Parse through the list
        Set<String> namesSet = nameToTimesMap.keySet();
        Iterator<String> names = namesSet.iterator();
        while (names.hasNext()) {
            String name = names.next();
            ArrayList<Integer>[] timesArr = nameToTimesMap.get(name);
            for (int i = 0; i < timesArr.length; i++) {
                ArrayList<Integer> indexes = timesArr[i];
                if (indexes == null || indexes.size() < 2) {
                    continue;
                }
                HashSet<String> cities = new HashSet<>();
                int len = indexes.size();
                for (int j = 0; j < len; j++) {
                    cities.add(indexToCityMap.get(indexes.get(j)));
                }
                if (cities.size() > 1) {
                    for (int j = 0; j < len; j++) {
                        invalidIndexes.add(indexes.get(j));
                    }
                }
            }
        }
        
        // Now populate the result list
        Iterator<Integer> invalids = invalidIndexes.iterator();
        while (invalids.hasNext()) {
            invalid.add(transactions[invalids.next()]);
        }
        
        return invalid;
    }
    
    private Transaction getTransactionObject(String trans) {
        int startIndex = 0;
        int commaIndex = trans.indexOf(',', startIndex);
        String name = trans.substring(startIndex, commaIndex);
        startIndex = commaIndex+1;
        commaIndex = trans.indexOf(',', startIndex);
        int time = Integer.parseInt(trans.substring(startIndex, commaIndex));
        startIndex = commaIndex+1;
        commaIndex = trans.indexOf(',', startIndex);
        int amount = Integer.parseInt(trans.substring(startIndex, commaIndex));
        startIndex = commaIndex+1;
        String city = trans.substring(startIndex);
        return new Transaction(name, amount, time, city);
    }
}