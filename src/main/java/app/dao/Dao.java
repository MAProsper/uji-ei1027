package app.dao;

import java.util.List;

public interface Dao<T> {
    void add(T address);

    void update(T address);

    void delete(T address);

    List<T> getAll();

    T getById(int id);

    void test();
}