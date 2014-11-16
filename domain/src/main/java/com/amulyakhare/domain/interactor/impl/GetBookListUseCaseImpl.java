package com.amulyakhare.domain.interactor.impl;

import com.amulyakhare.domain.interactor.GetBookListUseCase;
import com.amulyakhare.domain.model.Book;
import com.amulyakhare.domain.repository.BookRepository;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 3:35 PM
 */
public class GetBookListUseCaseImpl implements GetBookListUseCase {

    private BookRepository bookRepository;

    private EventBus eventBus;

    @Inject
    public GetBookListUseCaseImpl(BookRepository bookRepository, EventBus eventBus) {
        this.bookRepository = bookRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void execute() {
        List<Book> bookList = bookRepository.getBookList();
        GetBookListUseCaseEvent event = new GetBookListUseCaseEvent();
        event.bookList = bookList;
        eventBus.post(event);
    }

    public static class GetBookListUseCaseEvent {
        public List<Book> bookList;
    }
}
