public class Tile {
    private TileVal value;
    private int curHealth;
    private int originalHealth;
    public Tile(TileVal _value, int _health){
        value = _value;
        originalHealth = _health;
        curHealth = _health;
    }

    public String getValue(){
        switch(value){
            case FIRST :
                return "X";
            case SECOND :
                return "O";
            case EMPTY :
                return "#";
            default :
                return "";
        }
    }

    public void reduceHealth(){
        switch(value){
            case FIRST:
            case SECOND :
                curHealth--;
                break;
            default :
                break;
        }
        if(checkZeroHP()){
            resetValue();
        }
        return ;
    }

    public boolean checkZeroHP(){
        return curHealth == 0;
    }

    public void resetValue(){
        value = TileVal.EMPTY;
        curHealth = originalHealth;
    }
    public void changeValue(TileVal _value){
        value = _value;
        curHealth = originalHealth;
    }
}
