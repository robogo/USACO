import java.io.*;
import java.util.*;
import java.lang.*;

public class MooTube {
  public static void main(String[] args) throws Exception {
    String name = "mootube";
    BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
    InputLine line = new InputLine(br.readLine());
    int N = line.nextInt();
    int Q = line.nextInt();
    ArrayList<Node>[] nodes = new ArrayList[N];
    for (int i = 0; i < N - 1; i++) {
      line = new InputLine(br.readLine());
      int v1 = line.nextInt() - 1;
      int v2 = line.nextInt() - 1;
      long rel = line.nextLong();
      if (nodes[v1] == null) {
        nodes[v1] = new ArrayList<>();
      }
      if (nodes[v2] == null) {
        nodes[v2] = new ArrayList<>();
      }
      nodes[v1].add(new Node(v2, rel));
      nodes[v2].add(new Node(v1, rel));
    }
    PrintWriter pw = new PrintWriter(name + ".out");
    for (int i = 0; i < Q; i++) {
      line = new InputLine(br.readLine());
      long k = line.nextLong();
      int v = line.nextInt() - 1;
      int r = find(nodes, v, k);
      pw.println("" + r);
    }    
    pw.close();
    br.close();
  }

  static int find(ArrayList<Node>[] nodes, int q, long k) {
    long[] ar = new long[nodes.length];
    int[] path = new int[nodes.length];    
    int start = 0;
    int end = 0;
    path[end++] = q;
    ar[q] = Long.MAX_VALUE;
    int count = end - start;
    int r = 0;
    int i = 0;
    while (i < count) {
      int curr = path[i + start];
      for (Node d : nodes[curr]) {
        if (ar[d.vid] == 0) {
          ar[d.vid] = Math.min(ar[curr], d.rel);
          if (ar[d.vid] >= k) {
            r++;
          }
          if (nodes[d.vid].size() > 1) {
            path[end++] = d.vid;
          }
        }
      }
      if (++i == count) {
        start += count;
        i = 0;
        count = end - start;
      }
    }
    return r;
  }

  static class Node {
    int vid;
    long rel;
    public Node(int v, long r) {
      vid = v;
      rel = r;
    }
  }

  static class InputLine {
    final StringTokenizer tokenizer;
    public InputLine(String line) {
      this.tokenizer = new StringTokenizer(line);
    }
    public int nextInt() {
      return Integer.parseInt(this.tokenizer.nextToken());
    }
    public long nextLong() {
      return Long.parseLong(this.tokenizer.nextToken());
    }
  }
}