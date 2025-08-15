class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total_tank = 0;
        int current_tank = 0;
        int start = 0;

        for(int i=0; i<gas.length; i++){
            int diff = gas[i] - cost[i];
            total_tank += diff;
            current_tank += diff;

            if(current_tank < 0){
                start = i + 1;
                current_tank = 0;
            }
        }

        return total_tank >= 0 ? start : -1;
    }
}
