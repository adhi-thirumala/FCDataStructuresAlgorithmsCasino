import java.math.BigDecimal;
import java.math.RoundingMode;

public class Player {

    /**
     * String field representing name of the player.
     */
    private String name;

    private BigDecimal money;

    public Player() {
        money = BigDecimal.valueOf(1500);
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money.setScale(2, RoundingMode.HALF_UP);
    }

    public String getName() {
        return name;
    }

    public void setMoney(BigDecimal money) {
        this.money = money.setScale(2, RoundingMode.HALF_UP);;

    }

    @Override
    public String toString() {
        return  STR."name='\{name}\{'\''}, money=\{money.toString()}";
    }
}
