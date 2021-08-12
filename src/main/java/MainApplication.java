public class MainApplication {

    public static void main(String[] args) {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        bankStatementAnalyzer.analyzer(args[0], bankStatementParser);
    }
}
