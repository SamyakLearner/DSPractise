import java.util.* ;

public class ques_HashMap{
    //349
    public int[] intersection(int[] nums1, int[] nums2) {
        
        HashSet<Integer> map = new HashSet<>();
        for(int ele: nums1) map.add(ele);
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int ele: nums2){
            if(map.contains(ele)){
                list.add(ele);
                map.remove(ele);
            }
        }
        
        int[] ans = new int[list.size()];
        for(int i=0;i<ans.length;i++) ans[i] = list.get(i);
        
        return ans;
    }
 
    //350
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele: nums1) map.put(ele,map.getOrDefault(ele,0)+1);
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int ele: nums2){
            if(map.containsKey(ele)){
                map.put(ele,map.get(ele) - 1);
                list.add(ele);
                
                if(map.get(ele) == 0) map.remove(ele);
            }
        }
        
        int[] ans = new int[list.size()];
        for(int i=0;i<ans.length;i++) ans[i] = list.get(i);
        
        return ans;
        
    }

    //128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        
        for(int ele : nums) map.add(ele);
        
        int len = 0;
        for(int ele : nums){
            if(!map.contains(ele)) continue;
            
            map.remove(ele);
            int prev = ele-1;
            int next = ele+1;
            
            while(map.contains(prev)) map.remove(prev--);
            while(map.contains(next)) map.remove(next++);
            
            len = Math.max(len,next - prev - 1);
        }
        
        return len;
        
    }

    // 347
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele: nums) map.put(ele,map.getOrDefault(ele,0) + 1);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return map.get(a) - map.get(b);
        });
        
        for(int ele: map.keySet()){
            pq.add(ele);
            if(pq.size() > k) pq.poll();
        }
        
        int[] ans = new int[pq.size()];
        int i = 0;
        while(pq.size()!=0){
            ans[i++] = pq.poll();
            
        }
        return ans;   
    }
   
    //https://practice.geeksforgeeks.org/problems/check-arithmetic-progression/0
    public void mainsolve ()
	 {
	     Scanner scn = new Scanner(System.in);
	     
	     int t = scn.nextInt();
	  while(t-->0){
	      int n = scn.nextInt();
	      int[] arr = new int[n];
	      
	     HashSet<Integer> map = new HashSet<>();
	     
	     PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
	           return b - a;    
	     });
	     
	     for(int i = 0; i < n;i++){
	         arr[i] = scn.nextInt();
	         map.add(arr[i]);
	         pq.add(arr[i]);
	         
	         if(pq.size() > 2) pq.remove();
	     }
	     
	     if(n <= 2) {
	          System.out.println("YES");
	          continue;
	          
	      }
	     
	     int a = pq.poll();
	     int b = pq.poll();
	     
	     int d = a - b;
	     int ele = b + d;
	     int count = 1;
	     while(map.contains(ele)){
	         map.remove(ele);
	         count++;
	         ele += d;
	     }
	     
	     System.out.println((count == n) ? "YES":"NO");
	  }
     }
     
     // 760
     public int[] anagramMappings(int[] A, int[] B) {
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        
        int n = A.length;
        for(int i = 0;i<n;i++) {
            map.putIfAbsent(B[i],new ArrayList<>());
            map.get(B[i]).add(i);
        }
        
        int[] ans = new int[n];
        
        for(int i=0;i<n;i++){
            ArrayList<Integer> li = map.get(A[i]);
            ans[i] = li.get(li.size() - 1);
            li.remove(li.size() - 1);
            if(li.size() == 0) map.remove(A[i]);
        }
        
        return ans;   
    }

    class RandomizedSet {
        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random(); 
  
        public RandomizedSet() {
            
        }
        
        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val)) return false;

            map.put(val,list.size());
            list.add(val);

            return true;
        }
        
        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val)) return false;

            int targetIndex = map.get(val);
            int lastIndex = list.size()-1;

            map.put(list.get(lastIndex), targetIndex);
            map.remove(val);
            list.set(targetIndex,list.get(lastIndex));

            list.remove(lastIndex);

            return true;
        }
        
        /** Get a random element from the set. */
        public int getRandom() {
            int idx = rand.nextInt(list.size()); 
            return list.get(idx);
        }
    }   

    // https://www.geeksforgeeks.org/maximum-consecutive-ones-or-zeros-in-a-binary-array/

    class FreqStack {
    
        HashMap<Integer,Integer> freq = new HashMap<>();
        HashMap<Integer,Stack<Integer>> map = new HashMap<>();
        int maxFreq = 0;
        
        public FreqStack() {
            
        }
        
        public void push(int x) {
            freq.put(x,freq.getOrDefault(x,0) + 1);
            maxFreq = Math.max(maxFreq,freq.get(x));
            
            map.putIfAbsent(freq.get(x),new Stack<>());
            map.get(freq.get(x)).push(x);
        }
        
        public int pop() {
            int rv = map.get(maxFreq).pop();
            freq.put(rv,maxFreq - 1);
            if(map.get(maxFreq).size() == 0) {
                map.remove(maxFreq);
                maxFreq--;
            }
            return rv;
            
        }
    }

    //https://www.geeksforgeeks.org/check-two-strings-k-anagrams-not/
    //Sol 1
    public static boolean kAnagram(String s1,String s2){
        if(s1.length() != s2.length()) return false;
        sort(s1);
        sort(s2);
        int count = 0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i) != s2.charAt(i) && ++count > k) return false;
        }
        return true;
    }
    
    //Sol 2
    public static boolean kAnagram(String s1,String s2){
        if(s1.length() != s2.length()) return false;
        int[] f1 = new int[26];
        int[] f2 = new int[26];
        int count = 0;

        for(int i=0;i<s1.length();i++) f1[s1.charAt(i) - 'a']++;
        for(int i=0;i<s1.length();i++) f2[s2.charAt(i) - 'a']++;
        for(int i=0;i<s1.length();i++) count += Math.abs(f2[i] - f1[i]);
        
        return true;
    }

    // https://www.geeksforgeeks.org/check-anagram-string-palindrome-not/
    public static boolean palindromeAnagram(String str){
        int n = str.length();
        int[] freq = new int[26];
        for(int i=0;i<n;i++) freq[str.charAt(i) - 'a']++;

        int oddCount = 0;
        for(int i=0;i<26;i++){
            if((freq[i] % 2) != 0 && ++oddCount > 1) return false;   
        }

        return true;
    }

    // -> https://www.geeksforgeeks.org/check-if-frequency-of-all-characters-can-become-same-by-one-removal/

    // 407
    public int trapRainWater(int[][] arr) {
        if(arr.length == 0 || arr[0].length == 0) return 0;
        int n = arr.length;
        int m = arr[0].length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return arr[a/m][a%m] - arr[b/m][b%m];
        });
        
        int maxWater = 0;
        int boun = 0;
        boolean[][] vis = new boolean[n][m];
        
        for(int i=0;i<n;i++){
            for(int j =0;j<m;j++){
                if(i==0 || j==0 || i == n - 1 || j == m - 1){
                    pq.add(i*m+j);
                    vis[i][j] = true;
                }`
            }
        }
        
        
        int[][] dir = {{1,0},{0,1},{0,-1},{-1,0}};
        while(pq.size() != 0){
            int idx = pq.poll();
            int r = idx/m;
            int c = idx%m;
            
            boun = Math.max(boun,arr[r][c]);
            maxWater += Math.max(0, boun - arr[r][c]);
            for(int d = 0;d<4;d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if(x>=0 && y>=0 && x<n && y<m && !vis[x][y]){
                    
                    vis[x][y] = true;
                    pq.add(x * m + y);
                }
            }  
        }
        
        return maxWater;
        
    }






}