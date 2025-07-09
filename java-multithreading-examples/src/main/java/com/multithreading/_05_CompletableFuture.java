package com.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  ----- JAVA 8 CompletableFuture --------
 * Source : https://nurkiewicz.com/2013/05/java-8-definitive-guide-to.html
 * Youtube : https://www.youtube.com/watch?v=xpjvY45Hbyg
 *
 * CompletableFuture => A reference to the result of an asynchronous task.
 *
 *   -- Future Limitations :
 *      - Cannot be completed manually.
 *      - Cannot be chained / combined together
 *      - A callback function cannot be attached to the future.
 *      - Cannot do exception handling
 *      - Action cannot be performed until the result is available in Future. (Future.get() is a blocking call).
 *
 *      All of the above limitations have been overcome in CompletableFuture API.
 *
 */
public class _05_CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        /**
         *  -  We can create CompletableFuture instance by using new keyword.
         *  - We can get the value using .get() method. It is blocking call.
         *  - But .get() will always remain blocking if we do not complete() the CompletableFuture object.
         *  - Future always needs to be Completed.
         */

        CompletableFuture<String> cf0 = new CompletableFuture<>();
        cf0.complete("Completed!"); // If this line is removed, program will keep indefinitely waiting in next .get() statement.
        System.out.println(cf0.get()); // Prints "Completed!"

        /**
         * - Generally, new keyword is not used to create CompletableFuture as then we have to manually .complete() it.
         * - We create CompletableFuture instance by using.supplyAsync() method from CompletableFuture class
         * - We provide an instance of Supplier Functional Interface to that method.
         */
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(()-> {
            sleep(5000);
            return "Async task processing finished";
        });

        System.out.println("Didn't block for Async task to complete"); // This gets printed immediately.
        System.out.println(cf.get()); // Here the flow is blocked (for 5 sec), as .get() waits for the result of async task.
        System.out.println("This should be printed after async task completed"); // This gets printed after waiting for 5 sec

        /**
         * - CompletableFuture instances are favoured over normal Future instances,
         *   because we can compose and combine the async() tasks together. This makes
         *   it the perfect case for use in Functional Programming.
         */

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "This is an async task")
                .thenApply((s) -> s + " and this is another async task");

        System.out.println(cf1.get()); // Prints "This is an async task and this is another async task".

        /**
         * Chaining callbacks
         *  - .thenApply() --> Applied a Function Interface (Meant to return a completableFuture instance)
         *  - .thenAccept() --> Applies a Consumer Interface (Doesn't return anything)
         *
         *  Note in below example the cf2 type is CompletableFuture<Void> because .thenAccept returns it.
         */

        CompletableFuture<Void> cf2 = CompletableFuture.supplyAsync(()-> "Async Task #2")
                .thenApply(String::toLowerCase)
                .thenAccept(System.out::println);

        cf2.get(); // Prints "async task #2"

        /**
         * If we want the chaining callbacks to run in different threads then use the
         * ..async versions of the callback methods.
         *
         *  - So the three threadnames printed will be different below.
         */

        CompletableFuture<Void> cf4 = CompletableFuture.supplyAsync(()-> {
                    System.out.println(Thread.currentThread().getName()); // Prints "ForkJoinPool.commonPool-worker-<some integer>"
                    sleep(300);
                    return "Async Task #3";
                })
                .thenApplyAsync((s)-> {
                    System.out.println(Thread.currentThread().getName()); // Prints "ForkJoinPool.commonPool-worker-<some integer>"
                    sleep(100);
                    return s + " in Java";
                })
                .thenAcceptAsync((s)-> {
                    System.out.println(Thread.currentThread().getName()); // Prints "ForkJoinPool.commonPool-worker-<some integer>"
                    sleep(300);
                    System.out.println(s);
                });

        cf4.get();

        /**
         * Using custom executorService
         *  - Here we are passing our own impl'n of threadpool of 5 threads.
         *  - Notice how each callback function that we pass will print different threads from the threadpool.
         *  - All the callbacks will have to accept additional parameter of ExecutorService reference.
         */
        ExecutorService ex = Executors.newFixedThreadPool(5);

        CompletableFuture.supplyAsync(()-> "Async Task #1", ex)
                .thenApplyAsync((s)-> {
                    System.out.println(Thread.currentThread().getName());   // // Prints "pool-1-thread-2"
                    sleep(300);
                    return s.toUpperCase();
                }, ex)
                .thenAcceptAsync((s)-> {
                    System.out.println(Thread.currentThread().getName());  // Prints "pool-1-thread-3"
                    sleep(300);
                    System.out.println("String - "+s);
                }, ex)
                .get();

        ex.shutdown();






    }

    /**
     * Make the current thread sleep.
     * @param millis
     */
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
