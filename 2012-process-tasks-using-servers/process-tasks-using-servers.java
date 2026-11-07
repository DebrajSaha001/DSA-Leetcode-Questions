class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        // Available servers: [weight, index]
        PriorityQueue<int []> available = new PriorityQueue<>(
            (a, b) -> {
                if(a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: [finishTime, weight, index]
        PriorityQueue<int []> busy = new PriorityQueue<>(
            (a, b) -> {
                if(a[0] != b[0])
                    return Long.compare(a[0], b[0]);

                if(a[1] != b[1])
                    return Integer.compare(a[1], b[1]);

                return Integer.compare(a[2], b[2]);
            }
        );

        int n = servers.length;
        int m = tasks.length;
        int ans[] = new int[m];

        // Initially all servers are available
        for(int i = 0; i < n; i++)
            available.offer(new int[]{servers[i], i});

        long time = 0;
        for(int j = 0; j < m; j++) {
            time = Math.max(time, j);

            // Release all servers that have finished
            while(!busy.isEmpty() && busy.peek()[0] <= time) {
                int server[] = busy.poll();
                available.offer(new int[]{server[1], server[2]});
            }

            // If no server is available move the time forward
            if(available.isEmpty()) {
                time = busy.peek()[0];

                // Release all the servers fininshing at this time
                while(!busy.isEmpty() && busy.peek()[0] <= time) {
                    int server[] = busy.poll();
                    available.offer(new int[]{server[1], server[2]});
                }
            }

            // Assign the task to the best available server
            int server[] = available.poll();
            int weight = server[0];
            int index = server[1];
            ans[j] = index;

            // Mark the server as busy
            busy.offer(new int[]{(int)(time + tasks[j]), weight, index});
        }

        return ans;
    }
}