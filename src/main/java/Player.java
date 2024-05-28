public class Player {

    /**
     * String field representing name of the player.
     */
    private String name;

    private int money;

    public Player() {
        money = 1500;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return  STR."name='\{name}\{'\''}, money=\{money}";
    }
}
