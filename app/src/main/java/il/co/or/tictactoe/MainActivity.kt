package il.co.or.tictactoe

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var cells: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cells = listOf(
            findViewById(R.id.cell0),
            findViewById(R.id.cell1),
            findViewById(R.id.cell2),
            findViewById(R.id.cell3),
            findViewById(R.id.cell4),
            findViewById(R.id.cell5),
            findViewById(R.id.cell6),
            findViewById(R.id.cell7),
            findViewById(R.id.cell8)
        )

        // Wire clicks: read index from android:tag and forward
        cells.forEach { button ->
            button.setOnClickListener { view ->
                val index = when (val tag = view.tag) {
                    is Int -> tag
                    is String -> tag.toIntOrNull()
                    else -> null
                } ?: return@setOnClickListener

                onCellClicked(index)
            }
        }
    }

    private fun onCellClicked(index: Int) {
        // Temporary placeholder to verify clicks work
        cells[index].text = "X"
    }
}
