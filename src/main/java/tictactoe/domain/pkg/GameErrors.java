package tictactoe.domain.pkg;

import java.net.HttpURLConnection;

public class GameErrors {
    public static final AppError errGameFinished = new AppError("GAME_FINISHED", "Game is already finished", HttpURLConnection.HTTP_OK);
    public static final AppError errNotPlayerTurn = new AppError("NOT_PLAYER_TURN", "It's not player's turn", HttpURLConnection.HTTP_BAD_REQUEST);
    public static final AppError errNotComputerTurn = new AppError("NOT_COMPUTER_TURN", "It's not computer's turn", HttpURLConnection.HTTP_BAD_REQUEST);
    public static final AppError errInvalidMove = new AppError("INVALID_MOVE", "Invalid move coordinates", HttpURLConnection.HTTP_BAD_REQUEST);
    public static final AppError errCellOccupied = new AppError("CELL_OCCUPIED", "Cell is already occupied", HttpURLConnection.HTTP_BAD_REQUEST);
    public static final AppError errNoMovesLeft = new AppError("NO_MOVES_LEFT", "No moves left", HttpURLConnection.HTTP_BAD_REQUEST);

    // Ошибки репозитория (404)
    public static final AppError errGameNotFound = new AppError("GAME_NOT_FOUND", "Game not found", HttpURLConnection.HTTP_NOT_FOUND);

    // Внутренние ошибки (500)
    public static final AppError errInternalServer = new AppError("INTERNAL_SERVER_ERROR", "Internal server error", HttpURLConnection.HTTP_INTERNAL_ERROR);
    public static final AppError errFailedToSave = new AppError("FAILED_TO_SAVE", "Failed to save game", HttpURLConnection.HTTP_INTERNAL_ERROR);
    public static final AppError errFailedToUpdate = new AppError("FAILED_TO_UPDATE", "Failed to update game", HttpURLConnection.HTTP_INTERNAL_ERROR);


    // Ошибки валидации
    
    public static final AppError errInvalidGameID = new AppError("INVALID_GAME_ID", "Invalid game ID", HttpURLConnection.HTTP_BAD_REQUEST);
    public static final AppError errInvalidPlayerID = new AppError("INVALID_PLAYER_ID", "Invalid player ID", HttpURLConnection.HTTP_BAD_REQUEST);
    public static final AppError errInvalidMark = new AppError("INVALID_MARK", "Invalid mark, must be X or O", HttpURLConnection.HTTP_BAD_REQUEST);

}
