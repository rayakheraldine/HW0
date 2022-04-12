import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;

    public Main() {
    }

    public static void theStudentsGame() {
        scanner = new Scanner(System.in);
        System.out.println("Dear president, please enter the board’s size.");
        int rows = scanner.nextInt();
        scanner.next();
        int cols = scanner.nextInt();
        int[][] Board = new int[rows][cols];

        do {
            System.out.println("Dear president, please enter the cell’s indexes.");
            scanner = new Scanner(System.in);
            int x = Integer.parseInt(scanner.next().replaceAll(",", ""));

            for(int y = scanner.nextInt(); !inbound(rows, cols, x, y); y = scanner.nextInt()) {
                System.out.println("The cell is not within the board’s boundaries, enter a new cell.");
                scanner = new Scanner(System.in);
                x = Integer.parseInt(scanner.next().replaceAll(",", ""));
            }
        } while(scanner.nextLine() != "Yokra");

        PrintBoard(rows, cols, Board);
    }

    public static boolean inbound(int row, int col, int in_row, int in_col) {
        return in_col < col && in_col >= 0 && in_row < row && in_row >= 0;
    }

    public static void FriendsConditions(int stud_row, int stud_col, int[][] Board, int rows, int cols) {
        int friends = 0;
        int Academic_status = Board[stud_row][stud_col];
        int Diag = GetDiagFriends(stud_row, stud_row, rows, cols, Board);
        int Horiz = GetHorizFriends(stud_row, stud_row, rows, cols, Board);
        int Vert = GetVertFriends(stud_row, stud_row, rows, cols, Board);
         friends = friends + Diag + Horiz + Vert;
        if (Academic_status == 1) {
            if (friends <= 1) {
                Board[stud_row][stud_col] = 0;
            } else if (friends != 2 && friends != 3) {
                if (friends > 3) {
                    Board[stud_row][stud_col] = 0;
                }
            } else {
                Board[stud_row][stud_col] = 1;
            }
        } else if (friends == 3) {
            Board[stud_row][stud_col] = 1;
        } else {
            Board[stud_row][stud_col] = 0;
        }

    }

    public static int GetDiagFriends(int stud_row, int stud_col, int rows, int cols, int[][] Board) {
        int cnt = 0;
        if (inbound(cols, rows, stud_row - 1, stud_col + 1)) {
            cnt += Board[stud_row - 1][stud_col + 1];
        }

        if (inbound(cols, rows, stud_row + 1, stud_col - 1)) {
            cnt += Board[stud_row + 1][stud_col - 1];
        }

        return cnt;
    }

    public static int GetHorizFriends(int stud_row, int stud_col, int rows, int cols, int[][] Board) {
        int cnt = 0;
        if (inbound(cols, rows, stud_row, stud_col + 1)) {
            cnt += Board[stud_row][stud_col + 1];
        }

        if (inbound(cols, rows, stud_row + 1, stud_col - 1)) {
            cnt += Board[stud_row][stud_col - 1];
        }

        return cnt;
    }

    public static int GetVertFriends(int stud_row, int stud_col, int rows, int cols, int[][] Board) {
        int cnt = 0;
        if (inbound(cols, rows, stud_row - 1, stud_col)) {
            cnt += Board[stud_row - 1][stud_col + 1];
        }

        if (inbound(cols, rows, stud_row - 1, stud_col)) {
            cnt += Board[stud_row - 1][stud_col];
        }

        return cnt;
    }

    public static int abs(int x) {
        return x < 0 ? -1 * x : x;
    }

    public static void PrintBoard(int rows, int cols, int[][] Board) {
        char AcademicallyWell = 9646;
        char AcademicallyNotWell = 9647;

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                if (Board[i][j] == 1) {
                    System.out.print(AcademicallyWell);
                } else {
                    System.out.print(AcademicallyNotWell);
                }
            }

            System.out.println();
        }

    }

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        int numberOfGames = scanner.nextInt();
        scanner.nextLine();

        for(int i = 1; i <= numberOfGames; ++i) {
            System.out.println("Game number " + i + " starts.");
            theStudentsGame();
            System.out.println("Game number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }

        System.out.println("All games are ended.");
    }
}

