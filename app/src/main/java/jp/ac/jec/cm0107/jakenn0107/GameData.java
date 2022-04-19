package jp.ac.jec.cm0107.jakenn0107;

public class GameData {
    private String monsterName; //　敵の名前
    private int monsterHP; //　敵のHP
    private String situationtxt; //　状況
    private int id; // GameDataID

    public GameData(String monsterName, int monsterHP, String situationtxt, int id) {
        this.monsterName = monsterName;
        this.monsterHP = monsterHP;
        this.situationtxt = situationtxt;
        this.id = id;
    }

    public GameData(String monsterName, int monsterHP, String situationtxt) {
        this.monsterName = monsterName;
        this.monsterHP = monsterHP;
        this.situationtxt = situationtxt;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public int getMonsterHP() {
        return monsterHP;
    }

    public void setMonsterHP(int monsterHP) {
        this.monsterHP = monsterHP;
    }

    public String getSituationtxt() {
        return situationtxt;
    }

    public void setSituationtxt(String situationtxt) {
        this.situationtxt = situationtxt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
