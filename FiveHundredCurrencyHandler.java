package atmSystem;

public class FiveHundredCurrencyHandler extends CurrencyHandler {

    public FiveHundredCurrencyHandler(CurrencyHandler nextCurrencyHandler) {
        super(nextCurrencyHandler);
    }

    @Override
    public void withdraw(ATMMachine atm, int amount) {
        int quantity = atm.getFiveHundredNotes();
        int quantityGranted = Math.min(quantity, amount/500);
        atm.deductFiveHundredNotes(quantityGranted);
        String withdrawnTillNow = atm.getAmountWithdrawn();
        withdrawnTillNow += "500 : " + quantityGranted + "; ";
        atm.setAmountWithdrawn(withdrawnTillNow);
        int amountRemaining = amount - (500 * quantityGranted);
        if (amountRemaining > 0) {
            super.withdraw(atm, amountRemaining);
            if (!atm.isAmountWithdrawSuccess()) {
                atm.setFiveHundredNotes(quantity);
            }
        }
    }
}
