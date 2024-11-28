class Bishop extends ChessPiece {
    public Bishop(String color, String position) {
        super(color, position);
    }

    @Override
    public boolean canMove(String newPosition, ChessPiece[][] board) {
        int[] current = getCoordinates(position);
        int[] target = getCoordinates(newPosition);

        return Math.abs(target[0] - current[0]) == Math.abs(target[1] - current[1]) &&
                isPathClear(newPosition, board);
    }

    @Override
    public boolean canAttack(String newPosition, ChessPiece[][] board) {
        return canMove(newPosition, board);
    }
}