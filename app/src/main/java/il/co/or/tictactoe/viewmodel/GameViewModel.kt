package il.co.or.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import il.co.or.tictactoe.logic.WinnerChecker
import il.co.or.tictactoe.model.GameResult
import il.co.or.tictactoe.model.GameState
import il.co.or.tictactoe.model.Player

class GameViewModel : ViewModel() {

    private val _state = MutableLiveData(GameState())
    val state: LiveData<GameState> = _state

    fun makeMove(index: Int) {
        val current = _state.value ?: GameState()

        // If game ended -> ignore moves
        if (current.result != GameResult.InProgress) return

        // Out of range
        if (index !in 0..8) return

        // Cell already taken
        if (current.board[index] != null) return

        val newBoard = current.board.toMutableList()
        newBoard[index] = current.currentPlayer

        val newResult = WinnerChecker.calculateResult(newBoard)

        val nextPlayer = if (current.currentPlayer == Player.X) Player.O else Player.X

        _state.value = current.copy(
            board = newBoard,
            currentPlayer = if (newResult == GameResult.InProgress) nextPlayer else current.currentPlayer,
            result = newResult
        )
    }

    fun resetGame() {
        _state.value = GameState()
    }
}
