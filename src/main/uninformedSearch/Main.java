package main.uninformedSearch;

public class Main {

    public static void main(String[] args) {
        int goalNumber = 466;
        int[] numbers = {50, 25, 5, 8, 1, 6};
        int count = 0;

        DFS dfs1 = new DFS(goalNumber, numbers);

        while (count < 5) {
            dfs1.randomizeOrder();
            dfs1.initializeStacks();
            dfs1.search();
            count++;
            System.out.print("\n---------------------------\n");
        }
    }
}
