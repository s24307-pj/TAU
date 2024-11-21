package zjazd3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleGameTest {

    @Test
    void testValidMoveOnEmptyField() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', 'A', '.'},
                {'.', '.', 'B'}
        };
        assertTrue(ConsoleGame.isMoveValid(1, 2, board));
    }

    @Test
    void testInvalidMoveIntoObstacle() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', 'A', 'X'},
                {'.', '.', 'B'}
        };
        assertFalse(ConsoleGame.isMoveValid(1, 2, board));
    }

    @Test
    void testInvalidMoveOutOfBounds() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', 'A', '.'},
                {'.', '.', 'B'}
        };

        assertFalse(ConsoleGame.isMoveValid(-1, 0, board));
        assertFalse(ConsoleGame.isMoveValid(3, 0, board));
        assertFalse(ConsoleGame.isMoveValid(0, -1, board));
        assertFalse(ConsoleGame.isMoveValid(0, 3, board));
    }

    @Test
    void testValidMoveToStop() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', 'A', '.'},
                {'.', '.', 'B'}
        };
        assertTrue(ConsoleGame.isMoveValid(2, 2, board));
    }

    @Test
    void testStayOnStart() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', 'A', '.'},
                {'.', '.', 'B'}
        };
        assertTrue(ConsoleGame.isMoveValid(1, 1, board));
    }

    @Test
    void testWinningMove() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', 'A', '.'},
                {'.', '.', 'B'}
        };
        assertTrue(ConsoleGame.isMoveValid(2, 2, board));
    }

    @Test
    void testMoveAroundObstacle() {
        char[][] board = {
                {'.', 'X', '.'},
                {'.', 'A', '.'},
                {'.', '.', 'B'}
        };

        assertFalse(ConsoleGame.isMoveValid(0, 1, board));
        assertTrue(ConsoleGame.isMoveValid(1, 2, board));
        assertTrue(ConsoleGame.isMoveValid(2, 1, board));
    }

    @Test
    void testNoObstaclesBoard() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'}
        };

        assertTrue(ConsoleGame.isMoveValid(0, 0, board));
        assertTrue(ConsoleGame.isMoveValid(1, 1, board));
        assertTrue(ConsoleGame.isMoveValid(2, 2, board));
    }

    @Test
    void testFullyBlockedBoard() {
        char[][] board = {
                {'X', 'X', 'X'},
                {'A', 'X', 'X'},
                {'X', 'X', 'B'}
        };

        assertTrue(ConsoleGame.isMoveValid(1, 0, board));
        assertTrue(ConsoleGame.isMoveValid(2, 2, board));
        assertFalse(ConsoleGame.isMoveValid(1, 1, board));
        assertFalse(ConsoleGame.isMoveValid(0, 0, board));
    }

    @Test
    void testBoardWithDifferentCharacters() {
        char[][] board = {
                {'.', 'X', '.'},
                {'A', 'C', 'D'},
                {'.', '.', 'B'}
        };

        assertTrue(ConsoleGame.isMoveValid(1, 1, board));
        assertTrue(ConsoleGame.isMoveValid(1, 2, board));
        assertFalse(ConsoleGame.isMoveValid(0, 1, board));
    }

}

