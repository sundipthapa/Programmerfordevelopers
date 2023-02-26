package Questioncollections.Question7;

/*
b)	Write multithreaded web crawler

 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Solutionb {
    private static final int NUM_THREADS = 10; // number of threads to use

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS); // create executor service with NUM_THREADS threads
        List<Future<String>> results = new ArrayList<>(); // list to store results from each thread

        // submit tasks to executor service
        for (int i = 0; i < 100; i++) {
            results.add(executorService.submit(new CrawlTask("https://www.canva.com/" + i)));
        }

        // wait for all tasks to finish and collect results
        for (Future<String> result : results) {
            try {
                System.out.println(result.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Task failed: " + e.getMessage());
            }
        }
        executorService.shutdown(); // shut down executor service
    }

    private static class CrawlTask implements Callable<String> {
        private final String url;

        public CrawlTask(String url) {
            this.url = url;
        }

        @Override
        public String call() throws Exception {
            // implement web crawling logic here
            return "Crawled " + url;
        }
    }
}

/*
This code creates an ExecutorService with NUM_THREADS threads and submits CrawlTask instances to it. Each CrawlTask represents a single web crawling task and implements the Callable interface to return the result of the task.

The main method waits for all tasks to finish by calling the get method on each Future in the results list. The results of each task are printed to the console.

Note: The web crawling logic is currently empty and needs to be implemented in the call method of the CrawlTask class.
 */
