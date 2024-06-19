package atmSystem;

public class ATMMachine {
    private int fiveHundredNotes;
    private int twoHundredNotes;
    private int hundredNotes;
    private int fiftyNotes;

    CurrencyHandler currencyHandler;

    private String amountWithdrawn = "";
    private boolean amountWithdrawSuccess = false;

    public ATMMachine(int fiveHundredNotes, int twoHundredNotes, int hundredNotes,
                      int fiftyNotes, CurrencyHandler currencyHandler) {
        this.fiveHundredNotes = fiveHundredNotes;
        this.twoHundredNotes = twoHundredNotes;
        this.hundredNotes = hundredNotes;
        this.fiftyNotes = fiftyNotes;
        this.currencyHandler = currencyHandler;
    }

    public int getFiveHundredNotes() {
        return fiveHundredNotes;
    }

    public void setFiveHundredNotes(int fiveHundredNotes) {
        this.fiveHundredNotes = fiveHundredNotes;
    }

    public int getTwoHundredNotes() {
        return twoHundredNotes;
    }

    public void setTwoHundredNotes(int twoHundredNotes) {
        this.twoHundredNotes = twoHundredNotes;
    }

    public int getHundredNotes() {
        return hundredNotes;
    }

    public void setHundredNotes(int hundredNotes) {
        this.hundredNotes = hundredNotes;
    }

    public int getFiftyNotes() {
        return fiftyNotes;
    }

    public void setFiftyNotes(int fiftyNotes) {
        this.fiftyNotes = fiftyNotes;
    }

    // add
    public void addFiveHundredNotes(int quantity) {
        this.fiveHundredNotes += quantity;
    }
    public void addTwoHundredNotes(int quantity) {
        this.twoHundredNotes += quantity;
    }
    public void addHundredNotes(int quantity) {
        this.hundredNotes += quantity;
    }
    public void addFiftyNotes(int quantity) {
        this.fiftyNotes += quantity;
    }

    // deduct
    public void deductFiveHundredNotes(int quantity) {
        this.fiveHundredNotes -= quantity;
    }
    public void deductTwoHundredNotes(int quantity) {
        this.twoHundredNotes -= quantity;
    }
    public void deductHundredNotes(int quantity) {
        this.hundredNotes -= quantity;
    }
    public void deductFiftyNotes(int quantity) {
        this.fiftyNotes -= quantity;
    }

    public void setAmountWithdrawn(String s) {
        amountWithdrawn = s;
    }

    public String getAmountWithdrawn() {
        return amountWithdrawn;
    }

    public boolean isAmountWithdrawSuccess() {
        return amountWithdrawSuccess;
    }

    public void setAmountWithdrawSuccess(boolean amountWithdrawSuccess) {
        this.amountWithdrawSuccess = amountWithdrawSuccess;
    }

    public String withdrawAmount(int amount) {
        System.out.println();
        amountWithdrawn = "";
        currencyHandler.withdraw(this, amount);
        System.out.println(" | current present = 500 : " + getFiveHundredNotes() + "; 200 : "
                + getTwoHundredNotes() + "; 100 : " + getHundredNotes()
                + "; 50 : " + getFiftyNotes());
        return amountWithdrawn;
    }
}
