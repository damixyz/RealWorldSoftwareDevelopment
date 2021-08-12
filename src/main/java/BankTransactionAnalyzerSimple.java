import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class BankTransactionAnalyzerSimple {
    public static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) {
        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
        final String fileName = args[0];

        final Path path = Paths.get(RESOURCES + fileName);
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                final List<BankTransaction> bankTransactions =
                        bankStatementCSVParser.parseLinesFromCSV(lines);
                System.out.println("✅ The total for all transactions is " + calculateTotalAmount(bankTransactions));
                System.out.println("✅ Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("🛑 Sorry file does not exist");
        }
    }

    private static List<BankTransaction> selectInMonth(List<BankTransaction> bankTransactions, Month month) {
        return bankTransactions.stream().filter(
                bankTransaction -> bankTransaction.getDate().getMonth() == month
        ).collect(Collectors.toList());
    }

    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
        return bankTransactions.stream().mapToDouble(BankTransaction::getAmount).sum();
    }
}
