package com.amulyakhare.bookstore.ui.application;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amulya
 * @datetime 13 Nov 2014, 5:08 PM
 */
public class BookStoreBaseActivity extends ActionBarActivity {

    private List<IPresenter> presenterList = new ArrayList<IPresenter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BookStoreApplication) getApplication()).inject(this);
    }

    public void registerPresenter(IPresenter presenter) {
        presenter.onInit();
        presenterList.add(presenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (IPresenter presenter : presenterList) {
            presenter.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (IPresenter presenter : presenterList) {
            presenter.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (IPresenter presenter : presenterList) {
            presenter.onDestroy();
        }
        presenterList.clear();
    }
}
