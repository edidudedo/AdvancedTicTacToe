// IMPROVEMENTS
/// (DONE) Change the reduce Health from O(n2) of TC to O(n) of SC 
/// (DONE) Add Setting for this attributes : 
//// GridSize
//// Player1 mark
//// Player2 mark
/// (Done) No need to print the grid when the input is wrong
/// (Done) tile.getValue should be TileVal and not String (String should only be during the print
/// (Done) Change output from O(n2) to O(1)

import java.util.Scanner;

class Main{
    public static void main(String args[]){
        // Initialization 
        /// TC = O(n2)
        /// SC = O(n2)
        TicTacToe tictactoe = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        // PrintRule
        /// TC = O(1)
        /// SC = O(1)
        printRule();

        // Setting
        /// TC = O(l + n2)
        /// SC = O(n2)
        setting(tictactoe, scanner);

        // Game
        /// TC = O(k(n+l))
        /// SC = O(n)
        int winner = game(tictactoe, scanner);

        // Ending
        /// TC = O(1)
        /// SC = O(1)
        ending(tictactoe, winner);
        scanner.close();
        return;
    }

    static private void printRule(){
        System.out.println("Welcome to the advanced CLI Tic-Tac-Toe");
        System.out.println("After you did several moves, the last move you had will be removed");
        System.out.println("The first person who makes a line horizontally, vertically, or diagonally will win");
        System.out.println("");
    }

    static private void setting(TicTacToe tictactoe, Scanner scanner){
        do{
            System.out.println("Set the Setting (Input format : GRIDSIZE Player1Mark Player2Mark) : ") ;
            String settingInput = scanner.nextLine(); 

            if(checkUserSettingInput(tictactoe, settingInput)){
                break;
            }
        }while(true);
    }

    static private boolean checkUserSettingInput(TicTacToe tictactoe, String input){
        int gridSize = 0;
        char player1Mark = 'X';
        char player2Mark = 'O';
        String[] words = input.trim().split("\\s+");
        if(words.length != 3){
            System.out.println("Wrong Number of Inputs");
            return false;
        }
        try{
            gridSize = Integer.parseInt(words[0]);
        }catch(Exception e){
            System.out.println("Grid size is not an integer");
            return false;
        }
        if(gridSize < 3 || gridSize > 5){
            System.out.println("Grid size should be 3 to 5");
            return false;
        } 
        if(words[1].length() != 1) {
            System.out.println("Player 1 mark is not a char");
            return false;
        }
        player1Mark = words[1].charAt(0);
        if(player1Mark == '#'){
            System.out.println("Player 1 mark cannot be default mark #");
            return false;
        }

        if(words[2].length() != 1) {
            System.out.println("Player 2 mark is not a char");
            return false;
        }
        player2Mark = words[2].charAt(0);
        if(player2Mark == '#'){
            System.out.println("Player 2 mark cannot be default mark #");
            return false;
        }

        tictactoe.changeGridSize(gridSize);
        tictactoe.changePlayerMark(0, player1Mark);
        tictactoe.changePlayerMark(1, player2Mark);
        return true;
    }

    static private int game(TicTacToe tictactoe, Scanner scanner){
        int winner = 0;
        do{
            tictactoe.printGridSB(); // O(1)
            int curPlayerIndex = tictactoe.getCurPlayer(); // O(1)

            // TC = O(l)
            do{
                System.out.println("Player " + curPlayerIndex + " move : ");
                String userInput = scanner.nextLine();
                int[] inputs = checkUserInput(tictactoe, userInput);
    
                if(inputs == null) {
                    System.out.println("Wrong input");
                }
                else {
                    if(!tictactoe.curPlayerPlay(inputs[0], inputs[1])){
                        System.out.println("Tile has already been filled");
                    }
                    else break;
                }            
            } while(true);

            winner = tictactoe.checkWonPlayer(); // O(n)
            if(winner==0){
                tictactoe.switchPlayers(); // O(1)
                tictactoe.checkBoard(); // O(1)
            }
        }while(winner == 0);
        return winner;
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
    
    static void ending(TicTacToe tictactoe, int winner){
        tictactoe.printGridSB();
        System.out.println("Congratulations player " + winner + "!");
    }
}