import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class utopiaCoins {

    public static int[] coins={1,7,10,22};
    static final int MAX = 100000;

    // tempResult array to store the temp results
    static int [] tempResult = new int[MAX + 1];

    // List to store the tempResult
    static List<Integer> results = new LinkedList<>();

    // Function to find the minimum number of coins to make the sum equals to X
    static int countMinCoins(int n, int m)
    {
        // Base case
        if (n == 0)
        {
            tempResult[0] = 0;
            return 0;
        }

        // If previously computed subproblem occurred
        if (tempResult[n] != -1)
            return tempResult[n];

        // Initialize result
        int ret = Integer.MAX_VALUE;

        // Try every coin that has smaller value than n
        for (int i = 0; i < m; i++)
        {
            if (coins[i] <= n)
            {
                int x = countMinCoins(n - coins[i],
                        m);

                // Check for Integer.MAX_VALUE to avoid overflow and see if tempResult
                // can be minimized
                if (x != Integer.MAX_VALUE)
                    ret = Math.min(ret, 1 + x);
            }
        }

        // Memorizing value of current state
        tempResult[n] = ret;
        return ret;
    }

    // Function to find the possible combination of coins to make the sum equal to X
    static void findSolution(int n, int m)
    {
        // Base Case
        if (n == 0)
        {
            // Print Solutions
            for (int it : results)
            {
                System.out.print(it + " ");
            }
            return;
        }

        for (int i = 0; i < m; i++)
        {
            // Try every coin that has
            // value smaller than n
            if (n - coins[i] >= 0 &&
                    tempResult[n - coins[i]] + 1 ==
                            tempResult[n])
            {
                // Add current denominations
                results.add(coins[i]);

                // Backtrack
                findSolution(n - coins[i], m);
                break;
            }
        }
    }

    // Function to find the minimum combinations of coins for value X
    static void countMinCoinsUtil(int X)
    {
        // Initialize tempResult with -1
        Arrays.fill(tempResult, -1);

        // Min coins
        int isPossible = countMinCoins(X, coins.length);

        // If no solution exists
        if (isPossible == Integer.MAX_VALUE)
        {
            System.out.print("-1");
        }

        // Backtrack to find the solution
        else
        {
            findSolution(X,coins.length);
        }
    }

    public static void main(String[] args) {
        countMinCoinsUtil(15);
    }

}
