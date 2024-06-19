package atmSystem;

public abstract class CurrencyHandler {
    CurrencyHandler nextCurrencyHandler;

    public CurrencyHandler(CurrencyHandler nextCurrencyHandler) {
        this.nextCurrencyHandler = nextCurrencyHandler;
    }

    public void withdraw(ATMMachine atm, int amount) {
        if (this.nextCurrencyHandler != null) {
            nextCurrencyHandler.withdraw(atm, amount);
        } else {
            if (amount > 0) {
                atm.setAmountWithdrawn("Insufficient Notes in the ATM. Your request can't be fulfilled");
                atm.setAmountWithdrawSuccess(false);
            }
        }
    }
}
