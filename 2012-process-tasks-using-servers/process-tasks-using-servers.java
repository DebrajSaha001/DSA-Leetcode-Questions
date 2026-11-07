class Solution {
    static class Server {
        int index;
        int weight;

        Server(int i, int w) {
            index = i;
            weight = w;
        }
    }

    static class Usage {
        long freeTime;
        Server server;

        Usage(long f, Server s) {
            freeTime = f;
            server = s;
        }
    }

    PriorityQueue<Server> pqs = new PriorityQueue<>((a, b) -> {
        if (a.weight == b.weight)
            return Integer.compare(a.index, b.index);
        
        return Integer.compare(a.weight, b.weight);
    });

    PriorityQueue<Usage> busy = new PriorityQueue<>((a, b) -> {
        return Long.compare(a.freeTime, b.freeTime);
    });

    public int[] assignTasks(int[] servers, int[] tasks) {
        for (int i = 0; i < servers.length; i++)
            pqs.add(new Server(i, servers[i]));

        int[] ans = new int[tasks.length];
        long time = 0;

        for (int i = 0; i < tasks.length; i++) {
            time = Math.max(time, i);

            // Release completed servers
            while (!busy.isEmpty() && busy.peek().freeTime <= time)
                pqs.add(busy.poll().server);

            // If no server is available, jump to earliest finish time
            if (pqs.isEmpty()) {
                time = busy.peek().freeTime;

                while (!busy.isEmpty() && busy.peek().freeTime <= time)
                    pqs.add(busy.poll().server);
            }

            // Assign task to the best available server
            Server server = pqs.poll();
            ans[i] = server.index;

            // Mark server as busy
            busy.add(new Usage(time + tasks[i], server));
        }

        return ans;
    }
}
