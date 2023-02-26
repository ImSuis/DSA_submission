import java.util.*;

public class Q1A {

    static class Edge {
        int source, destination, time, cost;
        Edge(int source, int destination, int time, int cost) {
            this.source = source;
            this.destination = destination;
            this.time = time;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 5}, {0, 3, 2}, {1, 2, 5}, {3, 4, 5}, {4, 5, 6}, {2, 5, 5}};
        int[] charges = {10, 2, 3, 25, 25, 4};
        int source = 0, destination = 5, timeConstraint = 14;

        int n = charges.length;
        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], time = edge[2], cost = charges[v];
            graph[u].add(new Edge(u, v, time, cost));
        }

        int[] minTime = new int[n];
        int[] minCost = new int[n];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minTime[source] = minCost[source] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(e -> e.time));
        pq.offer(new Edge(-1, source, 0, 0));
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (curr.destination == destination) {
                System.out.println("Cheapest route: " + curr.cost);
                break;
            }
            for (Edge next : graph[curr.destination]) {
                int newTime = curr.time + next.time;
                int newCost = curr.cost + next.cost;
                if (newTime <= timeConstraint && newTime < minTime[next.destination]) {
                    minTime[next.destination] = newTime;
                    minCost[next.destination] = newCost;
                    pq.offer(new Edge(next.source, next.destination, newTime, newCost));
                } else if (newTime <= timeConstraint && newTime == minTime[next.destination] && newCost < minCost[next.destination]) {
                    minCost[next.destination] = newCost;
                    pq.offer(new Edge(next.source, next.destination, newTime, newCost));
                }
            }
        }
    }
}