class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();

        // [finishTime, serverIndex]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int count[] = new int[k];

        // Initially all servers are available
        for(int i = 0; i < k; i++)
            available.add(i);

        for(int i = 0; i < arrival.length; i++) {
            // Release servers that have completed their tasks
            while(!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                long completed[] = busy.poll();
                available.add((int) completed[1]);
            }

            // All servers are busy, so drop this request
            if(available.isEmpty())
                continue;

            // Preferred server
            int preferred = i % k;

            // Finding the next available server
            Integer server = available.ceiling(preferred);

            // Wrap around if necessary
            if(server == null)
                server = available.first();

            // Assigning the request
            available.remove(server);
            count[server]++;
            long finishTime = (long) arrival[i] + load[i];
            busy.offer(new long[]{finishTime, server});
        }

        // Finding the maximum number of handled requests
        int max = 0;

        for(int c : count)
            max = Math.max(max, c);

        // Collecting all the busiest servers
        List<Integer> ans = new ArrayList<>();

        for(int i = 0; i < k; i++) {
            if(count[i] == max)
                ans.add(i);
        }

        return ans;
    }
}