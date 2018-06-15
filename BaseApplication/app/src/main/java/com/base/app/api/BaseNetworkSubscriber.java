package com.base.app.api;

import android.content.Context;
import android.util.Log;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseNetworkSubscriber<T> extends DisposableObserver<T> {

    private Context mContext;

    public BaseNetworkSubscriber(Context context) {
        mContext = context;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
       /* if (e instanceof RetrofitException) {
            final RetrofitException error = (RetrofitException) e;
            if (error.getKind() == RetrofitException.Kind.NETWORK) {
                if (error.getCause() instanceof SocketTimeoutException) {
                    onUnknownError(error);
                } else {
                    onNetworkError();
                }
            } else if (error.getKind() == RetrofitException.Kind.HTTP) {
                try {
                    final ServerError serverError = error
                            .getErrorBodyAs(ServerError.class);
                    onCustomServerError(serverError.getErrorCode(),
                            serverError.getMessage(),
                            ErrorMessage.get(serverError.getErrorCode()));
                } catch (RuntimeException | IOException re) {
                    onUnknownError(re);
                }
            } else {
                onUnknownError(e);
            }
        } else {
            onUnknownError(e);
        }*/
    }


    @Override
    public void onNext(T t) {
        Log.d(getClass().getSimpleName(), "onNext");
    }

}
