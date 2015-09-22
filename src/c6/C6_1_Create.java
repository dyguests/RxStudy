package c6;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;

/**
 * Created by fanhl on 15/9/22.
 */
public class C6_1_Create {
    public static void main(String[] args) {
//        create();
//        defer();
//        empty();
//        from();
//        interval();
//        just();
//        range();
//        repeat();
//        doOnEach();
//        timer();
        test();
    }

    private static void create() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for (int i = 0; i < 5; i++) {
                            subscriber.onNext(i);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Next: " + integer);
            }
        });
    }

    private static void defer() {
        Observable.defer(() -> Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for (int i = 0; i < 5; i++) {
                            subscriber.onNext(i);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Next: " + integer);
            }
        });
    }

    private static void empty() {
        Observable.empty().subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext");
            }
        });
    }

    private static void from() {
        Integer[] items = {1, 2, 3, 4, 5, 6};
        Observable.from(items).subscribe(System.out::print, System.out::println);
    }

    private static void interval() {
        //FIXME 不知道这是做啥的?
        Observable.just(1,2,3).timeInterval().subscribe(System.out::println);
    }

    private static void just() {
        Observable.just(1, 2, 3).subscribe(System.out::println);
    }

    private static void range() {
        Observable.range(1, 5).subscribe(System.out::println);
    }

    private static void repeat() {
        Observable.just(1, 2, 3).repeat(3).subscribe(System.out::println);
    }

    private static void doOnEach() {
        Observable.just(1, 2, 3).doOnEach(notification -> System.out.println("notification: " + notification)).subscribe(System.out::println);
    }

    private static void timer() {
        //FIXME 好像这么写没有用
        Observable.timer(5, TimeUnit.SECONDS).just(1, 2, 3).subscribe(System.out::println);
    }

    private static void test() {
        Observable.just(1, 2, 3, 4, 5, 6).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
