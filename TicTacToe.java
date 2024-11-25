import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
public class TicTacToe {
    static private int GRIDSIZE = 3;
    private Tile[][] grid;
    private Player[] players;
    private List<Deque<Tile>> playerMoves = new ArrayList<>();
    private int curPlayerIndex;
    private StringBuilder gridSB;

    public TicTacToe(int gridSize, char p1, char p2){
        resetGrid(gridSize);
        resetPlayers(p1, p2);
        resetPlayerMoves();
        resetGridSB();
    }
    private void resetGrid(int gridSize){
        changeGridSize(gridSize);
        grid = new Tile[GRIDSIZE][GRIDSIZE];
        for(int i = 0;i<GRIDSIZE;i++){
            for(int j = 0;j<GRIDSIZE;j++){
                grid[i][j] = new Tile(TileVal.EMPTY, GRIDSIZE*2, i, j);
            }
        }

    }

    private void resetPlayers(char p1, char p2){
        players = new Player[2];
        players[0] = new Player(PlayerVal.FIRST, TileVal.FIRST, p1);
        players[1] = new Player(PlayerVal.SECOND, TileVal.SECOND, p2);
        curPlayerIndex = 0;
    }

    private void resetPlayerMoves(){
        Deque<Tile> player1Moves = new ArrayDeque<>();
        Deque<Tile> player2Moves = new ArrayDeque<>();
        playerMoves.add(player1Moves);
        playerMoves.add(player2Moves);
    }

    private void resetGridSB(){
        gridSB = new StringBuilder();
        gridSB.append("  ");
        for(int i = 0;i<GRIDSIZE;i++){
            gridSB.append("" + (i+1) + " ");
        }
        gridSB.append("\n");
        for(int i = 0;i<GRIDSIZE;i++){
            gridSB.append((char)('A' + i) + " ");
            for(int j = 0;j<GRIDSIZE;j++){
                gridSB.append( "# ");
            }
            gridSB.append("\n");
        }
    }

    public void switchPlayers(){
        curPlayerIndex = 1-curPlayerIndex;
    }

    public boolean curPlayerPlay(int i, int j){
        Player curPlayer = players[curPlayerIndex];
        
        if(!curPlayer.makeMove(grid, i, j)){
            return false;
        }
        Deque<Tile> curPlayerMoves = playerMoves.get(curPlayerIndex);
        curPlayerMoves.addLast(grid[i][j]);
        changeGridSB(i, j, curPlayer.mark);
        return true;
    }

    private void changeGridSB(int i, int j, char mark){
        gridSB.setCharAt(findIndexInSB(i, j), mark);
    }

    public void changePlayerMark(int playerIndex, char playerMark){
        players[playerIndex].setMark(playerMark);
    }

    private int findIndexInSB(int i, int j){
        return (i+1)*(2*(GRIDSIZE+1)+1) + 2*(j+1);
    }

    public void debug(){
        for(int i = 0;i<GRIDSIZE;i++){
            for(int j = 0;j<GRIDSIZE;j++){
                System.out.print("" + grid[i][j].getValue() + " ");
            }
            System.out.println("");
        }
    }

    public void checkBoard(){
        Deque<Tile> curPlayerMoves = playerMoves.get(curPlayerIndex);
        if(curPlayerMoves.size() >= GRIDSIZE){
            Tile curTile = curPlayerMoves.removeFirst();
            curTile.resetValue();
            int[] curTileCoordinate = curTile.getCoordinate();
            changeGridSB(curTileCoordinate[0], curTileCoordinate[1], '#');
            
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

    public void printGridSB(){
        System.out.print(gridSB.toString());
    }

    public int getCurPlayer(){
        return curPlayerIndex+1;
    }

    public int getGridSize(){
        return GRIDSIZE;
    }

    public void changeGridSize(int newGridSize){
        GRIDSIZE = newGridSize;
    }

    public int checkWonPlayer(){
        // Check Vertically 
        for(int i = 0;i<GRIDSIZE;i++){
            TileVal winningTile = TileVal.EMPTY;
            boolean isWinnerDecided = true;
            for(int j = 0;j<GRIDSIZE;j++){
                TileVal tileValue = grid[i][j].getValue();
                
                if(tileValue == TileVal.EMPTY){
                    isWinnerDecided = false;
                    break;
                }

                if(winningTile ==  TileVal.EMPTY) {
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
                return (winningTile == TileVal.SECOND) ? 2 : 1;
            }
        }
        // horizontally
        for(int i = 0;i<GRIDSIZE;i++){
            TileVal winningTile = TileVal.EMPTY;
            boolean isWinnerDecided = true;
            for(int j = 0;j<GRIDSIZE;j++){
                TileVal tileValue = grid[j][i].getValue();
                
                if(tileValue == TileVal.EMPTY){
                    isWinnerDecided = false;
                    break;
                }

                if(winningTile ==  TileVal.EMPTY) {
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
                return (winningTile == TileVal.SECOND) ? 2 : 1;
            }
        }

        // Diagonal 1
        TileVal winningTile = TileVal.EMPTY;
        boolean isWinnerDecided = true;

        for(int i = 0;i<GRIDSIZE;i++){
            TileVal tileValue = grid[i][i].getValue();
                
            if(tileValue == TileVal.EMPTY){
                isWinnerDecided = false;
                break;
            }

            if(winningTile ==  TileVal.EMPTY) {
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
            return (winningTile == TileVal.SECOND) ? 2 : 1;
        }

        // Diagonal 2
        winningTile = TileVal.EMPTY;
        isWinnerDecided = true;

        for(int i = 0;i<GRIDSIZE;i++){
            TileVal tileValue = grid[GRIDSIZE-1-i][i].getValue();
                
            if(tileValue == TileVal.EMPTY){
                isWinnerDecided = false;
                break;
            }

            if(winningTile ==  TileVal.EMPTY) {
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
            return (winningTile == TileVal.SECOND) ? 2 : 1;
        }
        return 0;
    }
}
