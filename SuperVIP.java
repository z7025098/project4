public class SuperVIP extends VIP {
    private int bonus;
    public SuperVIP(int money, String id, String name)
    {
        super(money, id, name);
        bonus = 0;
    }

    public void setBonus()
    {
        if (getBetTimes() > 20)
            bonus = 50;
        else if (getBetTimes() > 10)
            bonus = 25;
        else if (getBetTimes() > 4)
            bonus = 10;
        else
            bonus = 0;
    }
}