package local;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Task2 {
    public static void main(String[] args) {
        String defaultFilePath = "src/main/resources/puh.dat";
        String filePath;

        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = defaultFilePath;
        }

        while (true) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                break;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread was interrupted: " + e.getMessage());
                break;
            }
        }
    }
}
