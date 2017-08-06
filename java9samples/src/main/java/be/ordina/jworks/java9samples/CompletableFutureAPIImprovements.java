package be.ordina.jworks.java9samples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture API improvements.
 *
 * @author Yannick De Turck
 */
public class CompletableFutureAPIImprovements {
    public static void main(String[] args) throws InterruptedException {
        // completeAsync
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("calculated value1");
        future.whenComplete((s, throwable) -> System.out.printf("value -> %s, throwable -> %s\n", s, throwable));
        // value -> calculated value1, throwable -> null
        future = new CompletableFuture<>();
        CompletableFuture<String> completedAsync =
                future.completeAsync(() -> "calculated value2");
        completedAsync.whenComplete((s, throwable) -> System.out.printf("value -> %s, throwable -> %s\n", s, throwable));
        // value -> calculated value2, throwable -> null

        // orTimeout
        CompletableFuture<String> someFuture = new CompletableFuture<>();
        someFuture.orTimeout(1, TimeUnit.SECONDS);
        someFuture.whenComplete((s, throwable) -> System.out.printf("value -> %s, throwable -> %s\n", s, throwable));
        // value -> null, throwable -> java.util.concurrent.TimeoutException

        // completedOnTimeout
        CompletableFuture<String> otherFuture = new CompletableFuture<>();
        otherFuture.completeOnTimeout("alternative result", 1, TimeUnit.SECONDS);
        otherFuture.whenComplete((s, throwable) -> System.out.printf("value -> %s, throwable -> %s\n", s, throwable));
        // value -> alternative result, throwable -> null

        Thread.sleep(2000L);
    }

    class MyCompletableFuture<T> extends CompletableFuture<T> {
        @Override
        public <U> CompletableFuture<U> newIncompleteFuture() {
            return new MyCompletableFuture<>();
        }
    }
}
