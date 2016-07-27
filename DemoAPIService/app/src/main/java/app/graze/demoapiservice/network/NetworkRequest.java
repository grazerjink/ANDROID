package app.graze.demoapiservice.network;

import android.content.Context;
import android.util.Log;

import app.graze.demoapiservice.model.response.BaseResponse;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by graze on 27/07/2016.
 */
public class NetworkRequest {
    //Cấu hình FLOW hoạt động
    public static <T>Subscription performAsyncRequest(final Context context,
                                                      Observable<T> observable,
                                                      Func1<? super T, BaseResponse> doInBackground,
                                                      Action1<BaseResponse> onNext) {
        // Chỉ định một lịch trình (Scheduler.new Thread (), Scheduler.immediate (), ...)
        // Chúng tôi chọn
        // Scheduler.io () để thực hiện yêu cầu mạng trong một pool thread
        return observable.subscribeOn(Schedulers.io())
            // Observe result in the main thread to be able to update UI
            .observeOn(AndroidSchedulers.mainThread())
            // Set callback action
            .map(doInBackground)
                //Nếu lỗi ta sẽ nhận xử lí lỗi
                //Xong xuôi sẽ thả về onNext
            .onErrorReturn(throwable -> {
                BaseResponse baseResponse = new BaseResponse();
                Log.i("ErrorNetWork", throwable.getMessage());
                if(throwable instanceof HttpException) {
                    HttpException response = (HttpException) throwable;
                    int code = response.code();
                    baseResponse.setStatus(code);
                    baseResponse.setMessage(response.getMessage());
                } else {
                    baseResponse.setMessage(throwable.getMessage());
                    baseResponse.setStatus(0);
                }
                return baseResponse;
            }).subscribe(onNext);
    }
}
