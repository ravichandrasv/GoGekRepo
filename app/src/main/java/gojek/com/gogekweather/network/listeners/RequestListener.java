package gojek.com.gogekweather.network.listeners;

/**
 * Created by Accionlabs on 2/24/2017.
 */

public interface RequestListener<T> {

    void onStart(int apiRequestCode);

    void onFinish(int apiRequestCode);

    void onError(int apiRequestCode, String code, String message);

    void onSuccess(int apiRequestCode, T response);
}
