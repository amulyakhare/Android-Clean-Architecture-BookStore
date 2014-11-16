package com.amulyakhare.bookstore.ui.model.mapper;

import com.amulyakhare.bookstore.R;
import com.amulyakhare.bookstore.ui.model.BookModel;
import com.amulyakhare.domain.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 3:13 PM
 */
public class BookModelMapper {

    public List<BookModel> transform(List<Book> bookList) {
       List<BookModel> modelList = new ArrayList<BookModel>();
        if (bookList != null) {
            for (Book book : bookList) {
                modelList.add(transform(book));
            }
        }
        return modelList;
    }

    public BookModel transform(Book book) {
        return new BookModel(
                book.getId(),
                R.drawable.book1,
                book.getTitle(),
                book.getAuthor(),
                book.getDetail(),
                book.getDownloadUrl()
        );
    }
}
