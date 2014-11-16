package com.amulyakhare.bookstore.ui.book.list;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.amulyakhare.bookstore.R;
import com.amulyakhare.bookstore.ui.application.BookStoreBaseActivity;
import com.amulyakhare.bookstore.ui.model.BookModel;
import com.amulyakhare.bookstore.ui.navigation.Navigator;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

@EActivity(R.layout.activity_home)
public class BookListActivity extends BookStoreBaseActivity implements IBookListView {

    @ViewById
    ListView bookList;

    @Inject
    BookListPresenter bookListPresenter;

    @Inject
    Navigator navigator;

    BookListAdapter bookListAdapter;

    @AfterViews
    void onViewInit() {
        registerPresenter(bookListPresenter);
        bookListPresenter.setView(this);
        bookListPresenter.showBookList();
    }

    @ItemClick(R.id.book_list)
    void onBookItemClick(BookModel bookItemModel) {
        navigator.navigateToDetail(this, bookItemModel.getId());
    }

    @Override
    public void renderBookList(List<BookModel> bookItemModelList) {
        if (bookListAdapter == null) {
            bookListAdapter = new BookListAdapter();
            bookListAdapter.setData(bookItemModelList);
            bookList.setAdapter(bookListAdapter);
        } else {
            bookListAdapter.setData(bookItemModelList);
            bookListAdapter.notifyDataSetChanged();
        }
    }

    private static class BookListAdapter extends BaseAdapter {

        private List<BookModel> itemList;

        public void setData(List<BookModel> itemList) {
            this.itemList = itemList;
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public BookModel getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            BookListItemView itemView = (BookListItemView) view;
            if (itemView == null) {
                itemView = BookListItemView_.build(parent.getContext());
            }
            return itemView.renderBookItem(getItem(position));
        }
    }
}
