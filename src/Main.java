import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

        int[] answer = dijkstra(startX, startY);

        System.out.println(answer[0] + " " + answer[1]);

    }
    static boolean[][] visit;
    static char[][] maps;
    static int n;
    static int m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

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
    static int[] dijkstra(int x, int y) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        int[] result = new int[2];
        q.offer(new Node(x, y, 0, 0));
        visit[x][y] = true;
        loop : while (true) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny]) {
                    continue;
                }
                if (maps[nx][ny] == '.') {
                    q.offer(new Node(nx, ny, now.near, now.garbage));
                    visit[nx][ny] = true;
                } else if (maps[nx][ny] == 'g') {
                    q.offer(new Node(nx, ny, now.near, now.garbage + 1));
                    visit[nx][ny] = true;
                } else if (maps[nx][ny] == 'n') {
                    q.offer(new Node(nx, ny, now.near + 1, now.garbage));
                    visit[nx][ny] = true;
                } else if (maps[nx][ny] == 'F') {
                    result[0] = now.garbage;
                    result[1] = now.near;
                    break loop;
                }
            }
        }
        return result;
    }

}

class Node implements Comparable<Node>{
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

    @Override
    public int compareTo(Node o) {
        if (garbage == o.garbage) {
            return Integer.compare(near, o.near);
        }
        return Integer.compare(garbage, o.garbage);
    }
}