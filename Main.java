
public class Main {
    public static void main(String[] args) {
        ATMMachine atmMachine = new ATMMachine(10, 10, 10, 10,
                new FiveHundredCurrencyHandler(new TwoHundredCurrencyHandler(
                        new HundredCurrencyHandler(new FiftyCurrencyHandler(null)))));
        System.out.println(atmMachine.withdrawAmount(10850));
        System.out.println(atmMachine.withdrawAmount(7750));
        System.out.println(atmMachine.withdrawAmount(1050));
    }
}
