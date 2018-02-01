public class Runner {
    public static void main(String[] args) {
        Main main = new Main();
        main.setDbURL(args[0]);
        main.setJdbcDriver(args[1]);
        main.setDatabaseUsername(args[2]);
        main.setDatabasePassword(args[3]);
        main.setN(Integer.valueOf(args[4]));
        main.run();
    }
}
