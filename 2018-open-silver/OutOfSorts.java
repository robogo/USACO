import java.io.*;
import java.util.*;
import java.lang.*;

public class OutOfSorts {
  public static void main(String[] args) throws Exception {
    String name = "sort";
    BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
    InputLine line = new InputLine(br.readLine());
    int N = line.nextInt();
    Num[] arr = new Num[N];
    for (int i = 0; i < N; i++) {
      line = new InputLine(br.readLine());
      Num n = new Num();
      n.num = line.nextLong();
      n.pos = i;
      arr[i] = n;
    }
    br.close();
    // Assume this is NLog(N)
    Arrays.sort(arr, new NumComparator());
    int max = 0;
    for (int i = 0; i < N; i++) {
      int d = arr[i].pos - i + 1;
      if (d > max) {
        max = d;
      }
    }
    System.out.println(max);
    PrintWriter pw = new PrintWriter(name + ".out");
    pw.write("" + max);
    pw.close();
  }
  static class Num {
    long num;
    int pos;
  }
  static class NumComparator implements Comparator<Num> {
    public int compare(Num c1, Num c2) {
      if (c1.num == c2.num) return 0;
      return c1.num > c2.num ? 1 : -1;
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