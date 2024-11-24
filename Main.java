import java.util.Scanner;

class Main{
    public static void main(String args[]){
        TicTacToe tictactoe = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        printRule();
        int winner = 0;
        do{
            tictactoe.printGrid();
            int curPlayerIndex = tictactoe.getCurPlayer();
            System.out.println("Player " + curPlayerIndex + " move : ");
            String userInput = scanner.nextLine();
            int[] inputs = checkUserInput(tictactoe, userInput);

            if(inputs == null) {
                System.out.println("Wrong input");
                continue;
            }

            if(!tictactoe.curPlayerPlay(inputs[0], inputs[1])){
                System.out.println("Tile has already been filled");
                continue;
            }

            tictactoe.switchPlayers();
            winner = tictactoe.checkWonPlayer();
            if(winner==0){
                tictactoe.reduceHealth();
            }
        }while(winner == 0);

        tictactoe.printGrid();
        System.out.println("Congratulations player " + winner + "!");
        scanner.close();
        return;
    }

    static private void printRule(){
        System.out.println("Welcome to the advanced CLI Tic-Tac-Toe");
        System.out.println("After you did several moves, the last move you had will be removed");
        System.out.println("The first person who makes a line horizontally, vertically, or diagonally will win");
        System.out.println("");
    }

    static private int[] checkUserInput(TicTacToe tictactoe, String input){
        String[] words = input.trim().split("\\s+");
        if(words.length != 2){
            return null;
        }
        if(words[0].length() != 1 || words[1].length() != 1){
            return null;
        }
        int[] ret = new int[2];
        ret[0] = words[0].charAt(0) - 'A';
        try{
            ret[1] = Integer.parseInt(words[1])-1;
        }catch(Exception e){
            return null;
        }
        
        if(ret[0] < 0 || ret[0] >=  tictactoe.getGridSize() || ret[1] < 0 || ret[1] >= tictactoe.getGridSize()){
            return null;
        }

        return ret;

    }
}