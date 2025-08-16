class Solution {
    public int maximum69Number (int num) {
        char[] numbers = String.valueOf(num).toCharArray();
        for(int i=0; i<numbers.length; i++){
            if(numbers[i] == '6'){
                numbers[i] = '9';
                break;
            }
        }

        return Integer.parseInt(new String(numbers));
    }
}
