package com.amulyakhare.domain.interactor.impl;

import com.amulyakhare.domain.interactor.DownloadBookUseCase;

import de.greenrobot.event.EventBus;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 5:00 PM
 */
public class DownloadBookUseCaseImpl implements DownloadBookUseCase {

    private static final int STATUS_IN_PROGRESS = 0;
    private static final int STATUS_SUCCESS = 1;
    private static final int STATUS_FAILED = 2;
    private EventBus eventBus;

    public DownloadBookUseCaseImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void execute(String url) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DownloadBookEvent event = new DownloadBookEvent();
                    event.status = STATUS_IN_PROGRESS;
                    event.progress = (int) (((i + 1) / 10.0f) * 100);
                    eventBus.post(event);
                }
                DownloadBookEvent event = new DownloadBookEvent();
                event.status = STATUS_SUCCESS;
                event.progress = 100;
                eventBus.post(event);
            }
        });
        thread.start();
    }
    public static class DownloadBookEvent {
        public int progress;
        private int status;

        public boolean inProgress() {
            return (status == STATUS_IN_PROGRESS);
        }

        public boolean isSuccess() {
            return (status == STATUS_SUCCESS);
        }

        public boolean isFailed() {
            return (status == STATUS_FAILED);
        }
    }
}
