import java.util.*;

public class Application {


//    public static void main(String[] args) {
//        KafkaTopicConfig kfg=new KafkaTopicConfig();
//        kfg.sendMessage("Ishan");
//    }
static List<Integer> ans=new ArrayList<>();
    public static void main(String[] args)
    {
        // Creating a graph with 5 vertices
        int V = 7;

        Graph g = new Graph(V);

        int[] arr1={1,2,2,3,4,5};
        int[] arr2={2,4,5,5,5,6};

        int[] trioArray=new int[10];
         Map<Integer, HashSet<Integer>> hs=new HashMap<>();

        for (int x = 0; x < arr1.length; x++) {

            g.addEdge(arr1[x], arr2[x]);


            if (hs.containsKey(arr1[x])) {
                hs.get(arr1[x]).add(arr2[x]);
                trioArray[arr1[x]]++;
            } else {
                HashSet<Integer> objects = new HashSet<>();
                objects.add(arr2[x]);
                hs.put(arr1[x], objects);
                trioArray[arr1[x]]++;
            }

            if (hs.containsKey(arr2[x])) {
                hs.get(arr2[x]).add(arr1[x]);
                trioArray[arr2[x]]++;
            } else {
                HashSet<Integer> objects = new HashSet<>();
                objects.add(arr1[x]);
                hs.put(arr2[x], objects);
                trioArray[arr2[x]]++;
            }
        }




        g.printAdjacencyList();

        System.out.println(hs);


        //2,4,5



        List<Integer> ls=new ArrayList<>();

        for(int x=0;x<trioArray.length;x++) {
            if(trioArray[x]>=2){
                ls.add(x);
            }
        }
        for(int i:ls){
            //trioHelper(g, 0,i , hs.get(i));
        }


        findTriosIfANY(hs,ls);
        int minSum=0;

        for(Map.Entry<Integer,HashSet<Integer>> k:hs.entrySet()){
            if(ls.contains(k.getKey())){
                    for(int a:k.getValue()){
                        if(!ls.contains(a)){
                            minSum+=a;
                        }
                    }
            }
        }

        System.out.println(minSum);
        System.out.println(Arrays.toString(trioArray));
    }

    private static void findTriosIfANY(Map<Integer, HashSet<Integer>> hs, List<Integer> ls) {
    List<Integer> trioList=new ArrayList<>();
    //2->1,4,
        int count=1;
        //2
        //List<> temp=new ArrayList();
        for(int i:ls){

            //1,4,5
        HashSet<Integer> integers = hs.get(i);

        for(Integer t1: integers){
            if(hs.get(t1).contains(i)){

            }
        }


    }



    }


//    public static void trioHelper(Graph G, int hops, int startNode,HashMap<Integer,Set<Integer>> nextNodes) {
//        // if node 2 hops away from start
//        if(hops == 2) {
//
//            for(int item : nextNodes.entrySet()) {
//                // if node is adj to start then its a trio
//                if(startNode == item)
//                        ans.add(item);
//            }
//        }
//        else {
//            // if less than 2 hops get adjs
//            for(Integer item : nextNodes.values()) {
//                // if node == start ignore since its where we just came from
//                if(item == startNode) continue;
//                trioHelper(G, hops + 1, startNode,hashmap.get(item) );
//            }
//
//        }
//    }
}
