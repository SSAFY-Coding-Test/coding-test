public class 14620 {
    private static int N;
    private static int min_cost = Integer.MAX_VALUE;
    private static int[][] cost;
    private static boolean[][] visited;
    private static final int[][] directions = {
        {0, -1}, {0, 1},
        {-1, 0}, {1, 0}
    };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        plantFlowers(0, 0);
        System.out.println(min_cost);
    }

    private static void plantFlowers(int count, int currentCost) {
        if (count == 3) {
            min_cost = Math.min(min_cost, currentCost);
            return;
        }

        for (int i = 2; i < N; i++) {
            for (int j = 2; j < N; j++) {
                if (isGoodZone(i, j)) {
                    visited[i][j] = true;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + directions[k][0];
                        int ny = j + directions[k][1];
                        visited[nx][ny] = true;
                    }

                    plantFlowers(count + 1, currentCost + get5Costs(i, j));

                    visited[i][j] = false;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + directions[k][0];
                        int ny = j + directions[k][1];
                        visited[nx][ny] = false;
                    }
                }
            }
        }
    }

    private static boolean isGoodZone(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];

            if (!isValid(nx, ny) || visited[nx][ny]) return false;
        }
        return true;
    }

    private static boolean isValid(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }

    private static int get5Costs(int x, int y) {
        int totalCost = cost[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];

            totalCost += cost[nx][ny];
        }
        return totalCost;
    }
}
