class Pawn extends ChessPiece {
    public Pawn(String color, String position) {
        super(color, position);
    }

    @Override
    public boolean canMove(String newPosition, ChessPiece[][] board) {
        int[] current = getCoordinates(position);
        int[] target = getCoordinates(newPosition);

        int direction = color.equals("white") ? -1 : 1;

        // Обычный ход на одну клетку вперед
        if (target[1] == current[1] && target[0] == current[0] + direction) {
            return board[target[0]][target[1]] == null;
        }

        // Первый ход на две клетки
        if (isFirstMove && target[1] == current[1] &&
                target[0] == current[0] + 2 * direction) {
            return board[target[0]][target[1]] == null &&
                    board[current[0] + direction][current[1]] == null;
        }

        return false;
    }

    @Override
    public boolean canAttack(String newPosition, ChessPiece[][] board) {
        int[] current = getCoordinates(position);
        int[] target = getCoordinates(newPosition);

        int direction = color.equals("white") ? -1 : 1;

        return Math.abs(target[1] - current[1]) == 1 &&
                target[0] == current[0] + direction;
    }
}