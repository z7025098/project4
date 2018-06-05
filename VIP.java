public class VIP extends Player {
    private String name;
    private String id;
    private double cash_back;
    private static final double CASH_BACK_RATE = 0.05;
    public VIP(int money, String id, String first, String last)
    {
        super(money);
        this.name = first +" " + last;
        this.id = id;
    }

    public void setCash_back() { cash_back = getTotalBet() * CASH_BACK_RATE; }
}
