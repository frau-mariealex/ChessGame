class Rook extends ChessPiece {
    public Rook(String color, String position) {
        super(color, position);
    }

    @Override
    public boolean canMove(String newPosition, ChessPiece[][] board) {
        int[] current = getCoordinates(position);
        int[] target = getCoordinates(newPosition);

        return (target[0] == current[0] || target[1] == current[1]) &&
                isPathClear(newPosition, board);
    }

    @Override
    public boolean canAttack(String newPosition, ChessPiece[][] board) {
        return canMove(newPosition, board);
    }
}