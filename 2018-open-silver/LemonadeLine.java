import java.io.*;
import java.util.*;
import java.lang.*;

public class LemonadeLine {
  public static void main(String[] args) throws Exception {
    String name = "lemonade";
    BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
    InputLine line = new InputLine(br.readLine());
    int N = line.nextInt();
    long[] cows = new long[N];
    line = new InputLine(br.readLine());
    for (int i = 0; i < N; i++) {
      cows[i] = line.nextLong();
    }
    br.close();
    Arrays.sort(cows);
    int min = N;
    for (int i = 0; i < N; i++) {
      if (cows[cows.length - i - 1] < i) {
        min = i;
        break;
      }
    }
    System.out.println(min);
    PrintWriter pw = new PrintWriter(name + ".out");
    pw.println(min);
    pw.close();
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