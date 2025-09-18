class Solution {
    public int maxProfit(int[] prices) {
        int minimumPrice = Integer.MAX_VALUE;
        int maximumProfit = 0;

        for(int price : prices){
            if(price < minimumPrice){
                minimumPrice = price;
            }

            else if(price - minimumPrice > maximumProfit){
                maximumProfit = price - minimumPrice;
            }
        }

        return maximumProfit;
    }
}
