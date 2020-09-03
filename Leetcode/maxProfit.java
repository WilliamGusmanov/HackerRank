class Solution {
    //we need to find the lowest price (to buy) and then find the highest price afterwards (to sell)
    //do we have choices? to buy/sell or not
    //what if we checked every day and looked ahead to see what the best day would be to sell and track the profit? O(n^2)
    //can we do this in linear time? 
    
    //maybe we can check everyday, see if it's better to buy that day or to sell that day or neither.
    //if you check sell, then subtract it from best buy price. store solution. 
    //if the buy price is lower than current buy price, update best buy price.
    //O(n) time solution
    
    public int maxProfit(int[] prices) {
        int bestBuyPrice = Integer.MAX_VALUE;
        int bestProfit = 0;
        if (prices.length < 2) 
            return 0;
        for (int i = 0; i < prices.length; i++){
            //check sell, check if sell is higher than buy. 
            int curr = prices[i];
            if (curr > bestBuyPrice)
                bestProfit = Math.max(bestProfit, curr - bestBuyPrice);
            if (curr < bestBuyPrice)
                bestBuyPrice = curr;
        }
        return bestProfit;
    }
}
