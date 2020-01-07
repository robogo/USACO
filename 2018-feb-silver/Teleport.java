import java.io.*;
import java.util.*;
import java.lang.*;

public class Teleport {
  public static void main(String[] args) throws Exception {
    String name = "teleport";
    BufferedReader br = new BufferedReader(new FileReader(name + ".in"));
    InputLine line = new InputLine(br.readLine());
    int N = line.nextInt();
    HashMap<Integer, Change> map = new HashMap<>();
    long total = 0;
    for (int i = 0; i < N; i++) {
      line = new InputLine(br.readLine());
      int a = line.nextInt();
      int b = line.nextInt();
      total += Math.abs(a - b);
      if (a < 0 && b > 0) {
        add(map, 0, 1);
        add(map, b, -2);
        add(map, 2 * b, 1);
      } else if (a > 0 && b > 0 && a < b && b > a * 2) {
        add(map, a * 2, 1);
        add(map, b, -2);
        add(map, 2 * (b - a), 1);
      } else if (a > 0 && b < 0) {
        add(map, b * 2, 1);
        add(map, b, -2);
        add(map, 0, 1);
      } else if (a < 0 && b < 0 && a > b && b < a * 2) {
        add(map, 2 * (b - a), 1);
        add(map, b, -2);
        add(map, 2 * a, 1);
      }
    }
    br.close();

    ArrayList<Change> list = new ArrayList<>(map.values());
    Collections.sort(list, new ChangeComparator());
    //System.out.println(list);

    long max = 0;
    Change pre = list.get(0);
    long change = pre.value;
    long save = 0;
    for (int i = 1; i < list.size(); i++) {
      Change curr = list.get(i);
      save += (curr.point - pre.point) * change;
      change += curr.value;
      pre = curr;      
      if (save > max) {
        max = save;
      }
    }

    long d = total - max;
    System.out.println("total:" + total + ",min:" + d);

    PrintWriter pw = new PrintWriter(name + ".out");
    pw.write("" + d);
    pw.close();
  }
  static void add(HashMap<Integer, Change> map, int p, int v) {
    Change change = map.get(p);
    if (change == null) {
      change = new Change();
      change.point = p;
      map.put(p, change);
    }
    
    change.value += v;
  }
  static class Change {
    int point;
    int value;
    public String toString() {
      return point + ":" + value;
    }
  }
  static class ChangeComparator implements Comparator<Change> {
    public int compare(Change c1, Change c2) {
      return c1.point - c2.point;
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