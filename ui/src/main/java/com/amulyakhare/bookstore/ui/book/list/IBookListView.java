package com.amulyakhare.bookstore.ui.book.list;

import com.amulyakhare.bookstore.ui.model.BookModel;

import java.util.List;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 3:22 PM
 */
public interface IBookListView {

    public void renderBookList(List<BookModel> bookItemModelList);
}
