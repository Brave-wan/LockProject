package www.jinke.com.charmhome.http;


import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import www.jinke.com.charmhome.bean.CharmHomeLoginBean;

/**
 * Created by root on 17-11-6.
 */

public class CharmHomeMethods {//http://10.15.208.86：8080/Charminghome/
    public static final String BASE_URL = "http://dev-mml.tq-service.com/Charminghome/";
    private static final int DEFAULT_TIMEOUT = 120;
    private Retrofit retrofit;
    private CharmHomeService movieService;

    private CharmHomeMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new LogInterceptor());
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        movieService = retrofit.create(CharmHomeService.class);
    }

    private static class SingletonHolder {
        private static final CharmHomeMethods INSTANCE = new CharmHomeMethods();
    }

    //获取单例
    public static CharmHomeMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public void getCharmHomeLogin(Subscriber<HttpResult<CharmHomeLoginBean>> subscriber, Map<String, String> map) {
        Observable observable = movieService.getCharmHomeLogin(map);
        toSubscribe(observable, subscriber);
    }

    public void getCharmHomeRegister(Subscriber<HttpResult<CharmHomeLoginBean>> subscriber, Map<String, String> map) {
        Observable observable = movieService.getCharmHomeRegister(map);
        toSubscribe(observable, subscriber);
    }

    public void getLockLogin(Subscriber<HttpResult<CharmHomeLoginBean>> subscriber, Map<String, String> map) {
        Observable observable = movieService.getLockLogin(map);
        toSubscribe(observable, subscriber);
    }

    public void getUpdatePsw(Subscriber<HttpResult<CharmHomeLoginBean>> subscriber, Map<String, String> map) {
        Observable observable = movieService.getUpdatePsw(map);
        toSubscribe(observable, subscriber);
    }
}
