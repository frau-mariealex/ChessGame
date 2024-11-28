public class ChessBoard {
    private final ChessPiece[][] board;
    public String currentPlayer;
    public boolean gameOver;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        currentPlayer = "white";
        gameOver = false;
        initializeBoard();
    }

    private void initializeBoard() {
        // Расставляем белые фигуры
        board[7][0] = new Rook("white", "a1");
        board[7][1] = new Horse("white", "b1");
        board[7][2] = new Bishop("white", "c1");
        board[7][3] = new Queen("white", "d1");
        board[7][4] = new King("white", "e1");
        board[7][5] = new Bishop("white", "f1");
        board[7][6] = new Horse("white", "g1");
        board[7][7] = new Rook("white", "h1");

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn("white", (char)('a' + i) + "2");
        }

        // Расставляем черные фигуры
        board[0][0] = new Rook("black", "a8");
        board[0][1] = new Horse("black", "b8");
        board[0][2] = new Bishop("black", "c8");
        board[0][3] = new Queen("black", "d8");
        board[0][4] = new King("black", "e8");
        board[0][5] = new Bishop("black", "f8");
        board[0][6] = new Horse("black", "g8");
        board[0][7] = new Rook("black", "h8");

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("black", (char)('a' + i) + "7");
        }
    }

    public boolean makeMove(String from, String to) {
        int[] fromCoordinates = getCoordinates(from);
        int[] toCoordinates = getCoordinates(to);

        ChessPiece piece = board[fromCoordinates[0]][fromCoordinates[1]];

        if (piece == null || !piece.getColor().equals(currentPlayer)) {
            return false;
        }

        ChessPiece targetPiece = board[toCoordinates[0]][toCoordinates[1]];

        // Проверка возможности хода
        if (targetPiece == null) {
            if (!piece.canMove(to, board)) {
                return false;
            }
        } else {
            if (!piece.canAttack(to, board) ||
                    targetPiece.getColor().equals(currentPlayer)) {
                return false;
            }
        }

        // Выполняем ход
        board[toCoordinates[0]][toCoordinates[1]] = piece;
        board[fromCoordinates[0]][fromCoordinates[1]] = null;
        piece.setPosition(to);
        piece.setFirstMove(false);

        // Проверяем рокировку
        if (piece instanceof King && Math.abs(toCoordinates[1] - fromCoordinates[1]) == 2) {
            performCastling(fromCoordinates, toCoordinates);
        }

        // Меняем текущего игрока
        currentPlayer = currentPlayer.equals("white") ? "black" : "white";

        return true;
    }

    private void performCastling(int[] fromCoordinates, int[] toCoordinates) {
        // Короткая рокировка
        if (toCoordinates[1] > fromCoordinates[1]) {
            ChessPiece rook = board[fromCoordinates[0]][7];
            board[fromCoordinates[0]][5] = rook;
            board[fromCoordinates[0]][7] = null;
            rook.setPosition((char)('a' + 5) + String.valueOf(8 - fromCoordinates[0]));
        }
        // Длинная рокировка
        else {
            ChessPiece rook = board[fromCoordinates[0]][0];
            board[fromCoordinates[0]][3] = rook;
            board[fromCoordinates[0]][0] = null;
            rook.setPosition((char)('a' + 3) + String.valueOf(8 - fromCoordinates[0]));
        }
    }

    private int[] getCoordinates(String position) {
        int col = position.charAt(0) - 'a';
        int row = 8 - (position.charAt(1) - '0');
        return new int[]{row, col};
    }

    public void printBoard() {
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("- ");
                } else {
                    char symbol = getPieceSymbol(board[i][j]);
                    System.out.print(symbol + " ");
                }
            }
            System.out.println();
        }
    }

    private char getPieceSymbol(ChessPiece piece) {
        char symbol = 'P';
        if (piece instanceof King) symbol = 'K';
        else if (piece instanceof Queen) symbol = 'Q';
        else if (piece instanceof Rook) symbol = 'R';
        else if (piece instanceof Bishop) symbol = 'B';
        else if (piece instanceof Horse) symbol = 'N';

        return piece.getColor().equals("white") ?
                Character.toUpperCase(symbol) :
                Character.toLowerCase(symbol);
    }
}