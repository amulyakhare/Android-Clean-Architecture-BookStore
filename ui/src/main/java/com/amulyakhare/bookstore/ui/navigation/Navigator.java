package com.amulyakhare.bookstore.ui.navigation;

import android.content.Context;
import android.content.Intent;

import com.amulyakhare.bookstore.ui.book.detail.BookDetailActivity_;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 5:22 PM
 */
public class Navigator {

    public static final String BOOK_ID = "BOOK_ID";

    public void navigateToDetail(Context context, int bookId) {
        Intent intent = new Intent(context, BookDetailActivity_.class);
        intent.putExtra(BOOK_ID, bookId);
        context.startActivity(intent);
    }
}
