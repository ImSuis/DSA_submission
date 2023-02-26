
// 7b)	Write multithreaded web crawler
//
//
import java.util.*;

public class Q7B {
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    int workingThreads = 0;

    public void crawl() {
        OUTER_LOOP: while(true) {
            String nextUrl;
            synchronized(this) {
                while(queue.isEmpty()) {
                    if(workingThreads == 0) {
                        break OUTER_LOOP;
                    }
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                nextUrl = queue.poll();
                workingThreads++;
            }
            List<String> URLs = getLinks(nextUrl);

            synchronized(this) {
                for(String newUrl: URLs) {
                    if(!visited.contains(newUrl)) {
                        queue.offer(newUrl);
                        visited.add(newUrl);
                    }
                }
                workingThreads--;
                notifyAll();
            }
        }
    }

    public List<String> getLinks(String url) {
        List<String> links = new ArrayList<>();
        return links;
    }

    public static void main(String[] args) {
        // Create a new instance of Q7_b
        Q7B webCrawler = new Q7B();

        String startingUrl = "https://http://www.google.com";
        webCrawler.queue.offer(startingUrl);
        webCrawler.visited.add(startingUrl);

        Thread[] workers = new Thread[5];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Thread(webCrawler::crawl);
            workers[i].start();
        }

        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Visited URLs:");
        for (String url : webCrawler.visited) {
            System.out.println(url);
        }
    }

}