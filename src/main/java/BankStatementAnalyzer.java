import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    public static final String RESOURCES = "src/main/resources/";

    public void analyzer(final String fileName, final BankStatementParser bankStatementParser) {

        final Path path = Paths.get(RESOURCES + fileName);
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
                final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

                collectSummary(bankStatementProcessor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("🛑 Sorry file does not exist");
        }
    }

    private static void collectSummary(BankStatementProcessor bankStatementProcessor) {

        System.out.println("✅ The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("✅ The total for transactions in January is "
                + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("✅ The total for transactions in February is "
                + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));

        System.out
                .println("The total salary received is " + bankStatementProcessor.calculateTotalForCategory("Salary"));

        final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(bankTransaction ->
                bankTransaction.getDate().getMonth() == Month.FEBRUARY &&
                        bankTransaction.getAmount() >= 1_000
        );

        System.out.println("The transaction over 1000 and with February " + transactions);
    }


}
