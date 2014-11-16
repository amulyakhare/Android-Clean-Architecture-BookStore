package com.amulyakhare.domain.interactor.impl;

import com.amulyakhare.domain.interactor.GetBookDetailUseCase;
import com.amulyakhare.domain.model.Book;
import com.amulyakhare.domain.repository.BookRepository;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 4:51 PM
 */
public class GetBookDetailUseCaseImpl implements GetBookDetailUseCase {

    private BookRepository bookRepository;

    private EventBus eventBus;

    @Inject
    public GetBookDetailUseCaseImpl(BookRepository bookRepository, EventBus eventBus) {
        this.bookRepository = bookRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(int id) {
        Book book = bookRepository.getBook(id);
        GetBookDetailEvent event = new GetBookDetailEvent();
        event.book = book;
        eventBus.post(event);
    }

    public static class GetBookDetailEvent {
        public Book book;
    }
}
