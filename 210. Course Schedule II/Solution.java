public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int[] visited = new int[numCourses];
        
        HashMap<Integer, ArrayList<Integer>> adjacency = new HashMap<>();
        List<Integer> queue = new ArrayList<>();
        int[] coursesWithNoPreReq = new int[numCourses];
        
        int resultIndex = 0;
        int visitedIndex = 0;
        
        for (int i = 0; i < numCourses; i++) {
            visited[i] = 0;
        }
        
        for (int i = 0; i < numCourses; i++) {
            coursesWithNoPreReq[i] = 0;
        }
        
        /*
        if (prerequisites == null || prerequisites.length == 0) {
            return result;
        }
        */
        
        for (int i = 0; i < prerequisites.length; i++) {
            int[] pre = prerequisites[i];
            ArrayList<Integer> children = adjacency.get(pre[1]);
            if (children == null) {
                children = new ArrayList<>();
                adjacency.put(pre[1], children);
            }
            children.add(pre[0]);
            coursesWithNoPreReq[pre[0]]++;
        }
        
        for (int i = 0; i < numCourses; i++) {
            if (coursesWithNoPreReq[i] == 0) {
                queue.add(i);
            }
        }
        
        while (resultIndex != numCourses) {
            if (queue.size() == 0) {
                break;
            } else {
                int course = queue.remove(0);
                if (visited[course] == 0) {
                    result[resultIndex++] = course;
                    visited[course] = 1;
                    ArrayList<Integer> children = adjacency.get(course);
                    if (children != null) {
                        Iterator<Integer> iterator = children.iterator();
                        while(iterator.hasNext()) {
                            int postCourse = iterator.next();
                            coursesWithNoPreReq[postCourse]--;
                            if (coursesWithNoPreReq[postCourse] == 0) {
                                queue.add(postCourse);
                            }
                        }
                    }
                }
            }
        }
        
        if (resultIndex != numCourses) {
            return new int[0];
        }
        
        return result;
    }
}