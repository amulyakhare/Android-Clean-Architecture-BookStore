package com.amulyakhare.bookstore.test.book;

import android.widget.ListAdapter;
import android.widget.ListView;

import com.amulyakhare.bookstore.R;
import com.amulyakhare.bookstore.ui.application.BookStoreApplication;
import com.amulyakhare.bookstore.ui.application.BookStoreModule;
import com.amulyakhare.bookstore.ui.book.list.BookListActivity_;
import com.amulyakhare.bookstore.ui.book.list.BookListPresenter;
import com.amulyakhare.bookstore.ui.model.BookModel;
import com.amulyakhare.bookstore.ui.navigation.Navigator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author amulya
 * @datetime 16 Nov 2014, 9:29 PM
 */
@RunWith(RobolectricTestRunner.class)
public class BookListActivityTest {

    private BookListActivity_ activity;

    @Mock
    private BookListPresenter bookListPresenter;

    @Mock
    private Navigator navigator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ObjectGraph graph = ObjectGraph.create(new TestModule(), new BookStoreModule(Robolectric.application));
        ((BookStoreApplication) Robolectric.application).updateGraph(graph);
        activity = Robolectric.buildActivity(BookListActivity_.class).create().get();
    }

    @Test
    public void testActivityNotNull() {
        assertNotNull(activity);
    }

    @Test
    public void testActivityComponentsNotNull() {
        assertNotNull(activity.getSupportActionBar());
        assertNotNull(activity.findViewById(R.id.book_list));
    }

    @Test
    public void testPresenterIsInitialized() {
        verify(bookListPresenter).onInit();
        verify(bookListPresenter).setView(activity);
        verify(bookListPresenter).showBookList();
        verifyNoMoreInteractions(bookListPresenter);
    }

    @Test
    public void testListViewCanRender() {
        int TEST_SIZE = 10;
        List<BookModel> modelList = mockModelList(TEST_SIZE);

        activity.renderBookList(modelList);
        ListView bookListView = (ListView) activity.findViewById(R.id.book_list);

        assertNotNull(bookListView.getAdapter());
        assertEquals(bookListView.getCount(), TEST_SIZE);
    }

    @Test
    public void testListViewCanUpdate() {
        int TEST_SIZE = 10;
        List<BookModel> modelList = mockModelList(TEST_SIZE);

        activity.renderBookList(modelList);
        ListView bookListView = (ListView) activity.findViewById(R.id.book_list);
        ListAdapter adapter = bookListView.getAdapter();

        // check if adapter and view count are correct
        assertNotNull(adapter);
        assertEquals(bookListView.getCount(), TEST_SIZE);

        int TEST_SIZE_NEW = 3;
        List<BookModel> modelListNew = mockModelList(TEST_SIZE_NEW);
        activity.renderBookList(modelListNew);

        // check if adapter instance is same, content has updated
        assertEquals(bookListView.getAdapter(), adapter);
        assertEquals(bookListView.getCount(), TEST_SIZE_NEW);
    }

    @Test
    public void testItemClickWillNavigate() {
        List<BookModel> modelList = mockModelList(10);

        activity.renderBookList(modelList);
        ListView bookListView = (ListView) activity.findViewById(R.id.book_list);

        int CLICK_POSITION = 2;
        bookListView.performItemClick(bookListView, CLICK_POSITION, CLICK_POSITION);

        // verify navigator method is called with correct params
        verify(navigator).navigateToDetail(activity, CLICK_POSITION);
    }

    private static List<BookModel> mockModelList(int size) {
        List<BookModel> modelList = new ArrayList<BookModel>();
        for (int i = 0; i < size; i++) {
            BookModel model = mock(BookModel.class);
            when(model.getId()).thenReturn(i);
            modelList.add(model);
        }
        return modelList;
    }

    @Module(
            includes = BookStoreModule.class,
            injects = {
                    BookListActivityTest.class},
            overrides = true,
            library = true
    )
    public class TestModule {

        @Provides
        BookListPresenter provideBookListPresenter() {
            return bookListPresenter;
        }

        @Provides
        Navigator provideNavigator() {
            return navigator;
        }
    }
}
