package com.example.a.asynctasktest;

import android.os.AsyncTask;
/**AsyncTask<>里的三个参数分别为Params传入的参数,可在后台任务中使用,Progress显示进度的单位,Result返回的结果类型*/
public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {


    @Override
    protected void onPreExecute() {
        /**任务开始前调用,常用于界面初始化等*/
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        /**主代码块,耗时任务处理,不能UI操作,可以调用publishProgress(Progress)方法更新UI'*/
        return null;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        /**调用publishProgress(Progress)方法后本方法会很快被调用,可以进行UI操作参数为Progress*/
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        /**h后台执行完毕并通过return返回后会执行此方法*/
        super.onPostExecute(aBoolean);
    }


}
