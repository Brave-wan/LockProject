package www.jinke.com.charmhome.http;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import www.jinke.com.charmhome.bean.CharmHomeLoginBean;

/**
 * Created by root on 17-11-6.
 */

public interface CharmHomeService {
    @FormUrlEncoded
    @POST("basicsAppController/login")
    Observable<HttpResult<CharmHomeLoginBean>> getCharmHomeLogin(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("basicsAppController/register")
    Observable<HttpResult<CharmHomeLoginBean>> getCharmHomeRegister(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("dsmSdkController/login")
    Observable<HttpResult<CharmHomeLoginBean>> getLockLogin(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("dsmSdkController/updatePsw")
    Observable<HttpResult<CharmHomeLoginBean>> getUpdatePsw(@FieldMap Map<String, String> map);
}
