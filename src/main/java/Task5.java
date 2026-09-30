import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task5 {
    public static void main(String[] args) throws InterruptedException {
        Task scanner = new PortScanner();
        scanner.start();
        Thread.sleep(5000);
        scanner.stop();
    }
}

interface Task {
    void start();

    void stop();
}

class PortScanner implements Task {
    private static final int THREADS = 100;
    private static final int START_PORT = 0;
    private static final int END_PORT = 65535;
    private static final String HOST = "127.0.0.1";
    private static final int TIMEOUT = 100;
    private ExecutorService executorService;
    private volatile boolean isRunning = false;

    @Override
    public void start() {
        if (isRunning) return;
        isRunning = true;
        executorService = Executors.newFixedThreadPool(THREADS);
        new Thread(() -> {
            System.out.println("Scanning started...");
            for (int i = START_PORT; i <= END_PORT && isRunning; i++) {
                final int port = i;
                executorService.execute(() -> {
                    if (!isRunning) return;
                    var inetSocketAddress = new InetSocketAddress(HOST, port);
                    try (Socket socket = new Socket()) {
                        socket.connect(inetSocketAddress, TIMEOUT);
                        System.out.println("Port " + port + " is opened");
                    } catch (IOException ignored) {

                    }
                });
            }
        }).start();
    }

    @Override
    public void stop() {
        if (!isRunning) return;
        isRunning = false;
        executorService.shutdownNow();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Scanning stopped");
    }
}

