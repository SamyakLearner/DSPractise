public class l003_CutType{

    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+" ");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }

    public static int mcm_rec(int[] arr,int si,int ei,int[][] dp){
        if(si + 1 == ei){
            return dp[si][ei] = 0;
        }

        if(dp[si][ei]!=0) return dp[si][ei];

        int myAns = (int)1e8;
        for(int cut = si + 1;cut<ei;cut++){
            int leftTree = mcm_rec(arr,si,cut,dp);
            int rightTree = mcm_rec(arr,cut,ei,dp);

            int myCost = leftTree +  arr[si] * arr[cut] * arr[ei] + rightTree;
            
            myAns = Math.min(myAns, myCost);
        }

        return dp[si][ei] = myAns;
    }

    public static int mcm_DP(int[] arr,int SI,int EI,int[][] dp){
        int n = arr.length;
        for(int gap = 1; gap < n;gap++){
            for(int si = 0, ei = gap; ei < n; si++,ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }
        
                int myAns = (int)1e8;
                for(int cut = si + 1;cut<ei;cut++){
                    int leftTree = dp[si][cut];
                    int rightTree = dp[cut][ei];
        
                    int myCost = leftTree +  arr[si] * arr[cut] * arr[ei] + rightTree;
                    
                    myAns = Math.min(myAns, myCost);
                }
        
                dp[si][ei] = myAns;
            }
        }

        return dp[SI][EI];
    }

    public static void mcm(){
        int[] arr = {1,2,3,4,3,7,5,10,45,234};
        int n = arr.length;

        int[][] dp = new int[n][n];
        System.out.println(mcm_rec(arr,0,n-1,dp));

        print2D(dp);
    }

    // for you -> https://www.geeksforgeeks.org/minimum-maximum-values-expression/

    public static class minMaxPair{
        int minVal = (int) 1e8;
        int maxVal = 0;

        minMaxPair(int minVal,int maxVal){
            this.minVal = minVal;
            this.maxVal = maxVal;
        }

        minMaxPair(){

        }

        public String toString(){
            return "(" + this.minVal + ", " + this.maxVal +  ")";
        }
    }

    
    public static int evaluate(char ch,int v1, int v2){
        if(ch == '+') return v1 + v2;
        else if(ch == '+') return v1 - v2;    
        else return v1 * v2;
    }

    public static minMaxPair minMaxValue(String str,int si,int ei,minMaxPair[][] dp){
        if(si == ei){
            int val = str.charAt(si) - '0';
            return dp[si][ei] = new minMaxPair(val,val);
        }

        if(dp[si][ei] != null) return dp[si][ei];
        
        
        minMaxPair myAns = new minMaxPair();

        for(int cut = si + 1; cut < ei; cut+=2){
            minMaxPair leftTree = minMaxValue(str,si , cut - 1,dp);
            minMaxPair rightTree = minMaxValue(str,cut + 1, ei,dp);

            
            char ch = str.charAt(cut);
            myAns.minVal = Math.min(myAns.minVal,evaluate(ch,leftTree.minVal,rightTree.minVal));
            myAns.maxVal = Math.max(myAns.maxVal,evaluate(ch,leftTree.maxVal,rightTree.maxVal));
        }

        return dp[si][ei] = myAns;
    }

    public static minMaxPair minMaxValue_DP(String str,int SI,int EI,minMaxPair[][] dp){
        int n = str.length();
        for(int gap = 0 ; gap < n; gap++){
            for(int si = 0, ei = gap; ei < n ; si++,ei++){
                if(si == ei){
                    int val = str.charAt(si) - '0';
                    dp[si][ei] = new minMaxPair(val,val);
                    continue;
                }
                
                minMaxPair myAns = new minMaxPair();
        
                for(int cut = si + 1; cut < ei; cut+=2){
                    minMaxPair leftTree = dp[si][cut-1];//minMaxValue(str,si , cut - 1,dp);
                    minMaxPair rightTree = dp[cut+1][ei];//minMaxValue(str,cut + 1, ei,dp);
        
                    
                    char ch = str.charAt(cut);
                    myAns.minVal = Math.min(myAns.minVal,evaluate(ch,leftTree.minVal,rightTree.minVal));
                    myAns.maxVal = Math.max(myAns.maxVal,evaluate(ch,leftTree.maxVal,rightTree.maxVal));
                }
        
                dp[si][ei] = myAns;
            }
        }

        return dp[SI][EI];
    }


    public static void solve(){
        mcm();
    }

    public static void main(String[] args){
        solve();
    }
}