import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SequentialPrint {
    private static final Logger logger = LoggerFactory.getLogger(SequentialPrint.class);
    private String last = "second";

    private synchronized void action(String message) {

        int j = 0;
        while (true) {
            try {
                j++;
                superAction(message, j);
                if (j == 10) {
                    while (j > 1) {
                        j--;
                        superAction(message, j);
                    }
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new NotInterestingException(ex);
            }
        }
    }

    private void superAction(String message, int j) throws InterruptedException {
        while (last.equals(message)) {
            wait();
        }
        //logger.info(message);
        last = message;
        sleep();
        notifyAll();
        //logger.info("print, j:{}",j);
        logger.info("nameThread = {} || currentThread = {} || j = {}",
                message, Thread.currentThread().getName(), j);
    }

    public static void main(String[] args) {
        SequentialPrint pingPong = new SequentialPrint();
        new Thread(() -> pingPong.action("first")).start();
        new Thread(() -> pingPong.action("second")).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(5_00);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static class NotInterestingException extends RuntimeException {
        NotInterestingException(InterruptedException ex) {
            super(ex);
        }
    }
}