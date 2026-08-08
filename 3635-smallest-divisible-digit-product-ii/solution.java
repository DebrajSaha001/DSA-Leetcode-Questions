class Solution {
    private static final int[] E2 = {0,0,1,0,2,0,1,0,3,0};
    private static final int[] E3 = {0,0,0,1,0,0,1,0,0,2};
    private static final int[] E5 = {0,0,0,0,0,1,0,0,0,0};
    private static final int[] E7 = {0,0,0,0,0,0,0,1,0,0};

    public String smallestNumber(String num, long t) {
        long A=0,B=0,C=0,D=0;
        while(t%2==0){t/=2;A++;}
        while(t%3==0){t/=3;B++;}
        while(t%5==0){t/=5;C++;}
        while(t%7==0){t/=7;D++;}
        if(t!=1) return "-1";

        int len = num.length();
        long Lmin = C + D + minSlots(A,B);

        if(len < Lmin){
            return greedyFill((int)Lmin, A,B,C,D);
        }

        boolean hasZero = num.indexOf('0') != -1;
        if(!hasZero){
            long ua=0,ub=0,uc=0,ud=0;
            for(int i=0;i<len;i++){
                int dig = num.charAt(i)-'0';
                ua+=E2[dig]; ub+=E3[dig]; uc+=E5[dig]; ud+=E7[dig];
            }
            if(ua>=A && ub>=B && uc>=C && ud>=D) return num;
        }

        long[] pa=new long[len+1], pb=new long[len+1], pc=new long[len+1], pd=new long[len+1];
        for(int i=0;i<len;i++){
            int dig=num.charAt(i)-'0';
            pa[i+1]=pa[i]+E2[dig];
            pb[i+1]=pb[i]+E3[dig];
            pc[i+1]=pc[i]+E5[dig];
            pd[i+1]=pd[i]+E7[dig];
        }

        int zpos = num.indexOf('0');
        int maxI = (zpos==-1) ? len-1 : Math.min(len-1, zpos);

        for(int i=maxI;i>=0;i--){
            long ua=pa[i],ub=pb[i],uc=pc[i],ud=pd[i];
            int dOrig = num.charAt(i)-'0';
            int remLen = len-i-1;
            for(int v=dOrig+1; v<=9; v++){
                long na = Math.max(A-ua-E2[v],0);
                long nb = Math.max(B-ub-E3[v],0);
                long nc = Math.max(C-uc-E5[v],0);
                long nd = Math.max(D-ud-E7[v],0);
                if(feasible(remLen, na,nb,nc,nd)){
                    String suffix = greedyFill(remLen, na,nb,nc,nd);
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i).append((char)('0'+v)).append(suffix);
                    return sb.toString();
                }
            }
        }

        return greedyFill(len+1, A,B,C,D);
    }

    private long minSlots(long a, long b){
        a=Math.max(a,0); b=Math.max(b,0);
        long tmax = Math.min(a,b);
        long best = Long.MAX_VALUE;
        for(long tt=0; tt<=tmax; tt++){
            long remA=Math.max(a-tt,0);
            long remB=Math.max(b-tt,0);
            long slots = tt + ceilDiv(remA,3) + ceilDiv(remB,2);
            if(slots<best) best=slots;
        }
        return best;
    }

    private long ceilDiv(long x,long y){ return (x+y-1)/y; }

    private boolean feasible(long L, long a,long b,long c,long d){
        if(c+d > L) return false;
        long rem = L-c-d;
        return minSlots(a,b) <= rem;
    }

    private String greedyFill(int L, long a, long b, long c, long d){
        char[] res = new char[L];
        a=Math.max(a,0);b=Math.max(b,0);c=Math.max(c,0);d=Math.max(d,0);
        for(int pos=0; pos<L; pos++){
            int remLen = L-pos-1;
            for(int v=1; v<=9; v++){
                long na=Math.max(a-E2[v],0);
                long nb=Math.max(b-E3[v],0);
                long nc=Math.max(c-E5[v],0);
                long nd=Math.max(d-E7[v],0);
                if(feasible(remLen, na,nb,nc,nd)){
                    res[pos]=(char)('0'+v);
                    a=na;b=nb;c=nc;d=nd;
                    break;
                }
            }
        }
        return new String(res);
    }
}
