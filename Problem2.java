import java.util.*
class Problem2 {
    //topological sort
    //TC : O(V+E)
    //SC : O(N)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int [] indegree = new int[numCourses];
        Map<Integer,List<Integer>> adjMap = new HashMap<>();

        for(int[] crs : prerequisites){
            int course = crs[0];
            int preReqCourse =  crs[1];
            indegree[course]++;//increment indegree
            if(!adjMap.containsKey(preReqCourse)){
                adjMap.put(preReqCourse,new ArrayList<>());
            }
            List<Integer> preReqLst = adjMap.get(preReqCourse);
            preReqLst.add(course);
            adjMap.put(preReqCourse,preReqLst);//populatr adj List
        }

        Queue<Integer> q = new LinkedList<>();

        int count = 0;
        for(int i = 0 ; i< numCourses ;i++){
            if(indegree[i] == 0 ){ // this means that the vertice i does not have any dependency
                q.offer(i); // so this can be taken independently
                count ++; // this course can be taken
            }

        }

        if(count == numCourses) return true; //all courses are independednt
        if(q.isEmpty()) return false ; // we could not add anything to queue

        while(!q.isEmpty()){
            int size =  q.size();
            for(int i=0;i<size;i++){
                int polled = q.poll();
                List<Integer> adjLst = adjMap.get(polled);
                if(adjLst != null){
                    for(int adj : adjLst){
                        indegree[adj]--;
                        if(indegree[adj] == 0){ // course does not have any dependency
                            q.offer(adj);
                            count ++ ;
                        }
                        if(count == numCourses) return true; // if all courses could be taken
                    }
                }

            }
        }
        return false;
    }

}