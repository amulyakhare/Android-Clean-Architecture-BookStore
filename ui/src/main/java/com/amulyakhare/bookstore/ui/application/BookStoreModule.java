package com.amulyakhare.bookstore.ui.application;

import android.content.Context;

import com.amulyakhare.bookstore.ui.book.detail.BookDetailActivity_;
import com.amulyakhare.bookstore.ui.book.detail.BookDetailPresenter;
import com.amulyakhare.bookstore.ui.book.list.BookListActivity_;
import com.amulyakhare.bookstore.ui.book.list.BookListPresenter;
import com.amulyakhare.bookstore.ui.model.mapper.BookModelMapper;
import com.amulyakhare.bookstore.ui.navigation.Navigator;
import com.amulyakhare.domain.interactor.*;
import com.amulyakhare.domain.interactor.impl.DownloadBookUseCaseImpl;
import com.amulyakhare.domain.interactor.impl.GetBookDetailUseCaseImpl;
import com.amulyakhare.domain.interactor.impl.GetBookListUseCaseImpl;
import com.amulyakhare.domain.repository.BookRepository;
import com.amulyakhare.framework.data.BookDataRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 3:57 PM
 */
@Module(
        injects = {BookListActivity_.class, BookDetailActivity_.class},
        complete = true
)
public class BookStoreModule {

    private Context applicationContext;

    public BookStoreModule(Context context) {
        this.applicationContext = context;
    }

    /*@Provides
    @Singleton
    Context provideContext() {
        return applicationContext;
    }
*/
    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new EventBus();
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    BookRepository provideBookRepository() {
        return new BookDataRepository();
    }

    @Provides
    GetBookListUseCase provideGetBookListUseCase(BookRepository repository, EventBus eventBus) {
        return new GetBookListUseCaseImpl(repository, eventBus);
    }

    @Provides
    BookModelMapper provideBookModelMapper() {
        return new BookModelMapper();
    }

    @Provides
    BookListPresenter provideBookListPresenter(GetBookListUseCase getBookListUseCase,
                                               EventBus eventBus,
                                               BookModelMapper bookModelMapper) {
        return new BookListPresenter(getBookListUseCase, eventBus, bookModelMapper);
    }

    @Provides
    GetBookDetailUseCase provideGetBookDetailUseCase(BookRepository bookRepository, EventBus eventBus) {
        return new GetBookDetailUseCaseImpl(bookRepository, eventBus);
    }

    @Provides
    DownloadBookUseCase provideDownloadBookUseCase(EventBus eventBus) {
        return new DownloadBookUseCaseImpl(eventBus);
    }

    @Provides
    BookDetailPresenter provideBookDetailPresenter(EventBus eventBus,
                                                   BookModelMapper bookModelMapper,
                                                   GetBookDetailUseCase getBookDetailUseCase,
                                                   DownloadBookUseCase downloadBookUseCase) {
        return new BookDetailPresenter(eventBus, bookModelMapper, getBookDetailUseCase, downloadBookUseCase);
    }
}
