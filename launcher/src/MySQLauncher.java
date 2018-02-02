public class MySQLauncher {
    public static void main(String[] args) {
        String login;
        String password;
        String url;
        int n;
        if (args.length == 4) {
            login = args[0];
            password = args[1];
            url = args[2];
            n = Integer.parseInt(args[3]);
        } else {
            login = "tander";
            password = "%I@mT@nder_";
            url = "jdbc:mysql://localhost/tander?autoReconnect=true&useSSL=false";
            n = 1_000;
        }
        Core core = new Core();
        DBHelper dbHelper = new MySQLDBHelper(login, password, url);
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
    }
}
