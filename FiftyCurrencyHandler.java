package atmSystem;

public class FiftyCurrencyHandler extends CurrencyHandler {

    public FiftyCurrencyHandler(CurrencyHandler nextCurrencyHandler) {
        super(nextCurrencyHandler);
    }

    @Override
    public void withdraw(ATMMachine atm, int amount) {
        int quantity = atm.getFiftyNotes();
        int quantityGranted = Math.min(quantity, amount/50);
        atm.deductFiftyNotes(quantityGranted);
        String withdrawnTillNow = atm.getAmountWithdrawn();
        withdrawnTillNow += "50 : " + quantityGranted + "; ";
        atm.setAmountWithdrawn(withdrawnTillNow);
        int amountRemaining = amount - (50 * quantityGranted);
        if (amountRemaining > 0) {
            super.withdraw(atm, amountRemaining);
            if (!atm.isAmountWithdrawSuccess()) {
                atm.setFiftyNotes(quantity);
            }
        }
    }
}
