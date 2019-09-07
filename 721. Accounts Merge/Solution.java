class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.size() == 0) {
            return accounts;
        }
        
        HashMap<Integer, List<Integer>> adjacencyList = new HashMap<>();
        HashMap<String, Integer> emailToAccountIndexMap = new HashMap<>();
        
        int numAccounts = accounts.size();
        for (int i = 0; i < numAccounts; i++) {
            List<String> account = accounts.get(i);
            if (account == null || account.size() < 2) {
                continue;
            }
            String name = account.get(0);
            int numEmails = account.size();
            for (int j = 1; j < numEmails; j++) {
                String email = account.get(j);
                Integer accountIndex = emailToAccountIndexMap.get(email);
                if (accountIndex != null) {
                    List<Integer> l1 = adjacencyList.get(i);
                    if (l1 == null) {
                        l1 = new ArrayList<>();
                        adjacencyList.put(i, l1);
                    }
                    l1.add(accountIndex);
                    
                    List<Integer> l2 = adjacencyList.get(accountIndex);
                    if (l2 == null) {
                        l2 = new ArrayList<>();
                        adjacencyList.put(accountIndex, l2);
                    }
                    l2.add(i);
                } else {
                    emailToAccountIndexMap.put(email, i);
                }
            }
        }
        
        boolean[] visited = new boolean[numAccounts];
        List<Integer> visitedIndexes = new ArrayList<>();
        List<List<String>> output = new ArrayList<>();
        
        for (int i = 0; i < numAccounts; i++) {
            if (!visited[i]) {
                String name = accounts.get(i).get(0);
                visitedIndexes.add(i);
                visited[i] = true;
                HashSet<String> emails = new HashSet<>();
                while(visitedIndexes.size() > 0) {
                    int index = visitedIndexes.remove(0);
                    List<Integer> children = adjacencyList.get(index);
                    addEmails(emails, accounts.get(index));
                    if (children != null && children.size() > 0) {
                        for (int j = 0; j < children.size(); j++) {
                            if (!visited[children.get(j)]) {
                                visited[children.get(j)] = true;
                                visitedIndexes.add(children.get(j));
                            }
                        }
                    }
                }
                List<String> list = getEmailsAsList(emails);
                Collections.sort(list);
                list.add(0, name);
                output.add(list);
            }
        }
        
        return output;
    }
    
    public List<String> getEmailsAsList(HashSet<String> set) {
        Iterator iterator = set.iterator();
        List<String> output = new ArrayList<>();
        while (iterator.hasNext()) {
            output.add((String) iterator.next());
        }
        return output;
    }
    
    public void addEmails(HashSet<String> emails, List<String> account) {
        int length = account.size();
        for (int i = 1; i < length; i++) {
            emails.add(account.get(i));
        }
    }
}