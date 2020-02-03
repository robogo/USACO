import java.io.*;
import java.util.*;
import java.lang.*;

public class BerryPicking {
  public static void main(String[] args) throws Exception {
    String name = "berries";
    BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
    InputLine line = new InputLine(br.readLine());
    int N = line.nextInt();
    int K = line.nextInt();
    int[] trees = new int[N];
    line = new InputLine(br.readLine());
    for (int i = 0; i < N; i++) {
      trees[i] = line.nextInt();
    }
    br.close();
    Arrays.sort(trees);
    int max = 0;
    for (int i = trees[N - 1]; i > 0; i--) {
      int p = pick(trees, i, K);
      if (p > max) {
        max = p;
      }
    }
    PrintWriter pw = new PrintWriter(name + ".out");
    System.out.println(max);
    pw.println(max);
    pw.close();
  }
  static int pick(int[] trees, int n, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    int[] baskets = new int[k];
    int t = trees.length - 1;
    pq.add(trees[t]);
    for (int i = 0; i < k && pq.size() > 0; i++) {
      int top = pq.poll();
      baskets[i] = Math.min(top, n);
      top -= baskets[i];
      if (top > 0) {
        pq.add(top);
      }
      if (top < n && t > 0) {
        t--;
        pq.add(trees[t]);
      }
    }
    int total = 0;
    for (int i =  k / 2; i < k; i++) {
      total += baskets[i];
    }
    //System.out.println(Arrays.toString(baskets));
    return total;
  }
  static class InputLine {
    final StringTokenizer tokenizer;
    public InputLine(String line) {
      this.tokenizer = new StringTokenizer(line);
    }
    public int nextInt() {
      return Integer.parseInt(this.tokenizer.nextToken());
    }
  }
}