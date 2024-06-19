package atmSystem;

public class HundredCurrencyHandler extends CurrencyHandler {

    public HundredCurrencyHandler(CurrencyHandler nextCurrencyHandler) {
        super(nextCurrencyHandler);
    }

    @Override
    public void withdraw(ATMMachine atm, int amount) {
        int quantity = atm.getHundredNotes();
        int quantityGranted = Math.min(quantity, amount/100);
        atm.deductHundredNotes(quantityGranted);
        String withdrawnTillNow = atm.getAmountWithdrawn();
        withdrawnTillNow += "100 : " + quantityGranted + "; ";
        atm.setAmountWithdrawn(withdrawnTillNow);
        int amountRemaining = amount - (100 * quantityGranted);
        if (amountRemaining > 0) {
            super.withdraw(atm, amountRemaining);
            if (!atm.isAmountWithdrawSuccess()) {
                atm.setHundredNotes(quantity);
            }
        }
    }
}
