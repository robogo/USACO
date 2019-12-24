import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SnowBoots {
  static int min = Integer.MAX_VALUE;
  public static void main(String[] args) throws Exception {
    String name = "snowboots";
    BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
    InputLine line = new InputLine(br.readLine());
    int N = line.nextInt();
    int B = line.nextInt();
    int[] tiles = new int[N];
    int[] b1 = new int[B];
    int[] b2 = new int[B];
    line = new InputLine(br.readLine());
    for (int i = 0; i < N; i++) {
      tiles[i] = line.nextInt();
    }
    for (int i = 0; i < B; i++) {
      line = new InputLine(br.readLine());
      b1[i] = line.nextInt();
      b2[i] = line.nextInt();
    }
    br.close();

    int start = 0;
    for (int i = 0; i < B; i++) {
      int s = walk(tiles, b1, b2, i, start);
      if (s > start) {
        start = s;
      }
      if (start >= tiles.length) {
        min = i;
        break;
      }
    }

    System.out.println(min);

    PrintWriter pw = new PrintWriter(name + ".out");
    pw.write("" + min);
    pw.close();
  }

  static int walk(int[] tiles, int[] b1, int[] b2, int b, int start) {
    for (; start > 0 && b > 0; start--) {
      if (tiles[start] <= b1[b] && tiles[start] <= b1[b - 1]) {
        break;
      }
    }
    int count = 0;
    for (int i = start; i < tiles.length; i++) {
      if (tiles[i] > b1[b]) {
        count++;
        if (count >= b2[b]) {
          return i - b2[b];
        }
      } else {
        count = 0;
      }
    }
    return tiles.length;
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