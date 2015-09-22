package c6;

import rx.Observable;

/**
 * Created by fanhl on 15/9/22.
 */
public class C6_4_Combine {
    public static void main(String[] args) {
        //部分略
//        merge();
//        merge2();
//        startWith();
        //switch//???
//        zip();
    }

    private static void merge() {
        //没太明白. 是先Observable1再Observable2,而不是交叉的!??
        Observable.create(subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        }).mergeWith(Observable.create(subscriber -> {
            for (int i = 1; i < 5; i++) {
                subscriber.onNext(i * 10);
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        })).subscribe(System.out::println);
    }

    private static void merge2() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<Integer> observable2 = Observable.just(10, 20, 30);
        Observable.merge(observable1, observable2).subscribe(System.out::println);
    }

    private static void startWith() {
        Observable.just(1, 2, 3).startWith(100).subscribe(System.out::println);

    }

    private static void zip() {
        Observable<Integer> o1 = Observable.just(1, 2, 3);
        Observable<String> o2 = Observable.just("A", "B", "C");
        Observable<Character> o3 = Observable.just('a', 'b', 'c');
        Observable.zip(o1, o2, o3, (integer, s, character) -> integer + s + character).subscribe(System.out::println);
    }
}
