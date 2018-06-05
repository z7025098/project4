public class SuperVIP extends VIP {
    private int bonus;
    private String name;
    public SuperVIP(int money, String id, String first, String last)
    {
        super(money, id, first, last);
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