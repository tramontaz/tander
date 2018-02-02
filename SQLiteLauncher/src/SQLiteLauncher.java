public class SQLiteLauncher {
    public static void main(String[] args) {
        long start = System.nanoTime();
        String dbFilename;
        int n;
        if(args.length == 2) {
            dbFilename = args[0];
            n = Integer.parseInt(args[1]);
        } else {
            dbFilename = "test_bd.sqlite";
            n = 1_000_000;
        }
        Core core = new Core();
        DBHelper dbHelper = new SQLiteDBHelper(dbFilename);
        try {
            dbHelper.connect();
            core.insertRecordsInTable(dbHelper, n);
            core.unloadingToXML(dbHelper, "1.xml");
        } finally {
            dbHelper.disconnect();
        }
        core.convertXML("1.xml", "2.xml", "converter.xsl");
        long sum = core.getAverageFromXML("2.xml");
        System.out.println("Sum = " + sum);
        long stop = System.nanoTime() - start;
        System.out.println("\n" +
                "Execution time of the program: " + stop/1_000_000_000 + " seconds.");
    }
}
