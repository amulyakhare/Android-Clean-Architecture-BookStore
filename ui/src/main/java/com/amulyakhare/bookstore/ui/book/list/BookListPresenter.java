package com.amulyakhare.bookstore.ui.book.list;

import com.amulyakhare.bookstore.ui.application.IPresenter;
import com.amulyakhare.bookstore.ui.model.BookModel;
import com.amulyakhare.bookstore.ui.model.mapper.BookModelMapper;
import com.amulyakhare.domain.interactor.GetBookListUseCase;
import com.amulyakhare.domain.interactor.impl.GetBookListUseCaseImpl;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 3:15 PM
 */
public class BookListPresenter implements IPresenter {

    private GetBookListUseCase getBookListUseCase;

    private EventBus eventBus;

    private BookModelMapper bookModelMapper;

    private IBookListView bookListView;

    @Override
    public void onInit() {
        eventBus.register(this);
    }

    @Inject
    public BookListPresenter(GetBookListUseCase getBookListUseCase,
                             EventBus eventBus,
                             BookModelMapper bookModelMapper) {
        this.getBookListUseCase = getBookListUseCase;
        this.eventBus = eventBus;
        this.bookModelMapper = bookModelMapper;
    }

    public void setView(IBookListView bookListView) {
        this.bookListView = bookListView;
    }

    @Override
    public void onResume() {

    }

    public void showBookList() {
        getBookListUseCase.execute();
    }

    public void onEvent(GetBookListUseCaseImpl.GetBookListUseCaseEvent event) {
        List<BookModel> modelList = bookModelMapper.transform(event.bookList);
        bookListView.renderBookList(modelList);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }
}
