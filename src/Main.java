import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        maps = new char[n][m];
        visit = new boolean[n][m];

        int startX = 0;
        int startY = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                maps[i][j] = str.charAt(j);
                if (maps[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        makeNearGarbage(maps);

        


    }
    static boolean[][] visit;
    static char[][] maps;
    static int n;
    static int m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] result = {Integer.MAX_VALUE, Integer.MAX_VALUE};

    static void makeNearGarbage(char[][] maps) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i][j] == '.') {
                    for (int dir = 0; dir < 4; dir++) {
                        int x = i + dx[dir];
                        int y = j + dy[dir];
                        if ( x < 0 || x >= n ||
                                y < 0 || y >= m || maps[x][y] != 'g') {
                            continue;
                        }
                        maps[i][j] = 'n';
                    }
                }
            }
        }
    }
}

class Node{
    int x;
    int y;
    int near;
    int garbage;

    public Node(int x, int y, int near, int garbage) {
        this.x = x;
        this.y = y;
        this.near = near;
        this.garbage = garbage;
    }
}