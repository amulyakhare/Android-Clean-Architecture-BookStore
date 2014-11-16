package com.amulyakhare.domain.repository;

import com.amulyakhare.domain.model.Book;

import java.util.List;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 3:46 PM
 */
public interface BookRepository {

    public List<Book> getBookList();

    public Book getBook(int id);
}
