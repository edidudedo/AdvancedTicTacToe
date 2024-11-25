public class Player {
    PlayerVal order;
    TileVal tileVal;
    char mark;

    public Player(PlayerVal _order, TileVal _tileVal){
        order = _order;
        tileVal = _tileVal;
        mark = (order == PlayerVal.FIRST) ? 'X' : 'O';
    }

    public void setMark(char newMark){
        mark = newMark;
    }

    public boolean makeMove(Tile[][] grid, int i, int j){
        if(grid[i][j].getValue()!= TileVal.EMPTY){
            return false;
        }
        grid[i][j].changeValue(tileVal);
        return true;
    }
    
}
