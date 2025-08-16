class Solution {
    public int reverse(int x) {
        String st = Integer.toString(x);

        boolean isNegative = st.charAt(0) == '-';

        if(isNegative){
            st = st.substring(1);
        }

        String revSt = new StringBuilder(st).reverse().toString();

        try{
            int rev = Integer.parseInt(revSt);
            return isNegative ? -rev : rev;
        }
        catch(NumberFormatException e){
            return 0;
        }
    }
}
