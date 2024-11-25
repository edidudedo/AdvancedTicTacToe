public class Player {
    PlayerVal order;
    TileVal tileVal;
    char mark;

    public Player(PlayerVal _playerVal, TileVal _tileVal, char _mark){
        order = _playerVal;
        tileVal = _tileVal;
        mark = _mark;
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
