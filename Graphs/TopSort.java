import java.util.*;

public class TopSort {

      /* Function to build graph based on PreReq List */
      public static Map<Character, List<Character>> buildGraph(List<PreReq> reqs) {
        Map<Character, List<Character>> map = new HashMap<>();
        for (int i = 0; i < reqs.size(); i++) {
            Character A = reqs.get(i).a;
            Character B = reqs.get(i).b;
            if (map.get(A) == null) map.put(A, new ArrayList<>(){{add(B);}});
            else {
                List<Character> currentList = map.get(A);
                currentList.add(B);
                map.put(A, currentList);
            }
        }
        return map;
    }

    /* Function to find the Topological Sort of a Graph */
    public static Deque<Character> topSort(Map<Character, List<Character>> graph) {
        if (graph.size() == 0) return new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        Deque<Character> order = new LinkedList<>();
        for (Character c : graph.keySet()) {
            if (visited.contains(c)) continue;
            topSortHelper(graph, visited, order, c);
        }
        return order;
    }
    /* Helper for the Topological Sort */
    public static void topSortHelper(Map<Character, List<Character>> graph, Set<Character> visited, Deque<Character> order, Character node) {
        visited.add(node);
        if (graph.get(node) == null) 
            order.offerFirst(node);
        else {
            for (Character adjVertex : graph.get(node)) {
                if (visited.contains(adjVertex)) {
                    continue;
                }
                topSortHelper(graph, visited, order, adjVertex);
            }
            order.offerFirst(node);
       }
    }

    public static void main(String[] args) {
        /* Practice building a graph with a list as a value */
        List<PreReq> reqs = new ArrayList<>();
        reqs.add(new PreReq('A', 'B')); 
        reqs.add(new PreReq('B', 'C')); 
        reqs.add(new PreReq('C', 'D')); 
        reqs.add(new PreReq('B', 'E')); 
        reqs.add(new PreReq('D', 'F')); 
        reqs.add(new PreReq('E', 'F')); 
        reqs.add(new PreReq('F', 'G')); 


        Map<Character, List<Character>> graph = buildGraph(reqs);
        System.out.println("Here is the dependency graph: " + graph);

        Deque<Character> order = topSort(graph);
        System.out.println("Here is the Topological Sort of the graph: " + order);
    }
}

class PreReq {
    Character a;
    Character b;
    PreReq(char a, char b) {
        this.a = Character.valueOf(a);
        this.b = Character.valueOf(b);
    }
    PreReq(Character a, Character b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public String toString() {
        return this.a + "," + this.b;
    }
}