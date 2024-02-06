package InputOutStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String[] address = {
                "C://Users//Алексей Коданёв//Desktop//Games//src",
                "C://Users//Алексей Коданёв//Desktop//Games//res",
                "C://Users//Алексей Коданёв//Desktop//Games//savegames",
                "C://Users//Алексей Коданёв//Desktop//Games//temp",
                "C://Users//Алексей Коданёв//Desktop//Games//src//main",
                "C://Users//Алексей Коданёв//Desktop//Games//src//test",
                "C://Users//Алексей Коданёв//Desktop//Games//res//drawables",
                "C://Users//Алексей Коданёв//Desktop//Games//res//vectors",
                "C://Users//Алексей Коданёв//Desktop//Games//res//icons",
        };
        String[] files = {
                "C://Users//Алексей Коданёв//Desktop//Games//src//main//Main.java",
                "C://Users//Алексей Коданёв//Desktop//Games//src//main//Utils.java",
                "C://Users//Алексей Коданёв//Desktop//Games//temp//temp.txt",
        };

        for (int i = 0; i < address.length; i++) {
            File file = new File(address[i]);
            if (file.mkdir()) {
                stringBuilder.append("Папка по адресу:" + " " + file + " " + "успешно создана\n");
            } else {
                stringBuilder.append("Папка по адресу:" + " " + file + " " + "не создана\n");
            }
        }

        try {
            for (int i = 0; i < files.length; i++) {
                File name = new File(files[i]);
                if (name.createNewFile()) {
                    stringBuilder.append("Файл по адресу:" + " " + name + " " + "успешно создан\n");
                } else {
                    stringBuilder.append("Файл по адресу:" + " " + name + " " + "не создан\n");
                }
            }
        } catch (IOException ex) {
            stringBuilder.append(ex.getMessage());
        }

        try (FileWriter log = new FileWriter("C://Users//Алексей Коданёв//Desktop//Games//temp//temp.txt", true)) {
            log.write(stringBuilder.toString());
        } catch (IOException ex) {
            stringBuilder.append(ex.getMessage());
        }
    }
}
