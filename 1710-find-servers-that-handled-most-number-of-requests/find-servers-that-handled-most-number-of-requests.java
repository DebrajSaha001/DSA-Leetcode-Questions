import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.TreeSet;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<int[]> serverSchedular = new PriorityQueue(new Comparator<int[]>() {  //expiry_time : server id
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
        });

        //available server load
        int[] serverLoadArr = new int[k];

        //int max load server
        int maxLoadServer = 0;  //use this to maintain max load server

        //treeset for next server
        TreeSet<Integer> availableServers = new TreeSet();

        //add all servers to available servers list
        for (int index = 0; index < k; index++) {
            availableServers.add(index);
        }

        for (int index = 0; index < arrival.length; index++) {
            int ts = arrival[index];
            int serverLoad = load[index];

            //step 1 - remove all servers from scheduler if task is finished
            while (!serverSchedular.isEmpty() && ts >= serverSchedular.peek()[0]) {
                int[] server = serverSchedular.poll();
                availableServers.add(server[1]);  //add to available servers
            }

            //steps 2 - get i % k server from available server. If not, get first available server
            //next available server
            if (availableServers.isEmpty()) {
                //drop request
            } else {
                Integer nextCandidate = availableServers.ceiling(index % k);
                if (nextCandidate == null) {
                    nextCandidate = availableServers.first();
                }

                availableServers.remove(nextCandidate);
                
                //make task and pushh to queue
                int[] newTask = new int[]{ts + serverLoad, nextCandidate};
                serverSchedular.offer(newTask);

                serverLoadArr[nextCandidate]++;

                maxLoadServer = Math.max(serverLoadArr[nextCandidate], maxLoadServer);
            }
        }

        //make result list
        ArrayList<Integer> mostBusyServers = new ArrayList();

        for (int index = 0; index < serverLoadArr.length; index++) {
            if (serverLoadArr[index] == maxLoadServer) {
                mostBusyServers.add(index);
            }
        }
        return mostBusyServers;
    }
}