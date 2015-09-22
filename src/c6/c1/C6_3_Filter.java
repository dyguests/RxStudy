package c6.c1;

import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.TimeUnit;

/**
 * Created by fanhl on 15/9/22.
 */
public class C6_3_Filter {

    public static void main(String[] args) {
//        debounce();
//        distinct();
//        elementAt();
//        filter();
//        first();
//        ignoreElements();
//        sample();
//        skip
//        take
        
    }

    private static void debounce() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                    try {
                        Thread.sleep(i * 300 % 800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                subscriber.onCompleted();
            }
        }).debounce(500, TimeUnit.MILLISECONDS).subscribe(System.out::println);
    }

    private static void distinct() {
        Observable.just(1, 2, 3, 1, 2, 3, 4, 3, 4, 5).distinct().subscribe(System.out::println);
    }

    private static void elementAt() {
        Observable.just(1, 2, 3, 4, 5).elementAt(2).subscribe(System.out::println);
    }

    private static void filter() {
        Observable.just(1, 2, 3, 4, 5, 6, 7).filter(i -> i / 2 * 2 == i).subscribe(System.out::println);
    }

    private static void first() {
        Observable.just(1, 2, 3).first().subscribe(System.out::println);
    }

    private static void ignoreElements() {
        Observable.just(1, 2, 3, 4).ignoreElements().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: " + integer);
            }
        });
    }

    private static void sample() {
        //FIXME 没弄明白
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9);
        observable.subscribe(System.out::println);
        observable.sample(100, TimeUnit.MILLISECONDS).subscribe(System.out::println);
    }
}