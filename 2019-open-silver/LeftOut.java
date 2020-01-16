import java.io.*;
import java.util.*;
import java.lang.*;

public class LeftOut {
  public static void main(String[] args) throws Exception {
    String name = "leftout";
    BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
    int N = Integer.parseInt(br.readLine());
    char[][] cows = new char[N][];
    for (int i = 0; i < N; i++) {
      String s = br.readLine();
      cows[i] = s.toCharArray();
    }
    br.close();
    for (int i = 0; i < N; i++) {
      if (cows[0][i] != 'R') {
        for (int j = 0; j < N; j++) {
          cows[j][i] = cows[j][i] == 'R' ? 'L' : 'R';
        }
      }
    }
    for (int i = 1; i < N; i++) {
      if (cows[i][0] != 'R') {
        for (int j = 0; j < N; j++) {
          cows[i][j] = cows[i][j] == 'R' ? 'L' : 'R';
        }
      }
    }
    int row = -1;
    int col = -1;
    int zeroRows = 0;
    int mixRows = 0;
    int zeroCols = 0;
    int mixCols = 0;
    for (int i = 1; i < N; i++) {
      int zeros = 0;
      for (int j = 1; j < N; j++) {
        if (cows[i][j] == 'L') {
          zeros++;
        }
      }
      if (zeros == N - 1) {
        zeroRows++;
        if (row < 0) {
          row = i;
        }
      } else if (zeros == 1) {
        mixRows++;
        if (row < 0) {
          row = i;
        }
      }
    }
    for (int i = 1; i < N; i++) {
      int zeros = 0;
      for (int j = 1; j < N; j++) {
        if (cows[j][i] == 'L') {
          zeros++;
        }
      }
      if (zeros == N - 1) {
        zeroCols++;
        if (col < 0) {
          col = i;
        }
      } else if (zeros == 1) {
        mixCols++;
        if (col < 0) {
          col = i;
        }
      }
    }
    String ret = "-1";
    if (zeroRows == N - 1 && zeroCols == N - 1) {
      ret = "1 1";
    } else if (zeroRows == 1 && mixCols == N - 1) {
      ret = (row + 1) + " 1";
    } else if (zeroCols == 1 && mixRows == N - 1) {
      ret = "0 " + (col + 1);
    } else if (mixRows == 1 && mixCols == 1) {
      ret = (row + 1) + " " + (col + 1);
    }
    PrintWriter pw = new PrintWriter(name + ".out");
    System.out.println(ret);
    pw.println(ret);
    pw.close();
  }
}