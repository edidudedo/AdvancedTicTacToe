public class TicTacToe {
    static private int GRIDSIZE = 4;
    private Tile[][] grid;
    private Player[] players;
    private int curPlayerIndex;

    public TicTacToe(){
        resetGrid();
        resetPlayers();
        
    }
    private void resetGrid(){
        grid = new Tile[GRIDSIZE][GRIDSIZE];
        for(int i = 0;i<GRIDSIZE;i++){
            for(int j = 0;j<GRIDSIZE;j++){
                grid[i][j] = new Tile(TileVal.EMPTY, GRIDSIZE*2);
            }
        }

    }

    private void resetPlayers(){
        players = new Player[2];
        players[0] = new Player(PlayerVal.FIRST, TileVal.FIRST);
        players[1] = new Player(PlayerVal.SECOND, TileVal.SECOND);
        curPlayerIndex = 0;
    }

    public void switchPlayers(){
        curPlayerIndex = 1-curPlayerIndex;
    }

    public boolean curPlayerPlay(int i, int j){
        Player curPlayer = players[curPlayerIndex];
        return curPlayer.makeMove(grid, i, j);
    }

    public void debug(){
        for(int i = 0;i<GRIDSIZE;i++){
            for(int j = 0;j<GRIDSIZE;j++){
                System.out.print("" + grid[i][j].getValue() + " ");
            }
            System.out.println("");
        }
    }

    public void reduceHealth(){
        for(int i = 0;i< GRIDSIZE;i++){
            for(int j = 0; j<GRIDSIZE;j++){
                grid[i][j].reduceHealth();
            }
        }
    }

    public void printGrid(){
        System.out.print("  ");
        for(int i = 0;i<GRIDSIZE;i++){
            System.out.print("" + (i+1) + " ");
        }
        System.out.println("");
        for(int i = 0;i<GRIDSIZE;i++){
            System.out.print((char)('A' + i) + " ");
            for(int j = 0;j<GRIDSIZE;j++){
                System.out.print("" + grid[i][j].getValue() + " ");
            }
            System.out.println("");
        }
    }

    public int getCurPlayer(){
        return curPlayerIndex+1;
    }

    public int getGridSize(){
        return GRIDSIZE;
    }

    public int checkWonPlayer(){
        // Check Vertically 
        for(int i = 0;i<GRIDSIZE;i++){
            String winningTile = "";
            boolean isWinnerDecided = true;
            for(int j = 0;j<GRIDSIZE;j++){
                String tileValue = grid[i][j].getValue();
                
                if(tileValue == "#"){
                    isWinnerDecided = false;
                    break;
                }

                if(winningTile ==  "") {
                    winningTile = tileValue;
                }
                else{
                    if(winningTile != tileValue){
                        isWinnerDecided = false;
                        break;
                    }
                }

            }
            if (isWinnerDecided){
                return (winningTile == "O") ? 2 : 1;
            }
        }
        // horizontally
        for(int i = 0;i<GRIDSIZE;i++){
            String winningTile = "";
            boolean isWinnerDecided = true;
            for(int j = 0;j<GRIDSIZE;j++){
                String tileValue = grid[j][i].getValue();
                
                if(tileValue == "#"){
                    isWinnerDecided = false;
                    break;
                }

                if(winningTile ==  "") {
                    winningTile = tileValue;
                }
                else{
                    if(winningTile != tileValue){
                        isWinnerDecided = false;
                        break;
                    }
                }

            }
            if (isWinnerDecided){
                return (winningTile == "O") ? 2 : 1;
            }
        }

        // Diagonal 1

        String winningTile = "";
        boolean isWinnerDecided = true;

        for(int i = 0;i<GRIDSIZE;i++){
            String tileValue = grid[i][i].getValue();
                
            if(tileValue == "#"){
                isWinnerDecided = false;
                break;
            }

            if(winningTile ==  "") {
                winningTile = tileValue;
            }
            else{
                if(winningTile != tileValue){
                    isWinnerDecided = false;
                    break;
                }
            }
        }

        if (isWinnerDecided){
            return (winningTile == "O") ? 2 : 1;
        }

        // Diagonal 1

        winningTile = "";
        isWinnerDecided = true;

        for(int i = 0;i<GRIDSIZE;i++){
            String tileValue = grid[GRIDSIZE-1-i][i].getValue();
                
            if(tileValue == "#"){
                isWinnerDecided = false;
                break;
            }

            if(winningTile ==  "") {
                winningTile = tileValue;
            }
            else{
                if(winningTile != tileValue){
                    isWinnerDecided = false;
                    break;
                }
            }
        }

        if (isWinnerDecided){
            return (winningTile == "O") ? 2 : 1;
        }

        // diagonally 
        return 0;
    }

    
}
