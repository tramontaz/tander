package DAO;

public interface FieldDAO {
    public void dropTable(String name);
    public void createTable(String name);
    public void fillTable(String tableName, String fieldName);

}
