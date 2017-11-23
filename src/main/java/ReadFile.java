
import java.io.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Files.readAllLines;

public class ReadFile {

   // final String InputFilewithContent = relativePath.toString();
    String InputFilewithContent;

    public ReadFile(String RelativeFileName) throws FileNotFoundException {
        // определяем объект для каталога
       this.exists(RelativeFileName);
        this.InputFilewithContent = RelativeFileName;
    }

    //Для проверки на существование файла создадим метод,
    private void exists(String fileName) throws FileNotFoundException {

            File file = new File(fileName);
            if (!file.exists()) {
                throw new FileNotFoundException(file.getName());
            }
    }

    public void UsingFileReader(String fileName)throws IOException{
        File file = new File(fileName);
        //создаем объект FileReader для объекта File
        FileReader fr = new FileReader(file);
        //создаем BufferedReader с существующего FileReader для построчного считывания
        BufferedReader br = new BufferedReader(fr);
        // считаем сначала первую строку
        String line;
        while((line = br.readLine()) != null){
            //обрабатываем считанную строку - пишем ее в консоль
            System.out.println(line);
        }
        br.close();
        fr.close();

    }


    //Используем класс Files для обработки небольших файлов, получаем содержимое файла файла
    public String byRead_UsingFileReader(String fileName) throws IOException {

        File file = new File(fileName);
        //создаем объект FileReader для объекта File
        FileReader fr = new FileReader(file);
        //создаем BufferedReader с существующего FileReader для построчного считывания
        BufferedReader br = new BufferedReader(fr);
        // считаем сначала первую строку
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        String line;
        try {
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
        } finally {
            //Также не забываем закрыть файл
            br.close();
        }

        br.close();
        fr.close();
        //Возвращаем полученный текст с файла
        return stringBuilder.toString();
    }


    // считываем содержимое файла в String с помощью BufferedReader
    public String byStream_UsingBufferedReader(String fileName) throws IOException {

        //Объект для чтения файла в буфер
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        //Этот спец. объект для построения строки
        StringBuilder stringBuilder = new StringBuilder();
        //StringBuilder — в чем разница между обычным String?
        // В том что когда  вы в StringBuilder добавляете текст он не пересоздается,
        // а String пересоздает себя.
        String ls = System.getProperty("line.separator");
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
        } finally {
            //Также не забываем закрыть файл
            reader.close();
        }

        //stringBuilder.deleteCharAt(stringBuilder.length()-1);
        //Возвращаем полученный текст с файла
        return stringBuilder.toString();
    }

    // читаем файл в строку с помощью класса Files
    public   String byRead_UsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }


    public  List<String> byRead_UsingBytes(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        //считываем содержимое файла в массив байт
        byte[] bytes = readAllBytes(path);
        //считываем содержимое файла в список строк
        List<String> allLines = readAllLines(path, StandardCharsets.UTF_8);
        return allLines;
    }


    public String byRead_UsingScanner(String Name) throws IOException {
        File fileName = new File(Name);
        Scanner sc = new Scanner(fileName);

        List<String> strings = new ArrayList<String>();

        StringBuilder stringBuilder = new StringBuilder();
        //StringBuilder — в чем разница между обычным String?
        // В том что когда  вы в StringBuilder добавляете текст он не пересоздается,
        // а String пересоздает себя.

        try {
            while (sc.hasNextLine()) {
            strings.add(sc.nextLine());

            }
        } finally {
            //Также не забываем закрыть файл
            sc.close();
        }
        stringBuilder.append(strings);
        //Возвращаем полученный текст с файла
        return stringBuilder.toString();
    }

    public String byRead_UsingJava78Example(String fileName) {
        StringBuilder sb = new StringBuilder(1024);

        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            for (String line : lines) {
                sb.append(line);
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String fromFile = sb.toString();
        return fromFile;
    }



}
