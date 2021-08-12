import java.time.Month;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        return bankTransactions.stream().mapToDouble(BankTransaction::getAmount).sum();
    }

    public double calculateTotalInMonth(final Month month) {
        return bankTransactions.stream().filter(bankTransaction -> bankTransaction.getDate().getMonth().equals(month))
                .mapToDouble(BankTransaction::getAmount).sum();
    }

    public double calculateTotalForCategory(final String category) {
        return bankTransactions.stream().filter(bankTransaction -> bankTransaction.description.equals(category))
                .mapToDouble(BankTransaction::getAmount).sum();
    }
}
