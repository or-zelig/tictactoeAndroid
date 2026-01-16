package il.co.or.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import il.co.or.tictactoe.model.GameResult
import il.co.or.tictactoe.model.Player
import il.co.or.tictactoe.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var statusText: TextView
    private lateinit var playAgainButton: Button
    private lateinit var cells: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        playAgainButton = findViewById(R.id.playAgainButton)

        cells = listOf(
            findViewById(R.id.cell0),
            findViewById(R.id.cell1),
            findViewById(R.id.cell2),
            findViewById(R.id.cell3),
            findViewById(R.id.cell4),
            findViewById(R.id.cell5),
            findViewById(R.id.cell6),
            findViewById(R.id.cell7),
            findViewById(R.id.cell8),
        )

        // Clicks
        cells.forEach { button ->
            button.setOnClickListener {
                val index = (it.tag as String).toIntOrNull()
                    ?: (it.tag as? Int)
                    ?: return@setOnClickListener
                viewModel.makeMove(index)
            }
        }

        playAgainButton.setOnClickListener {
            viewModel.resetGame()
        }

        // Observe
        viewModel.state.observe(this) { state ->
            render(state.board, state.currentPlayer, state.result)
        }
    }

    private fun render(board: List<Player?>, currentPlayer: Player, result: GameResult) {
        // Cells
        for (i in 0..8) {
            cells[i].text = when (board[i]) {
                Player.X -> "X"
                Player.O -> "O"
                null -> ""
            }
            cells[i].isEnabled = (board[i] == null) && (result == GameResult.InProgress)
        }

        // Status + play again
        when (result) {
            GameResult.InProgress -> {
                statusText.text = "Player ${if (currentPlayer == Player.X) "X" else "O"} turn"
                playAgainButton.visibility = Button.GONE
            }
            is GameResult.Win -> {
                statusText.text = "Player ${result.winner} wins!"
                playAgainButton.visibility = Button.VISIBLE
            }
            GameResult.Draw -> {
                statusText.text = "Draw!"
                playAgainButton.visibility = Button.VISIBLE
            }
        }
    }
}
