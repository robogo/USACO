import java.io.*;
import java.util.*;
import java.lang.*;

public class MultiMoo {
  public static void main(String[] args) throws Exception {
    String name = "multimoo";
    BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
    InputLine line = new InputLine(br.readLine());
    int N = line.nextInt();
    Number[][] board = new Number[N][N];
    for (int i = 0; i < N; i++) {
      line = new InputLine(br.readLine());
      for (int j = 0; j < N; j++) {
        board[i][j] = new Number(line.nextInt());
      }     
    }
    br.close();
    int max = 0;
    int s = 1;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (board[i][j].v == s) {
          continue;
        }
        int v = board[i][j].n;
        int r = find(board, i, j, s, v, v);
        //System.out.println(v + "=" + r);
        if (r > max) {
          max = r;
        }
      }
    }
    System.out.println(max);
    int max2 = 0;
    s++;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N-1; j++) {
        if (board[i][j].v == s && board[i][j+1].v == s) {
          continue;
        }
        s++;
        int v1 = board[i][j].n;
        int v2 = board[i][j+1].n;
        int r = find(board, i, j, s, v1, v2);
        //System.out.println(v1+"-"+v2+ "=" + r);
        if (r > max2) {
          max2 = r;
        }
      }
    }
    s++;
    for (int i = 0; i < N-1; i++) {
      for (int j = 0; j < N; j++) {
        if (board[i][j].v == s && board[i+1][j].v == s) {
          continue;
        }
        s++;
        int v1 = board[i][j].n;
        int v2 = board[i+1][j].n;
        int r = find(board, i, j, s, v1, v2);
        //System.out.println(v1+"-"+v2+ "=" + r);
        if (r > max2) {
          max2 = r;
        }
      }
    }
    System.out.println(max2);
    PrintWriter pw = new PrintWriter(name + ".out");
    pw.println(max);
    pw.println(max2);
    pw.close();
  }
  static int find(Number[][] board, int i, int j, int s, int v1, int v2) {
    ArrayList<Long> r = new ArrayList<>();
    r.add(pos(i, j));
    board[i][j].v = s;
    int start = 0;
    int end = 1;
    for (int k = start; k < end; k++) {
      long pos = r.get(k);
      int x = (int)(pos >> 32);
      int y = (int)pos;
      check(board, x, y-1, v1, v2, s, r);
      check(board, x, y+1, v1, v2, s, r);
      check(board, x-1, y, v1, v2, s, r);
      check(board, x+1, y, v1, v2, s, r);
      if (k == end - 1) {
        end = r.size();
      }
    }
    return r.size();
  }
  static class Number {
    int n;
    int v;
    public Number(int i) {
      n = i;
    }
  }
  static long pos(int i, int j) {
    return ((long)i << 32) + j;
  }
  static void check(Number[][] board, int i, int j, int v1, int v2, int s, ArrayList<Long> r) {
    if (i >= 0 && i < board.length &&
        j >= 0 && j < board[0].length &&
        (board[i][j].n == v1 || board[i][j].n == v2) &&
        board[i][j].v < s) {
          r.add(pos(i, j));
          board[i][j].v = s;
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
  }
}