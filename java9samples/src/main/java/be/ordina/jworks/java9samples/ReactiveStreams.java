package be.ordina.jworks.java9samples;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * Reactive streams.
 *
 * @author Yannick De Turck
 */
public class ReactiveStreams {
    public static void main(String[] args) throws Exception {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        Flow.Subscriber<String> subscriber = new MySubscriber<>();
        publisher.subscribe(subscriber);

        List<String> messages = List.of("these", "are", "a", "couple", "of", "words");
        messages.forEach(publisher::submit);

        Thread.sleep(1000L);
        publisher.close();
    }

    static class MySubscriber<T> implements Flow.Subscriber<T> {
        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("Received subscription: " + subscription);
            this.subscription = subscription;
            this.subscription.request(1);
        }

        @Override
        public void onNext(T item) {
            System.out.println("Treating item: " + item);
            this.subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("Treating error: " + throwable);
        }

        @Override
        public void onComplete() {
            System.out.println("Completed!");
        }
    }
}
