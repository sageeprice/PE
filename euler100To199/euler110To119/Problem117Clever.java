package euler100To199.euler110To119;

/**
 * Created by Sage on 9/13/2015.
 */
public class Problem117Clever {

    private static int BOARD_SIZE = 50;

    public static void main(String[] args) {
        long[] board = new long[BOARD_SIZE+1];
        board[0]=1;
        board[1]=1;
        board[2]=2;
        board[3]=4;
        for (int i = 4; i <=BOARD_SIZE; i++) {
            board[i]= board[i-1] + board[i-2] + board[i-3] + board[i-4];
        }
        System.out.println(board[50]);
    }
}
