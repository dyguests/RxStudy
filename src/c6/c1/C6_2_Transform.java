package c6.c1;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by fanhl on 15/9/22.
 */
public class C6_2_Transform {
    public static void main(String[] args) {
//        buffer();
//        flatMap();
//        groupBy();
//        map();
//        scan();
        window();
    }

    private static void buffer() {
        Observable.just(1, 2, 3, 4, 5, 6).buffer(3).subscribe(System.out::println);
    }

    private static void flatMap() {
        Observable.just(new int[]{1, 2, 3}, new int[]{4, 5, 6}).flatMap(new Func1<int[], Observable<? super Integer>>() {
            @Override
            public Observable<? super Integer> call(int[] ints) {
                return Observable.just(ints[0], ints[1], ints[2]);
            }
        }).subscribe(System.out::println);
    }

    private static void groupBy() {
        //FIXME 不会用
        Observable.just(1, 2, 3, 4, 5, 6).groupBy(new Func1<Integer, Object>() {
            @Override
            public Object call(Integer integer) {
                return null;
            }
        }).forEach(new Action1<GroupedObservable<Object, Integer>>() {
            @Override
            public void call(GroupedObservable<Object, Integer> objectIntegerGroupedObservable) {

            }
        });
    }


    private static void map() {
        Observable.just(1, 2, 3, 4, 5).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "Str" + integer;
            }
        }).subscribe(System.out::println);
    }

    private static void scan() {
        Observable.just(1, 2, 3, 4, 5).scan((sum, item) -> sum + item).subscribe(System.out::println);
    }

    private static void window() {
        Observable.just(1, 2, 3, 4, 5, 6).window(2).subscribe(new Subscriber<Observable<Integer>>() {
            @Override
            public void onCompleted() {
                System.out.println("Main onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            @Override
            public void onNext(Observable<Integer> integerObservable) {
                integerObservable.subscribe(System.out::println, System.out::println, () -> System.out.println("Sub onCompleted"));
            }
        });
    }
}
