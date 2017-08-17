package com.teamnexters.volleytalk.news;

import com.teamnexters.volleytalk.BasePresenter;
import com.teamnexters.volleytalk.BaseView;
import com.teamnexters.volleytalk.ResForm;

import java.util.List;

/**
 * Created by MIN on 2017. 7. 29..
 */

public interface NewsContract {
    interface View extends BaseView<Presenter> {
        void setDataOnAdapter(List<News> newsList);
    }

    interface Presenter extends BasePresenter {
        void getNewsList();
    }
}
