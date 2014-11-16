package com.amulyakhare.bookstore.ui.book.detail;

import com.amulyakhare.bookstore.ui.application.IPresenter;
import com.amulyakhare.bookstore.ui.model.BookModel;
import com.amulyakhare.bookstore.ui.model.mapper.BookModelMapper;
import com.amulyakhare.domain.interactor.DownloadBookUseCase;
import com.amulyakhare.domain.interactor.impl.DownloadBookUseCaseImpl;
import com.amulyakhare.domain.interactor.GetBookDetailUseCase;
import com.amulyakhare.domain.interactor.impl.GetBookDetailUseCaseImpl;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 4:46 PM
 */
public class BookDetailPresenter implements IPresenter {

    private IBookDetailView bookDetailView;

    private EventBus eventBus;

    private BookModelMapper bookModelMapper;

    private GetBookDetailUseCase getBookDetailUseCase;

    private DownloadBookUseCase downloadBookUseCase;

    @Inject
    public BookDetailPresenter(EventBus eventBus,
                               BookModelMapper bookModelMapper,
                               GetBookDetailUseCase getBookDetailUseCase,
                               DownloadBookUseCase downloadBookUseCase) {
        this.eventBus = eventBus;
        this.bookModelMapper = bookModelMapper;
        this.getBookDetailUseCase = getBookDetailUseCase;
        this.downloadBookUseCase = downloadBookUseCase;
    }

    @Override
    public void onInit() {
        eventBus.register(this);
    }

    @Override
    public void onResume() {

    }

    public void setView(IBookDetailView bookDetailView) {
        this.bookDetailView = bookDetailView;
    }

    public void showDetail(int bookId) {
        getBookDetailUseCase.execute(bookId);
    }

    public void onDownloadClick(String url) {
        downloadBookUseCase.execute(url);
    }

    public void onEvent(GetBookDetailUseCaseImpl.GetBookDetailEvent event) {
        BookModel model = bookModelMapper.transform(event.book);
        bookDetailView.renderBookDetail(model);
    }

    public void onEventMainThread(DownloadBookUseCaseImpl.DownloadBookEvent event) {
        if (event.inProgress()) {

        }
        else if (event.isSuccess()) {

        }
        else if (event.isFailed()) {

        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }
}
