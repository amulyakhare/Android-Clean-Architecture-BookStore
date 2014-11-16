package com.amulyakhare.bookstore.ui.book.detail;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.bookstore.R;
import com.amulyakhare.bookstore.ui.application.BookStoreBaseActivity;
import com.amulyakhare.bookstore.ui.model.BookModel;
import com.amulyakhare.bookstore.ui.navigation.Navigator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

@EActivity(R.layout.activity_book_detail)
public class BookDetailActivity extends BookStoreBaseActivity implements IBookDetailView {

    @ViewById
    ImageView bookIcon;

    @ViewById
    TextView bookTitle;

    @ViewById
    TextView bookAuthor;

    @ViewById
    TextView bookDetail;

    @ViewById
    Button downloadBtn;

    @Inject
    BookDetailPresenter bookDetailPresenter;

    @Click(R.id.download_btn)
    void onDownloadClick() {
        BookModel model = (BookModel) downloadBtn.getTag();
        bookDetailPresenter.onDownloadClick(model.getDownloadUrl());
    }

    @AfterViews
    void onViewInit() {
        Intent intent = getIntent();
        int bookId = intent.getIntExtra(Navigator.BOOK_ID, 0);
        registerPresenter(bookDetailPresenter);
        bookDetailPresenter.setView(this);
        bookDetailPresenter.showDetail(bookId);
    }

    @Override
    public void renderBookDetail(BookModel model) {
        bookIcon.setImageResource(model.getIcon());
        bookTitle.setText(model.getTitle());
        bookAuthor.setText(model.getAuthor());
        bookDetail.setText(model.getDetail());
        downloadBtn.setTag(model);
    }
}
