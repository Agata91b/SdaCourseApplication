package agata91bcomgithub.sdacourseapplication.mvp;

import android.os.Handler;

import nucleus.presenter.Presenter;

/**
 * Created by RENT on 2017-03-04.
 */
public class MvpPresenter extends Presenter<MvpActivity> {

    private LongRunningTask longRunningTask = new LongRunningTask();
    private Handler handler = new Handler();

    public void executeRunningTask() {
        new Thread() {

            @Override
            public void run() {
                final String result = longRunningTask.execute();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }


                });


            }
        }.start();
    }
}


