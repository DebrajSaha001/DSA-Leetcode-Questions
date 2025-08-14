class Solution {
    public String largestGoodInteger(String num) {
        String mxGood = "";

        for(int i=0; i<=num.length()-3; i++){
            String a = num.substring(i, i+3);

            if(a.charAt(0) == a.charAt(1) && a.charAt(1) == a.charAt(2)){
                if(mxGood.equals("") || a.compareTo(mxGood) > 0){
                    mxGood = a;
                }
            }
        }

        return mxGood;
    }
}
