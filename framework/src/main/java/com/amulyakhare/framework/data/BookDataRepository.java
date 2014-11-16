package com.amulyakhare.framework.data;

import com.amulyakhare.domain.model.Book;
import com.amulyakhare.domain.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 3:53 PM
 */
public class BookDataRepository implements BookRepository {

    @Override
    public List<Book> getBookList() {
        List<Book> bookList = new ArrayList<Book>();
        for (int i = 0; i < 3; i++) {
            bookList.add(new Book());
        }
        return bookList;
    }

    @Override
    public Book getBook(int id) {
        return new Book();
    }
}
