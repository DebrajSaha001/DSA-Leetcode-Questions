class Solution {
    public boolean checkIfPangram(String s) {
        for(int i=0;i<26;i++){
            char ch=(char)(i+'a');
            if(s.indexOf(ch)==-1) return false;
        }
        return true;  
    }
}

