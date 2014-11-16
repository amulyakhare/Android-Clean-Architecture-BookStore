package com.amulyakhare.bookstore.ui.book.list;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.bookstore.R;
import com.amulyakhare.bookstore.ui.model.BookModel;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 12:50 PM
 */
@EViewGroup(R.layout.book_list_item_layout)
public class BookListItemView extends FrameLayout {

    @ViewById
    ImageView bookIcon;

    @ViewById
    TextView bookTitle;

    @ViewById
    TextView bookAuthor;

    public BookListItemView(Context context) {
        super(context);
    }

    public View renderBookItem(BookModel bookItemModel) {
        bookIcon.setImageResource(bookItemModel.getIcon());
        bookTitle.setText(bookItemModel.getTitle());
        bookAuthor.setText(bookItemModel.getAuthor());
        return this;
    }
}
