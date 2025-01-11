package pl.pjatk.zjazd3;

import java.util.Random;
import java.util.Scanner;

public class ConsoleGame {

    private static final char START = 'A';
    private static final char STOP = 'B';
    private static final char OBSTACLE = 'X';
    private static final char EMPTY = '.';
    private static final int BOARD_SIZE = 10;

    private char[][] board;
    private int playerX;
    private int playerY;
    private int stopX;
    private int stopY;

    public static void main(String[] args) {
        ConsoleGame game = new ConsoleGame();
        game.initBoard();
        game.play();
    }

    public void initBoard() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        Random random = new Random();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }

        playerX = random.nextInt(BOARD_SIZE);
        playerY = random.nextInt(BOARD_SIZE);
        board[playerX][playerY] = START;

        do {
            stopX = random.nextInt(BOARD_SIZE);
            stopY = random.nextInt(BOARD_SIZE);
        } while (playerX == stopX && playerY == stopY);
        board[stopX][stopY] = STOP;

        int obstacleCount = (BOARD_SIZE * BOARD_SIZE) / 5;
        for (int i = 0; i < obstacleCount; i++) {
            int x, y;
            do {
                x = random.nextInt(BOARD_SIZE);
                y = random.nextInt(BOARD_SIZE);
            } while (board[x][y] != EMPTY);
            board[x][y] = OBSTACLE;
        }
    }

    public void displayBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the console game!");
        System.out.println("Your goal is to move from 'A' to 'B'.");
        System.out.println("Avoid obstacles ('X') and stay within the board.");
        System.out.println("Controls: W (up), A (left), S (down), D (right).");
        System.out.println();

        while (true) {
            displayBoard();

            System.out.print("Your move (W/A/S/D): ");
            String input = scanner.nextLine().toUpperCase();

            int nextX = playerX;
            int nextY = playerY;

            switch (input) {
                case "W": nextX--; break;
                case "S": nextX++; break;
                case "A": nextY--; break;
                case "D": nextY++; break;
                default:
                    System.out.println("Invalid input. Use W/A/S/D.");
                    continue;
            }

            if (isMoveValid(nextX, nextY, board)) {
                board[playerX][playerY] = EMPTY;
                playerX = nextX;
                playerY = nextY;

                if (playerX == stopX && playerY == stopY) {
                    System.out.println("Congratulations! You've reached 'B'!");
                    displayBoard();
                    break;
                }

                board[playerX][playerY] = START;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    public static boolean isMoveValid(int x, int y, char[][] board) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        if (board[x][y] == 'X') {
            return false;
        }
        return true;
    }
}
