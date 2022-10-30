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

        int[] answer = dfs(startX, startY, 0, 0);

        System.out.println(answer[0] +" "+ answer[1]);


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

    static int[] dfs(int x, int y, int near, int garbage) {
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny]) {
                continue;
            }
            if (maps[nx][ny] == 'g') {
                dfs(nx, ny, near, garbage + 1);
                visit[nx][ny] = false;
            } else if (maps[nx][ny] == 'n') {
                dfs(nx, ny, near + 1, garbage);
                visit[nx][ny] = false;
            } else if (maps[nx][ny] == '.') {
                dfs(nx, ny, near, garbage);
                visit[nx][ny] = false;
            } else if (maps[nx][ny] == 'F') {
                if (garbage < result[0] || (garbage == result[0] && near < result[1])) {
                    result[0] = garbage;
                    result[1] = near;
                }
            }
        }
        return result;
    }
    /*
    static int[] dfs(int x, int y, boolean[][] visit) {
        int[] result = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        Stack<Node> stack = new Stack<>();

        visit[x][y] = true;
        stack.push(new Node(x, y, 0, 0));

        while (!stack.isEmpty()) {
            Node now = stack.pop();
            for (int dir = 0; dir < 4; dir++) {
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny]) {
                    continue;
                }
                if (maps[nx][ny] == 'n') {
                    stack.push(new Node(nx, ny, now.near + 1, now.garbage));
                    visit[nx][ny] = true;
                } else if (maps[nx][ny] == 'g') {
                    stack.push(new Node(nx, ny, now.near, now.garbage + 1));
                    visit[nx][ny] = true;
                } else if (maps[nx][ny] == '.') {
                    stack.push(new Node(nx, ny, now.near, now.garbage));
                    visit[nx][ny] = true;
                }else if (maps[nx][ny] == 'F') {
                    if (now.garbage <= result[0] && now.near <= result[1]) {
                        result[0] = now.garbage;
                        result[1] = now.near;
                    }
                }
            }
        }
        return result;
    }
     */
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