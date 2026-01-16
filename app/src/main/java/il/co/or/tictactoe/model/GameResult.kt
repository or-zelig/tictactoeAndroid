package il.co.or.tictactoe.model

sealed class GameResult {
    object InProgress : GameResult()
    data class Win(val winner: Player) : GameResult()
    object Draw : GameResult()
}
