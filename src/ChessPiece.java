public abstract class ChessPiece {
    protected String color;
    protected String position;
    protected boolean isFirstMove = true;

    public ChessPiece(String color, String position) {
        this.color = color;
        this.position = position;
    }

    // Геттеры
    public String getColor() {
        return color;
    }

    public String getPosition() {
        return position;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    // Сеттеры
    public void setPosition(String position) {
        this.position = position;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    // Абстрактные методы
    public abstract boolean canMove(String newPosition, ChessPiece[][] board);
    public abstract boolean canAttack(String newPosition, ChessPiece[][] board);

    // Вспомогательные методы
    protected int[] getCoordinates(String position) {
        int col = position.charAt(0) - 'a';
        int row = 8 - (position.charAt(1) - '0');
        return new int[]{row, col};
    }

    protected boolean isPathClear(String newPosition, ChessPiece[][] board) {
        int[] current = getCoordinates(position);
        int[] target = getCoordinates(newPosition);

        int rowStep = Integer.compare(target[0] - current[0], 0);
        int colStep = Integer.compare(target[1] - current[1], 0);

        int row = current[0] + rowStep;
        int col = current[1] + colStep;

        while (row != target[0] || col != target[1]) {
            if (board[row][col] != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }

        return true;
    }
}