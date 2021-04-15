package app.dao;

import javax.sql.DataSource;
import java.util.List;

public interface Dao<T> {
    void setDataSource(DataSource dataSource);

    void add(T address);

    void update(T address);

    void delete(T address);

    List<T> getAll();

    T getById(int id);

    void test();

}