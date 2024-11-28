class Horse extends ChessPiece {
    public Horse(String color, String position) {
        super(color, position);
    }

    @Override
    public boolean canMove(String newPosition, ChessPiece[][] board) {
        int[] current = getCoordinates(position);
        int[] target = getCoordinates(newPosition);

        int rowDiff = Math.abs(target[0] - current[0]);
        int colDiff = Math.abs(target[1] - current[1]);

        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public boolean canAttack(String newPosition, ChessPiece[][] board) {
        return canMove(newPosition, board);
    }
}