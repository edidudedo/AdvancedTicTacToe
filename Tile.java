public class Tile {
    private int[] coordinate;
    private TileVal value;
    public Tile(TileVal _value, int _health, int i, int j){
        value = _value;
        coordinate = new int[]{i, j};
    }

    public TileVal getValue(){
        return value;
    }

    public void resetValue(){
        value = TileVal.EMPTY;
    }

    public void changeValue(TileVal _value){
        value = _value;
    }

    public int[] getCoordinate(){
        return coordinate;
    }
}
