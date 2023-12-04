package fr.eni.papeterie.dal;

import java.util.List;


public interface DAO<T> {

    T selectById(int id) throws DALException;
    List<T> selectAll() throws DALException;
    void update(T Object) throws DALException;
    void insert(T Object) throws DALException;
    void delete(int id) throws DALException;
    
}
