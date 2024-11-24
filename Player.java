public class Player {
    PlayerVal order;
    TileVal tileVal;

    public Player(PlayerVal _order, TileVal _tileVal){
        order = _order;
        tileVal = _tileVal;
    }

    public boolean makeMove(Tile[][] grid, int i, int j){
        if(grid[i][j].getValue()!= "#"){
            return false;
        }
        grid[i][j].changeValue(tileVal);
        return true;
    }
    
}
