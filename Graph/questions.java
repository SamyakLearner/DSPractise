import java.util.* ;

public class questions{
    
    // find min path to reach dest (min edges b/w src and dest)
    public static void BFS_01(int src,int dest,boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        
        int level=0;
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int vtx = que.removeFirst();
            
                if(vis[vtx])
                    continue;
                if(vtx == dest){
                    System.out.println(level);
                    break;
                }

                vis[vtx] = true; // mark
                for(Edge e: graph[vtx]){
                    if(!vis[e.v])
                    que.addLast(e.v);
                }
            }
            level++;
        }
    }


    //Leetcode 200    
    int n=0,m=0;
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    public void dfsIsland(int i,int j,char[][] grid){    
        
        grid[i][j]='0';
        for(int d=0;d<dir.length;d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            
            if(r>=0 && c>=0 && r<n && c<m&& grid[r][c]=='1')
                dfsIsland(r,c,grid);
        }
    }
    
    public int numIslands(char[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        n = grid.length;
        m = grid[0].length;
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='1'){
                    count++;
                    dfsIsland(i,j,grid);
                }
            }
        }
        
        return count;
    }

    //Leetcode 695
    public int dfsIsland(int i,int j,int[][] grid){    
        
        grid[i][j]=0;
        int count=0;
        for(int d=0;d<dir.length;d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            
            if(r>=0 && c>=0 && r<n && c<m&& grid[r][c]==1)
                count+=dfsIsland(r,c,grid);
        }
        
        return count + 1;
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        n = grid.length;
        m = grid[0].length;
        int area=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    
                    area=Math.max(area,dfsIsland(i,j,grid));
                }
            }
        }
        
        return area;
    }

    //Leetcode 463
    public int islandPerimeter(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
      
      int n = grid.length;
      int m = grid[0].length;
      int count=0,nbrs=0;
      for(int i=0;i<n;i++){
          for(int j=0;j<m;j++){
              if(grid[i][j] == 1){
               count++;
                for(int d=0;d<dir.length;d++){
                  int r = i + dir[d][0];
                  int c = j + dir[d][1];
          
                if(r>=0 && c>=0 && r<n && c<m&& grid[r][c]==1)
                  nbrs++;
              }
            }
        }
      }
      
      return 4*count-nbrs; 
    }

    //Leetcode 130

    public void SurroundedRegion(char[][] grid, int sr, int sc, int[][] dir) {
        grid[sr][sc] = '$';
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 'O')
                SurroundedRegion(grid, r, c, dir);
        }

    }

    public void solve(char[][] board) {

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1)
                    if (board[i][j] == 'O')
                        SurroundedRegion(board, i, j, dir);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '$')
                    board[i][j] = 'O';
            }
        }
    }

    //Leetcode 785
    public boolean isBipartite(int[][] graph,int src,int[] prevColor,int N){
        // 0 : Red, 1 : Green
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.addLast(src);
        int color = 0;
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                
                int vtx =  que.removeFirst();
                
                
                if(prevColor[vtx] !=-1 && prevColor[vtx] != color){
                    return false;
                }
                
                prevColor[vtx] = color;
                for(int e : graph[vtx]){
                    if(prevColor[e] == -1){
                        que.addLast(e);
                    }
                }
            }
            color = (color + 1) % 2;
        }
        
        return true;   
    }
    
    
    public boolean isBipartite(int[][] graph) {
        int N = graph.length;
        int[] prevColor = new int[N];
        Arrays.fill(prevColor,-1);
        
        for(int i=0;i<N;i++){
            if(prevColor[i]==-1){
                if(!isBipartite(graph,i,prevColor,N)) return false ;
            }
        }
        
        return true;
    }

    //Leetcode 994
    //Leetcode 994
    public int orangesRotting(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        
        ArrayDeque<int[]> que=new ArrayDeque<>();
        int freshOranges = 0;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1) freshOranges++;
                else if(grid[i][j]==2) que.addLast(new int[]{i,j});
            }
        }
        
        if(freshOranges == 0) return 0;
        int time = 0;
        
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int[] rvtx = que.removeFirst();
                int r = rvtx[0];
                int c = rvtx[1];
                
                for(int d=0;d<4;d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    
                    if(x>=0 && y>=0 && x< n && y< m && grid[x][y]==1){
                        grid[x][y] = 2;
                        freshOranges--;
                        que.addLast(new int[]{x,y});
                        
                        if(freshOranges==0) return time + 1;
                    }
                }
            }
            
            time++;
        }
        
        return -1;
    }

    public int orangesRotting2(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        
        ArrayDeque<Integer> que=new ArrayDeque<>();
        int freshOranges = 0;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1) freshOranges++;
                else if(grid[i][j]==2) que.addLast(i*m+j);
            }
        }
        
        if(freshOranges == 0) return 0;
        int time = 0;
        
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int rvtx = que.removeFirst();
                int r = rvtx / m;
                int c = rvtx%m;
                
                for(int d=0;d<4;d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    
                    if(x>=0 && y>=0 && x< n && y< m && grid[x][y]==1){
                        grid[x][y] = 2;
                        freshOranges--;
                        que.addLast(x*m+y);
                        
                        if(freshOranges==0) return time + 1;
                    }
                }
            }
            
            time++;
        }
        
        return -1;
    }

    //Leetcode 542
    public int[][] updateMatrix(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return grid;
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        
        
        int[][] vis=new int[n][m];
        for(int[] a: vis)
          Arrays.fill(a, -1);

        LinkedList<int[]> que=new LinkedList<>();
        
        int countOnes = n*m;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if (grid[i][j] == 0) {
                    que.addLast(new int[]{i,j});
                    vis[i][j] = 0;
                    countOnes--;
                }
            }
        }

        int level = 1;
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int[] rvtx = que.removeFirst();
                int r = rvtx[0];
                int c = rvtx[1];

                for(int d = 0; d < 4;d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x>=0 && y>=0 && x< n && y<m && vis[x][y] == -1){
                        vis[x][y] = level;
                        countOnes--;
                        que.addLast(new int[]{x,y});
                    }
                }
            }

            level++;
            if(countOnes == 0) break;
        }

        return vis;
    }

    //Leetcode 1091
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;

        if(grid[0][0] == 1 || grid[n-1][m-1] == 1) return -1;
        
        int[][] dir =  {{-1, -1}, {-1, 0}, {0, -1}, {-1, 1}, {1, -1}, {0, 1}, {1, 0}, {1, 1}};
        
        LinkedList<int[]> que=new LinkedList<>();
        que.addLast(new int[]{0,0});
        grid[0][0] = 1;
       
        int level=0;

        while(que.size() != 0){
            int size = que.size();
            while(size-->0){

                int[] rvtx = que.removeFirst();
                int x = rvtx[0];
                int y = rvtx[1];
                
                if(x == n-1 && y == m-1) return level + 1;

                for(int d = 0; d < 8; d++){
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];

                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0){
                        que.addLast(new int[]{r,c});
                        grid[r][c] = 1;
                    }
                }
            }

            level++;
        }

        return -1;
    }

    //Leetcode 1020
    public int numEnclaves(int[][] arr) {
        
        if(arr.length==0 || arr[0].length==0) return 0;
        int n = arr.length;
        int m = arr[0].length;
        
        int one=0;
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                one+=arr[i][j];
                if((i==0 || j==0 || i==n-1 || j==m-1) && arr[i][j]==1 ){
                    que.addLast(i*m+j);
                    one--;
                    arr[i][j] = 0;
                }
            }
        }
        
        if(one==0) return 0;
        
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int vtx = que.removeFirst();
                int x = vtx / m;
                int y = vtx % m;
                
                for(int d=0;d<4;d++){
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];
                    
                    if(r>=0 && c>=0 && r<n&& c<m && arr[r][c] == 1){
                        arr[r][c] = 0;
                        que.addLast(r*m+c);
                        one--;
                    }  
                }
            }
            
            if(one == 0) break;
        }
        
        return one;
    }

    //Leetcode 207 
    public boolean canFinish(int N, int[][] prerequisites) {

        // create graph
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i=0;i<N;i++) graph[i] = new ArrayList<>();
        int[] indegree= new int[N];
        
        // add edges 
        // and also store the indegree
        for(int[] a : prerequisites){
            int u = a[0];
            int v = a[1];

            indegree[v]++;
            graph[u].add(v);
        }

        // push all vtx with 0 indegree into the queue
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for(int i=0;i<N;i++) if(indegree[i]==0) que.addLast(i);

        int count = 0;
        while(que.size()!=0){
            // keep removing from q , updating ans_count
            int vtx = que.removeFirst();
            count++;

            // check the nbrs , decrement indegree
            // if any nbr's indegree becomes 0 add it to q
            for(int e : graph[vtx]){
                if(--indegree[e]==0)
                   que.add(e); 
            }
        }

        // return if all dependencies are resolved or not
        return count == N;
    }


    // using Kahn's Algo / dependency resolving
    public int[] findOrder1(int N, int[][] prerequisites) {

        // create graph
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i=0;i<N;i++) graph[i] = new ArrayList<>();
       
        int[] indegree= new int[N];
       
        // add edges 
        // also store the indegree of each vtx
        for(int[] a : prerequisites){
            int u = a[0];
            int v = a[1];

            indegree[v]++;
            graph[u].add(v);
        }

        ArrayDeque<Integer> que = new ArrayDeque<>();

        // add all vtx with 0 indegree in the queue
        for(int i=0;i<N;i++) if(indegree[i]==0) que.addLast(i);

        int[] ans = new int[N] ;
        int idx = 0 ;
        while(que.size()!=0){

            // remove vtx and add it to ans 
            int vtx = que.removeFirst();
            ans[idx++] = vtx;

            // check nbr , decrement its indegree(dependency) 
            // if indegree becomes 0 add it to queue
            for(int e : graph[vtx]){
                if(--indegree[e]==0)
                   que.addLast(e); 
            }
        }

        // if ans.size == n  , return ans 
        // else return new arr
        if(idx != N) return  new int[0];
        return ans;
    }

    public static boolean isCyclePresent(ArrayList<Integer>[] graph,int src,int[] vis,ArrayList<Integer> ans){
        
        // add src as visited
        vis[src] = 0;

        // call dfs for all nbrs
        // if any nbr is just visited on same path , there is cycle
        // if any nbr has completed visit no need to check again
        for(int e : graph[src]){
            if(vis[e]==-1){
               if(isCyclePresent(graph,e,vis,ans)) return true;
            }else if(vis[e]==0) return true;
        }

        // mark src as complete visited
        // also add it to ans 
        vis[src] = 1;
        ans.add(src);

        return false ;
    }


    // using dfs
    public int[] findOrder2(int N, int[][] prerequisites) {

        // create graph
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i=0;i<N;i++) graph[i] = new ArrayList<>();
        
        // add edges in graph
        for(int[] a : prerequisites)
            graph[a[0]].add(a[1]);

        // create vis arr with initial value -1 , denoting unvisited
        int[] vis = new int[N];
        Arrays.fill(vis,-1);
        ArrayList<Integer> ans = new ArrayList<>();

        // call dfs for vtx with 0 indegree
        for(int i=0;i<N;i++){
            if(vis[i]==-1) if(isCyclePresent(graph,i,vis,ans)) return new int[0];
        }


        int[] res = new int[ans.size()];
        int idx =0;
        for(int ele: ans) res[idx++] = ele;
        return res ;
    }

    //Leetcode 329 
   public int longestIncreasingPath(int[][] matrix) {
       int n = matrix.length; if(n==0) return 0;
       int m = matrix[0].length;if(m==0) return 0;

       int[][] indegree=new int[n][m];
       int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

       for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
               for(int d=0;d<4;d++){
                   int r = i + dir[d][0];
                   int c = j + dir[d][1];

                   if(r >= 0 && c >= 0 && r < n && c < m && matrix[r][c] > matrix[i][j])
                      indegree[r][c]++;
               }
           }
       }

       ArrayDeque<Integer> que = new ArrayDeque<>();
       for(int i=0;i<n;i++){
         for(int j=0;j<m;j++){
          if(indegree[i][j]==0) que.addLast(i*m+j);
         }
      } 

      int level = 0;
      while(que.size()!=0){
          int size = que.size();
          while(size-- > 0){
          
            int vtx = que.removeFirst();
            int i = vtx / m;
            int j = vtx % m;

            for(int d=0;d<4;d++){
                int r = i + dir[d][0];
                int c = j + dir[d][1];

                if(r >= 0 && c >= 0 && r < n && c < m && matrix[r][c] > matrix[i][j] && --indegree[r][c]==0)
                   que.addLast(r*m+c);
            }
          }
          level++;
      }

      return level;
   }


   //================================================

   int[] par;
    public int findPar(int u){
        if(par[u] == u) return u;
        return par[u] = findPar(par[u]);
    }
   
    //Leetcode 200
    public int numIslands(char[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        
        par = new int[n*m];
        int count=0;
        for(int i=0;i<n*m;i++){
            par[i] = i;
            if(grid[i/m][i%m]=='1') count++;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == '1'){
                    int p1 = findPar(i*m+j);
                    if(j+1 < m && grid[i][j+1] == '1'){
                        int p2 = findPar(i*m+j+1);
                        if(p1 != p2){
                            par[p2] = p1;
                            count--;
                        }
                    }

                    if(i+1 < n && grid[i+1][j] == '1'){
                        int p2 = findPar((i+1)*m+j);
                        if(p1 != p2){
                            par[p2] = p1;
                            count--;
                        }
                    }
                }
            }
        }
        return count;
    }

    public String smallestEquivalentString(String A, String B, String S) {
        par = new int[26];
        for(int i=0;i<26;i++) par[i] = i;
        
        for(int i=0;i<A.length();i++){
            int p1 = findPar(A.charAt(i)-'a');
            int p2 = findPar(B.charAt(i)-'a');
            
            par[p1] = Math.min(p1,p2);
            par[p2] = Math.min(p1,p2);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<S.length();i++){
            int ch = findPar(S.charAt(i)-'a');
            sb.append((char)(ch+'a'));
        }


        return sb.toString();
    }

    //Leetcode 684
    public int[] findRedundantConnection(int[][] edges) {
        int N=edges.length;
        par=new int[N+1];
        for(int i=0;i<=N;i++)
           par[i]=i;
        
        int[] ans = null;
        for(int[] e: edges){
            int p1 = findPar(e[0]);
            int p2 = findPar(e[1]);

            if(p1!=p2)
                par[p1] = p2;
            else
               {
                   ans = e;
                   break;
               }
        }

        return ans;
    }

    //Leetcode 839
    public boolean isSimilar(String a,String b){
        int count = 0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i) != b.charAt(i) && ++count > 2) return false;
        }

        return true;
    }
    
    public int numSimilarGroups(String[] A) {
        int n = A.length();
        
        par = new int[n];
        for(int i=0;i<n;i++) par[i] = i;
        
        int count = n;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(isSimilar(A[i],A[j])){
                    int p1 = findPar(i);
                    int p2 = findPar(j);

                    if(p1 != p2){
                        par[p1] = p2;
                        count--;
                    }
                }
            }
        }

        return count;
    }

    public List<Integer> numIslands2(int n, int m, int[][] positions) {
       par = new int[n*m];
       for(int i=0;i<n*m;i++) par[i] = i;

       int islandCount = 0;
       int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
       
       int[][] mat = new int[n][m];
       List<Integer> ans = new ArrayList<>();

       for(int[] a: positions){
           int i = a[0];
           int j = a[1];

           if(mat[i][j]==0){
            int p1 = findPar(i*m+j);
            mat[i][j] = 1;
            islandCount++;
 
            for(int d = 0;d<4;d++){
                int r = i + dir[d][0];
                int c = j + dir[d][1];
 
                if(r >= 0 && c >= 0 && r < n && c < m && mat[r][c]==1){
                   int p2 = findPar(r*m+c);
                   
                   if(p1 != p2){
                       islandCount--;
                       par[p2] = p1;
                   }
                }
            }
           
           }
           ans.add(islandCount);
       }

       return ans;

    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> gp = new ArrayList<>();
        for(int[] e: pipes) gp.add(e);
        for(int i=0;i<wells.length;i++){
            gp.add(new int[]{0,i+1,wells[i]});
        }

        Collections.sort(gp,(a,b)->{
            return a[2] - b[2];
        });

        par = new int[n+1];
        for(int i=0;i<=n;i++) par[i] = i;

        int weight = 0;
        for(int[] a: gp){
            int p1 = findPar(a[0]);
            int p2 = findPar(a[1]);

            if(p1!=p2){
                par[p1] = p2;
                weight += a[2];
            }
        }

        return weight;
    }

}