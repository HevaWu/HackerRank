/*Journey to the Moon
by amititkgp
Problem
Submissions
Leaderboard
Discussions
Editorial
The member states of the UN are planning to send  people to the Moon. But there is a problem. In line with their principles of global unity, they want to pair astronauts of  different countries.

There are  trained astronauts numbered from  to . But those in charge of the mission did not receive information about the citizenship of each astronaut. The only information they have is that some particular pairs of astronauts belong to the same country.

Your task is to compute in how many ways they can pick a pair of astronauts belonging to different countries. Assume that you are provided enough pairs to let you identify the groups of astronauts even though you might not know their country directly. For instance, if  are astronauts from the same country; it is sufficient to mention that  and  are pairs of astronauts from the same country without providing information about a third pair .

Input Format

The first line contains two integers,  and , separated by a single space.  lines follow. Each line contains integers separated by a single space  and  such that


and  and  are astronauts from the same country.

Constraints

Output Format

An integer that denotes the number of permissible ways to choose a pair of astronauts.

Sample Input

4 2
0 1
2 3
Sample Output

4
Explanation

Persons numbered  and  belong to same country, and those numbered  and  belong to same country. So the UN can choose one person out of  &  and another person out of  & . So the number of ways of choosing a pair of astronauts is .*/






/*
//Solution 1 just Math
1) Create an empty 2D vector.
2) For each I, and astronaut A and B there are can be following cases

    a) A and B are not in vector. So add them to vector and decrease n (number of single astronauts ) by 2.
    b) A is not in any vector. Append A to vector B. Decrease n by 1.
    c) B is not in any vector. Append B to vector A. Decrease n by 1.
    d) A and B are in vector. So, append all elements in vector B to vector A and delete vector B.
    After each of these cases, sort the vector. And to find if an element is in a vector we can now use binary search instead of linear one.

3) Now, we have n single astronauts. They can pair up between themselves in (n)(n-1)/2 ways and with others in (n * size of other group).
4) Others can pair them selves. To do this, we iterate over the original vector.

//Solution 2 DFS
Lets say somehow I find set A has a elements.
Answer = 0 (Since I don't have another country to pair with)
Now, I find set B with b elements.
Answer = axb; .................................................(1)
Then, I find set C with c elements.
Answer = (axb) + (axc) + (bxc) [because we can select a pair from A and B, or A and C or B and C]
But this can be written as
Answer = (axb) + (a+b)xc ......................................(2)
Now I find set D with d elements, and I've examined all people.
Answer = (axb) + (axc) + (axd) + (bxc) + (bxd) + (cxd)
Or
Answer = (axb) x (a+b)xc + (a+b+c)xd ..........................(3)
Do you see the pattern? That means when we find a new set, the new answer is the old answer + the sum of old values x new value.
 */

////////////////////////////////////////////////////////////////////////
//Java
//use ArrayList
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int P = sc.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < N; ++i){
            graph.add(new ArrayList<>(Arrays.asList(i)));
        }

        for(int i = 0; i < P; ++i){
            int num0 = sc.nextInt();
            int num1 = sc.nextInt();

            int e0 = checkGraph(graph, num0);
            int e1 = checkGraph(graph, num1);
            graph.get(e0).addAll(graph.get(e1));
            graph.remove(graph.get(e1));
        }

        long choice = 0;

        int total = N;
        for(int i = 0; i < graph.size(); ++i){
            int cur = graph.get(i).size();
            choice += cur*(total-cur);
            total = total-cur;
        }

        /*for(int i = 0; i < graph.size()-1; ++i){
            int cur = graph.get(i).size();
            for(int j = i+1; j < graph.size(); ++j){
                choice += graph.get(j).size() * cur;
            }
        }*/

        System.out.println(choice);
    }

    public static int checkGraph(List<List<Integer>> graph, int edge){
        for(int i = 0; i < graph.size(); ++i){
            if(graph.get(i).contains(edge))
                return i;
        }
        return -1;
    }
}




//use DFS
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int P = sc.nextInt();

        int[][] matrix = new int[N][N];
        for(int i = 0; i < P; ++i){
            int num0 = sc.nextInt();
            int num1 = sc.nextInt();

            matrix[num0][num1] = 1;
            matrix[num1][num0] = 1;
        }

        List<Integer> graph = new ArrayList<>();
        boolean[] visit = new boolean[N];
        for(int i = 0; i < N; ++i){
            if(!visit[i]){
                int curGraphNode = 1;
                visit[i] = true;
                curGraphNode = dfs(matrix, curGraphNode, visit, i, N);
                graph.add(curGraphNode);
            }
        }

        long choice = 0;

        int total = N;
        for(int i = 0; i < graph.size(); ++i){
            int cur = graph.get(i);
            choice += cur*(total-cur);
            total = total-cur;
        }

        /*for(int i = 0; i < graph.size()-1; ++i){
            int cur = graph.get(i).size();
            for(int j = i+1; j < graph.size(); ++j){
                choice += graph.get(j).size() * cur;
            }
        }*/

        System.out.println(choice);
    }

    public static int dfs(int[][] matrix, int count, boolean[] visit, int start, int N){
        for(int i = 0; i < N; ++i){
            if(matrix[start][i] == 1 && !visit[i]){
                visit[i] = true;
                ++count;
                count = dfs(matrix, count, visit, i, N);
            }
        }
        return count;
    }
}
