import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        Board testBoard = new Board();
        System.out.println("white disk : " + '\u25CB');
        System.out.println("black disk : " + '\u25CF');
        int turnTracker = 1;
        while(!(testBoard.getValidBlackMoves().size() == 0 && testBoard.getValidWhiteMoves().size() == 0)){
            System.out.println("valid white");
            for (String s: testBoard.getValidWhiteMoves()
            ) {
                System.out.println(s);

            }
            testBoard.printBoard();
            if(turnTracker % 2 == 1) {

                System.out.println("White turn");
                System.out.println("valid white");
                for (String s: testBoard.getValidWhiteMoves()
                ) {
                    System.out.println(s);

                }
                System.out.println("Enter the coordination : ");
                Scanner scanner1 = new Scanner(System.in);
                String choice1 = scanner1.nextLine();
                if(testBoard.getValidWhiteMoves().size() == 0){
                    System.out.println("Pass");
                    continue;
                }
                testBoard.putDisk( new Disk('w',choice1));
            }
            else {
                System.out.println("Black turn");
                System.out.println("valid black");
                System.out.println("Enter the coordination : ");
                for (String s: testBoard.getValidBlackMoves()
                ) {
                    System.out.println(s);

                }
                Scanner scanner2 = new Scanner(System.in);
                String choice2 = scanner2.nextLine();
                if(testBoard.getValidBlackMoves().size() == 0) {
                    System.out.println("Pass");
                    continue;
                }
                testBoard.putDisk( new Disk('b',choice2));
            }
            testBoard.validMoves();
            turnTracker++;
        }
        testBoard.printBoard();
        testBoard.scoreCounter();
    }
}
