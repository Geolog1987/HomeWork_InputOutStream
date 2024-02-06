package SerializableHomeWork;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        GameProgress gameProgress = new GameProgress(100, 1, 45, 54);
        GameProgress gameProgress2 = new GameProgress(73, 150, 50, 54);
        GameProgress gameProgress3 = new GameProgress(89, 150, 52, 54);
        saveGame("C://Users//Алексей Коданёв//Desktop//Games//savegames//save.dat", gameProgress);
        saveGame("C://Users//Алексей Коданёв//Desktop//Games//savegames//save2.dat", gameProgress2);
        saveGame("C://Users//Алексей Коданёв//Desktop//Games//savegames//save3.dat", gameProgress3);

        List<String> files = new ArrayList<String>();
        files.add("C://Users//Алексей Коданёв//Desktop//Games//savegames//save.dat");
        files.add("C://Users//Алексей Коданёв//Desktop//Games//savegames//save2.dat");
        files.add("C://Users//Алексей Коданёв//Desktop//Games//savegames//save3.dat");

        zipFiles("C://Users//Алексей Коданёв//Desktop//Games//savegames//zipSave.zip", files);

    }

    public static void saveGame(String address, GameProgress gameProgress) {
        try {
            FileOutputStream fileOut = new FileOutputStream(address);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(gameProgress);
            objectOut.close();
            fileOut.close();

            System.out.println("Игра успешно сохранена в файл " + address);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении игры: " + e.getMessage());
        }
    }

    public static void zipFiles(String zipFilePath, List<String> files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (String filePath : files) {
                try (FileInputStream fis = new FileInputStream(filePath)) {
                    File file = new File(filePath);
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, bytesRead);
                    }

                    zos.closeEntry();
                } catch (IOException e) {
                    System.out.println("Error zipping file: " + filePath);
                }
            }
        } catch (IOException e) {
            System.out.println("Error creating zip file: " + zipFilePath);
        }

        for (String filePath : files) {
            File file = new File(filePath);
            if (!file.delete()) {
                System.out.println("Error deleting file: " + filePath);
            }
        }
    }
}
