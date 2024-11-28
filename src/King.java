class King extends ChessPiece {
    public King(String color, String position) {
        super(color, position);
    }

    @Override
    public boolean canMove(String newPosition, ChessPiece[][] board) {
        int[] current = getCoordinates(position);
        int[] target = getCoordinates(newPosition);

        int rowDiff = Math.abs(target[0] - current[0]);
        int colDiff = Math.abs(target[1] - current[1]);

        // Обычный ход короля
        if (rowDiff <= 1 && colDiff <= 1) {
            return true;
        }

        // Рокировка
        if (isFirstMove && rowDiff == 0 && Math.abs(colDiff) == 2) {
            return canCastle(newPosition, board);
        }

        return false;
    }

    private boolean canCastle(String newPosition, ChessPiece[][] board) {
        int[] current = getCoordinates(position);
        int[] target = getCoordinates(newPosition);

        // Проверяем, что путь свободен
        if (!isPathClear(newPosition, board)) {
            return false;
        }

        // Короткая рокировка
        if (target[1] > current[1]) {
            Rook rook = (Rook) board[current[0]][7];
            return rook != null && rook.isFirstMove;
        }
        // Длинная рокировка
        else {
            Rook rook = (Rook) board[current[0]][0];
            return rook != null && rook.isFirstMove;
        }
    }

    @Override
    public boolean canAttack(String newPosition, ChessPiece[][] board) {
        int[] current = getCoordinates(position);
        int[] target = getCoordinates(newPosition);

        return Math.abs(target[0] - current[0]) <= 1 &&
                Math.abs(target[1] - current[1]) <= 1;
    }
}