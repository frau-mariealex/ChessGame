import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Scanner scanner = new Scanner(System.in);

        while (!board.gameOver) {
            board.printBoard();
            System.out.println("Ход " + board.currentPlayer);
            System.out.print("Введите ход (например, e2 e4): ");

            String[] move = scanner.nextLine().split(" ");
            if (move.length != 2) {
                System.out.println("Неверный формат хода!");
                continue;
            }

            if (!board.makeMove(move[0], move[1])) {
                System.out.println("Невозможный ход!");
            }
        }

        scanner.close();
    }
}