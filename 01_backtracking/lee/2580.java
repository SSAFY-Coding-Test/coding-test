import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 2580 {
    private static int[][] sudoku = new int[9][9];
    private static boolean[][] isUsedRow = new boolean[9][9];
    private static boolean[][] isUsedCol = new boolean[9][9];
    private static boolean[][] isUsedBox = new boolean[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0) {
                    isUsedRow[i][num - 1] = true;
                    isUsedCol[j][num - 1] = true;
                    isUsedBox[(i / 3) * 3 + (j / 3)][num - 1] = true;
                }
                sudoku[i][j] = num;
            }
        }

        backtracking(0, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean backtracking(int currentRow, int currentCol) {
        if (currentRow == 9) {
            return true;
        }

        if (currentCol == 9) {
            return backtracking(currentRow + 1, 0);
        }

        if (sudoku[currentRow][currentCol] != 0) {
            return backtracking(currentRow, currentCol + 1);
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidRow(currentRow, num) && isValidCol(currentCol, num) && isValidBox((currentRow / 3) * 3 + (currentCol / 3), num)) {
                isUsedRow[currentRow][num - 1] = true;
                isUsedCol[currentCol][num - 1] = true;
                isUsedBox[(currentRow / 3) * 3 + (currentCol / 3)][num - 1] = true;

                sudoku[currentRow][currentCol] = num;
                if (backtracking(currentRow, currentCol + 1)) {
                    return true;
                }

                sudoku[currentRow][currentCol] = 0;
                isUsedRow[currentRow][num - 1] = false;
                isUsedCol[currentCol][num - 1] = false;
                isUsedBox[(currentRow / 3) * 3 + (currentCol / 3)][num - 1] = false;
            }
        }
        return false;
    }

    private static boolean isValidRow(int currentRow, int num) {
        return !isUsedRow[currentRow][num - 1];
    }

    private static boolean isValidCol(int currentCol, int num) {
        return !isUsedCol[currentCol][num - 1];
    }

    private static boolean isValidBox(int boxIndex, int num) {
        return !isUsedBox[boxIndex][num - 1];
    }
}
