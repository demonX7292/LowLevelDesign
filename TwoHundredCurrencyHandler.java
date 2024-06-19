package atmSystem;

public class TwoHundredCurrencyHandler extends CurrencyHandler {

    public TwoHundredCurrencyHandler(CurrencyHandler nextCurrencyHandler) {
        super(nextCurrencyHandler);
    }

    @Override
    public void withdraw(ATMMachine atm, int amount) {
        int quantity = atm.getTwoHundredNotes();
        int quantityGranted = Math.min(quantity, amount/200);
        atm.deductTwoHundredNotes(quantityGranted);
        String withdrawnTillNow = atm.getAmountWithdrawn();
        withdrawnTillNow += "200 : " + quantityGranted + "; ";
        atm.setAmountWithdrawn(withdrawnTillNow);
        int amountRemaining = amount - (200 * quantityGranted);
        if (amountRemaining > 0) {
            super.withdraw(atm, amountRemaining);
            if (!atm.isAmountWithdrawSuccess()) {
                atm.setTwoHundredNotes(quantity);
            }
        }
    }
}
