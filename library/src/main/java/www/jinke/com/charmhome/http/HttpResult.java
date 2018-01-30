package www.jinke.com.charmhome.http;

/**
 * Created by liukun on 16/3/5.
 */
public class HttpResult<T> {


    /**
     * errcode : 1
     * errmsg : 成功
     */

    private String errcode;
    private String errmsg;
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
