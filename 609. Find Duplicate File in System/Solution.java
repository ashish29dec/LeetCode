class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        if (paths == null) {
            return null;
        }
        
        List<List<String>> output = new ArrayList<>();
        if (paths.length == 0) {
            return output;
        }
        
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < paths.length; i++) {
            String info = paths[i];
            String[] infoArr = split(info);
            if (infoArr == null) {
                continue;
            }
            String dir = infoArr[0];
            for (int j = 1; j < infoArr.length; j++) {
                String file = infoArr[j];
                String name = file.substring(0, file.indexOf('('));
                String content = getContent(file);
                List<String> matchedFiles = map.get(content);
                if (matchedFiles == null) {
                    matchedFiles = new ArrayList<>();
                    map.put(content, matchedFiles);
                }
                matchedFiles.add(dir + "/" + name);
            }
        }
        
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            List<String> dirs = map.get(iterator.next());
            if (dirs.size() > 1) {
                output.add(dirs);
            }
        }
        
        return output;
    }
    
    private String getContent(String file) {
        int s = file.indexOf('(');
        int e = file.indexOf(')');
        return file.substring(s+1, e);
    }
    
    private String[] split(String infoStr) {
        List<String> list = new ArrayList<>();
        int start = 0;
        int end = infoStr.indexOf(' ', start);
        while (end != -1) {
            list.add(infoStr.substring(start, end));
            start = end + 1;
            end = infoStr.indexOf(' ', start);
        }
        if (list.size() == 0) {
            return null;
        }
        list.add(infoStr.substring(start));
        String[] info = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            info[i] = list.get(i);
        }
        return info;
    }
}