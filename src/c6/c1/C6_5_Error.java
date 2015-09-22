package c6.c1;

import rx.Observable;

/**
 * Created by fanhl on 15/9/22.
 */
public class C6_5_Error {
    public static void main(String[] args) {
        onErrorReturn();
        //略
    }

    private static void onErrorReturn() {
        Observable.create(subscriber -> subscriber.onError(new Exception()))
                .onErrorReturn(throwable -> 233)
                .subscribe(System.out::println);
    }
}
