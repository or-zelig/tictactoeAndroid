package il.co.or.tictactoe.model

data class GameState(
    val board: List<Player?> = List(9) { null },
    val currentPlayer: Player = Player.X,
    val result: GameResult = GameResult.InProgress
)
