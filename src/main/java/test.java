import java.util.*;

public class test {
    public static void main(String[] args) {

        int numOfProducts = 6;
        int[] arr1 = {1, 2, 2, 3, 4, 5};
        int[] arr2 = {2, 4, 5, 5, 5, 6};
        List<Integer> products_from = new ArrayList<Integer>();
        products_from.add(1);
        products_from.add(2);
        products_from.add(2);
        products_from.add(3);
        products_from.add(4);
        products_from.add(5);


        List<Integer> products_to = new ArrayList<Integer>();
        products_to.add(2);
        products_to.add(4);
        products_to.add(5);
        products_to.add(5);
        products_to.add(5);
        products_to.add(6);

        int min  = getShoppingPatternsTrioMinimum(numOfProducts, products_from, products_to);
        System.out.println(min);


    }


    private static int getShoppingPatternsTrioMinimum(int numOfProducts,
                                                      List<Integer> products_from, List<Integer> products_to) {
        if (numOfProducts < 1 || products_from == null || products_to == null || products_to.size() != products_from.size())
            return -1;

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i <= numOfProducts; i++) {
            graph.put(i, (new HashSet<Integer>()));
        }

        for (int i = 0; i < products_from.size(); i++) {
            //since its undirected
            graph.get(products_from.get(i)).add(products_to.get(i));
            graph.get(products_to.get(i)).add(products_from.get(i));
        }

        int count = Integer.MAX_VALUE;
        for (int i = 0; i <= numOfProducts; i++) {
            for (int j = i + 1; j <= numOfProducts; j++) {
                for (int k = j + 1; k <= numOfProducts; k++) {
                    if (graph.get(i).contains(j) && graph.get(j).contains(k) && graph.get(k).contains(i)) {
                        // these vertices forms a TRio

//                        findMinimumValue(i,j,k,graph);
//                        findMinimumValue(j,i,k,graph);
//                        findMinimumValue(j,i,k,graph);


                        count = findMinimumValue(i, j, k, graph) + findMinimumValue(j, i, k, graph) + findMinimumValue(k, i, j , graph);

                    }
                }
            }
        }
        return count;
    }

    private static int findMinimumValue(int i, int x, int y, Map<Integer, Set<Integer>> graph) {
        int minValue = Integer.MAX_VALUE;
        Set<Integer> adjList = graph.get(i);
        for (int nodes : adjList) {
            if (nodes != x && nodes != y) {
                minValue = Math.min(minValue, nodes);
            }

        }
        if (minValue == Integer.MAX_VALUE) {
            minValue = 0;
        }
        return minValue;
    }

}
