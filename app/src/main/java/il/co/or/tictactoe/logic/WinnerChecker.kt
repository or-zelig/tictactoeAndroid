package il.co.or.tictactoe.logic

import il.co.or.tictactoe.model.GameResult
import il.co.or.tictactoe.model.Player

object WinnerChecker {

    private val winningLines = listOf(
        listOf(0,1,2), listOf(3,4,5), listOf(6,7,8), // rows
        listOf(0,3,6), listOf(1,4,7), listOf(2,5,8), // cols
        listOf(0,4,8), listOf(2,4,6)                 // diagonals
    )

    fun calculateResult(board: List<Player?>): GameResult {
        for (line in winningLines) {
            val a = board[line[0]]
            val b = board[line[1]]
            val c = board[line[2]]

            if (a != null && a == b && b == c) {
                return GameResult.Win(a)
            }
        }

        val isFull = board.all { it != null }
        return if (isFull) GameResult.Draw else GameResult.InProgress
    }
}
