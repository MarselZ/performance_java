package local;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;
import java.util.concurrent.TimeUnit;


public class Task1 {
    public static void main(String[] args) {
        try (Connection connection = new ConnectionImpl()) {
            connection.start();  // вызов метода start() для начала работы с соединением
            try (Session session = connection.createSession(true)) {
                Destination destination = session.createDestination("My First Queue");
                Producer producer = session.createProducer(destination);  // Создаем продюсера (производителя) сообщений

                String[] messages = {"Five", "Six", "Seven"};
                for (String message : messages) {
                    producer.send(message);
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (DummyException e) {
                System.out.println("Caught: " + e);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted: " + e.getMessage());
        }
    }
}
