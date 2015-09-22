package c6;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by fanhl on 15/9/22.
 */
public class C6_7_Condition {
    public static void main(String[] args) {
//        all();
        amb();
    }

    private static void all() {
        Observable.just(1, 2, 3).all(i -> i < 10).subscribe(System.out::println);
    }

    private static void amb() {
        Observable<Integer> o1 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i < 10; i++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i);
                }
            }
        });
        Observable<Integer> o2 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i * 10);
                }
            }
        });
        Observable.amb(o1, o2).subscribe(System.out::println);
    }
}
