public class VIP extends Player {
    private String firstName, lastName;
    private String id;
    private double cash_back;
    private static final double CASH_BACK_RATE = 0.05;
    public VIP(int money, String id, String firstName, String lastName)
    {
        super(money, id, firstName, lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        
    }

    public void setCash_back() { cash_back = getTotalBet() * CASH_BACK_RATE; }
}