import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    public static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) {
        final Path path = Paths.get(RESOURCES + args[0]);
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                double total = 0d;
                final DateTimeFormatter DATE_PATTERN =
                        DateTimeFormatter.ofPattern("dd-MM-yyyy");
                for (final String line :
                        lines) {
                    final String[] columns =
                            line.split(",");
                    final LocalDate data = LocalDate.parse(columns[0], DATE_PATTERN);
                    if (data.getMonth() == Month.JANUARY) {
                        final double amount = Double.parseDouble(columns[1]);
                        total += amount;
                    }
                }
                System.out.println("✅ The total for all transactions in January is " + total);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("🛑 Sorry file does not exist");
        }
    }
}
