package com.amulyakhare.bookstore.ui.application;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 3:58 PM
 */
public class BookStoreApplication extends Application {

    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                (Object) new BookStoreModule(this)
        );
    }

    public void updateGraph(ObjectGraph graph) {
        this.graph = graph;
    }

    public void inject(Object object) {
        graph.inject(object);
    }
}
