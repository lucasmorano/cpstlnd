package ccp.com.copastel.domain;

import org.apache.http.client.entity.UrlEncodedFormEntity;

/**
 * Created by lucasmorano on 2/22/15.
 */
public class Request<T> {
    private String url;
    private String payload;
    private Class<T> responseClass;
    private UrlEncodedFormEntity loginHttpEntity;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Class<T> getResponseClass() {
        return responseClass;
    }

    public void setResponseClass(Class<T> responseClass) {
        this.responseClass = responseClass;
    }

    public UrlEncodedFormEntity getLoginHttpEntity() {
        return loginHttpEntity;
    }

    public void setLoginHttpEntity(UrlEncodedFormEntity loginHttpEntity) {
        this.loginHttpEntity = loginHttpEntity;
    }
}
