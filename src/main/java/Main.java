
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.io.File;

import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.FileLock;
import java.nio.channels.FileLockInterruptionException;
import java.nio.channels.OverlappingFileLockException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import java.util.List;
import java.util.Scanner;
import static java.nio.file.Files.*;

/*
        Если вам нужно считывать с файла, опираясь на разделитель, то желательно использовать
        класс Scanner.
        Если файл небольшой, то используем класс Files.
        Если нужно считать информация с больших файлов, то отлично подойдет BufferedReader */

public class Main {
    //Подготавливаем метод Main, чтобы не заморачиваться с обработкой исключений пропишем IOException
    public static void main(String[] args) throws IOException{
        String FILENAME1 = "C:\\git\\Alexsabrepo\\java_class\\IO.files\\input.txt";
        String filenameIn = "C:/git/Alexsabrepo/java_class/IO.files/input.txt";
        File file = new File("C:/git/Alexsabrepo/java_class/IO.files/input.txt");
        String text = "This new text This new text2This new text3This new text4";
        String fileNameOut = "C:/git/Alexsabrepo/java_class/IO.files/output.txt";
        exists(filenameIn);
        exists(fileNameOut);

     // Reading from a input file

        //readUsingFileReader(filename2);
        //readUsingScanner(file);
        //readUsingFiles(filename2);
        //StreamUsingBufferedReader(filenameIn);
        //readUsingBufferedReader(filenameIn);
        //readusingAllBytes(file);

        //readUsingFiles(filename2);  - does not work
        //System.out.println(Java78FileReadingExample(filename2)) - does not work;


        System.out.println("Staring  by reading created File in Java");
        System.out.println("+++++++++ Start +++++++++++++++");

        String TextintheFile=StreamUsingBufferedReader(filenameIn);
        //String TextintheFile= ReadFile.usingFileReader(filenameIn);
        System.out.println(TextintheFile);

        System.out.println("+++++++++++ End +++++++++++++++");

        System.out.println("+++++++++ Calculated Result +++++++++++++++");

        // Calculation options:
        //CalculateCharsNum_usingARRAY(TextintheFile);
        Map counts = CalculateCharsInSringUsingMap(TextintheFile); //for Alex
        CalculateCharsUsingMap2(TextintheFile); //for Console, but another method

// OUTPUT TO CONSOLE
        //PrintCharsMapToConsole(counts);
        System.out.println("++++++++++ End of Results ++++++++++++++");

     //Writing to a file using different methods
     //   http://www.baeldung.com/java-write-to-file
        //WriteStringToFile_whenUsingPrintWriter(fileNameOut, counts);
        //WriteStringToFile_whenUsingBufferedWriter(fileNameOut, counts);
        //whenAppendStringUsingBufferedWritter(fileNameOut, counts);
        //WritingStringToFile_whenUsingFileWriter(fileNameOut, counts);
        //WritingStringToFile_whenUsingFileOutputStream(fileNameOut, counts);
        //WritingToFile_whenUsingDataOutputStream(fileNameOut, counts);
        WritingToFile_whenUsingFileChannel(fileNameOut,counts); //for Alex
    }
//Для проверки на существование файла создадим метод,
    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }

    private static void PrintCharsMapToConsole(Map countObject){

        for (int i = 0; i < 255; i++) {
                 if(countObject.containsKey((char)i)) {
                     System.out.println((char)i  + " встречается " + countObject.get((char)i) + " раз");
                 }
        }
    }

// CALCULATION METHODS:
    //Using simple array
    private static void CalculateCharsNum_usingARRAY(String ContentintheFile) {
        int[] arr = new int[255];
        for (int i = 0; i < ContentintheFile.length(); i++) {
            //Для извлечения символов по индексу в классе String определен метод
            // char charAt(int index). Он принимает индекс, по которому надо получить символов,
            // и возвращает извлеченный символ
            arr[ContentintheFile.charAt(i)]++;
        }
        for (int i = 0; i < 255; i++) {
            if (arr[i] > 0) {
                System.out.println((char) i + " встречается " + arr[i] + " раз");
            }
        }
    }
// using Map
    private static Map<Character,Integer> CalculateCharsInSringUsingMap(String ContentintheFile){
        Map<Character,Integer> charMap = new HashMap<Character,Integer>();
        if (ContentintheFile != null) {
            for (Character charic : ContentintheFile.toCharArray()) {
                Integer count = charMap.get(charic);
                int newCount = (count==null ? 1 : count+1);
                charMap.put(charic, newCount);
            }
        }
        return charMap;
    }
//-----------------------------more Pair method - DOES NOT WORK------------
    public static class Pair
    {
        private char letter;
        private int count;
        public Pair(char letter, int count)
        {
            this.letter = letter;
            this.count= count;
        }
        public char getLetter(){return letter;}
        public int getCount(){return count;}
    }
    public static int countOccurrences(String ContentintheFile, char c)
    {
        int count = 0;
        for(int i = 0; i < ContentintheFile.length(); i++)
        {
            if(ContentintheFile.charAt(i) == c) count++;
        }
        return count;
    }

    public static void  countCharFreq(String ContentintheFile)
    {
        String temp = ContentintheFile;
        java.util.List<Pair> list = new java.util.ArrayList<Pair>();
        while(temp.length() != 0)
        {
            list.add(new Pair(temp.charAt(0), countOccurrences(temp, temp.charAt(0))));
            temp.replaceAll("[" + temp.charAt(0) +"]","");
        }

        for(int i = 0; i<temp.length(); i++){
            System.out.println(list.get(i));
        }
    }

//-----------------------end of Pair method ---------------

   private static void CalculateCharsUsingMap2(String ContentintheFile) {
        char array[] = ContentintheFile.toLowerCase().toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < ContentintheFile.length(); ++i) {
            char singlechar = ContentintheFile.charAt(i);
            int newCount = (map.get(singlechar)!=null ? map.get(singlechar) : 0);
            newCount++;
            // если надо, то проверяем является ли символ буквой
            if (Character.isLetter(singlechar)) {
                if (map.containsKey(singlechar)) {
                    map.put(singlechar, newCount);
                } else {
                    map.put(singlechar, 1);
                }
            }
        }
        for(Iterator<Character> it = map.keySet().iterator(); it.hasNext(); )
        {
            Character key = it.next();
            System.out.println(key+" = "+map.get(key));
        }


    }


    //READING_READING_READING_READING_READING_READING_READING_READING_READING_READING_READING_


    private static void readusingAllBytes(File filename){


    }

    //Используем класс Files для обработки небольших файлов, получаем содержимое файла файла
    private static void readUsingFileReader(String fileName) throws IOException {

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



    private static void readUsingBufferedReader(String fileName, Charset cs) throws IOException {
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, cs);
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        ArrayList<String> list = new ArrayList<String>();

        while((line = br.readLine()) != null){
            //char c = (char) br.read();

            System.out.println(line);
        }
        br.close();

    }

    // считываем содержимое файла в String с помощью BufferedReader
    private static String StreamUsingBufferedReader(String fileName) throws IOException {

        //Объект для чтения файла в буфер
        BufferedReader reader = new BufferedReader( new FileReader (fileName));
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
        }finally {
            //Также не забываем закрыть файл
            reader.close();
        }

        //stringBuilder.deleteCharAt(stringBuilder.length()-1);
        //Возвращаем полученный текст с файла
        return stringBuilder.toString();
    }

    // читаем файл в строку с помощью класса Files
    private static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }


    private static void readUsingScanner(File fileName) throws IOException {
        List<String> strings = new ArrayList<String>();
        Scanner sc = new Scanner(fileName);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            strings.add(line.trim()); //.trim() осуществляет обрезание пробелов
            System.out.println(line); //печать строки в стандартный вывод
        }
    }

    private static void readUsingFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        //считываем содержимое файла в массив байт
        byte[] bytes = readAllBytes(path);
        //считываем содержимое файла в список строк
        List<String> allLines = readAllLines(path, StandardCharsets.UTF_8);

    }

    private static String Java78FileReadingExample(String fileName) {
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

    // WRITING_WRITING_WRITING_WRITING_WRITING_WRITING_WRITING_WRITING_WRITING_

    public static void WriteStringToFile_whenUsingPrintWriter(String fileName, Map CalculatedStringinMap) {
        //PrintWriter prints formatted representations of objects to a text-output stream.
        // This class implements all of the print methods found in PrintStream.
        // It does not contain methods for writing raw bytes, for which a program should use
        // unencoded byte streams.
        //Определяем файл
        File file = new File(fileName);
        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }
            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try{
                //Записываем текст у файл
                for (int i = 0; i < 255; i++) {
                    if(CalculatedStringinMap.containsKey((char)i)) {
                        out.println((char)i  + " counts " + CalculatedStringinMap.get((char)i) + " times");
                    }
                }
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void WriteStringToFile_whenUsingBufferedWriter(String fileName, Map CalculatedStringinMap)
    throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        //Записываем текст у файл
        for (int i = 0; i < 255; i++) {
            if(CalculatedStringinMap.containsKey((char)i)) {
                writer.write(((char)i  + " counts " + CalculatedStringinMap.get((char)i) + " times"));
                writer.newLine();
            }
        }
            writer.close();
        }

    public static void whenAppendStringUsingBufferedWritter(String fileName, Map CalculatedStringinMap)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        //Записываем текст у файл
        for (int i = 0; i < 255; i++) {
            if(CalculatedStringinMap.containsKey((char)i)) {
                writer.append(((char)i  + " counts " + CalculatedStringinMap.get((char)i) + " times"));
                writer.newLine();
            }
        }
        writer.close();

    }

    public static void WritingStringToFile_whenUsingFileWriter(String fileName, Map CalculatedStringinMap)
    //In such situations the constructors in this class will fail if the file involved is already open.
            throws IOException {
                FileWriter WriterintheFile = new FileWriter(fileName);

        for (int i = 0; i < 255; i++) {
            if(CalculatedStringinMap.containsKey((char)i)) {
                if (Character.isLetter((char)i)) {
                    WriterintheFile.write(((char) i + " counts " + CalculatedStringinMap.get((char) i) + " times"));
                    WriterintheFile.append("\r");
                }
            }
        }
                WriterintheFile.close();
    }

    public static void WritingStringToFile_whenUsingFileOutputStream(String fileName, Map CalculatedStringinMap)
            throws IOException {
            FileOutputStream StreamToFile = new FileOutputStream(fileName);
            String StringtoWrite = "";
            String Separator = System.getProperty("line.separator");
            for (int i = 0; i < 255; i++) {
            StringtoWrite = (char)i  + " counts " + CalculatedStringinMap.get((char)i) + " times";
            StringtoWrite = StringtoWrite.trim().replaceAll("[\n]{2,}", "");
            if(CalculatedStringinMap.containsKey((char)i)) {
                StreamToFile.write(StringtoWrite.getBytes());
                StreamToFile.write(Separator.getBytes());
            }
            }

        StreamToFile.close();
    }

    public static void WritingToFile_whenUsingDataOutputStream(String fileName, Map CalculatedStringinMap)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
        String StringtoWrite = "";
        String Separator = System.getProperty("line.separator");
        for (int i = 0; i < 255; i++) {
            StringtoWrite = (char) i + " counts " + CalculatedStringinMap.get((char) i) + " times";
            StringtoWrite = StringtoWrite.trim().replaceAll("[\n]{2,}", "");
            if (CalculatedStringinMap.containsKey((char) i)) {
                outStream.writeUTF(StringtoWrite);
                outStream.writeUTF(Separator);
            }
        }
        outStream.close();
    }

    // метод для записи данных в файл c Channel
    public static void WritingToFile_whenUsingFileChannel(String fileName, Map CalculatedStringinMap)
            throws IOException {
        // открываем файл с возможностью как чтения, так и записи
        RandomAccessFile stream = new RandomAccessFile(fileName, "rw");

        FileChannel ChannelToFile = stream.getChannel();
        String Separator = System.getProperty("line.separator");
        String StringtoWrite = "";

        FileLock lockmyOutputFile = null;
        try{
            lockmyOutputFile = ChannelToFile.lock();
        } catch (final OverlappingFileLockException e){
            stream.close();
            ChannelToFile.close();
        }

        for (int i = 0; i < 255; i++) {
            switch (i) {
                case 10:
                    StringtoWrite = "/r" + " counts " + CalculatedStringinMap.get((char) i) + " times";
                    break;
                case 13:
                    StringtoWrite = "/n" + " counts " + CalculatedStringinMap.get((char) i) + " times";
                    break;
                default:
                    StringtoWrite = (char) i + " counts " + CalculatedStringinMap.get((char) i) + " times";
            }
            StringtoWrite = StringtoWrite.trim().replaceAll("[\n]{2,}", "");
            if (CalculatedStringinMap.containsKey((char) i)) {
                byte[] strBytes = (StringtoWrite + Separator).getBytes();
                ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
                buffer.put(strBytes);
                buffer.flip();
                ChannelToFile.write(buffer);
            }
        }
        lockmyOutputFile.release();
        ChannelToFile.close();
        stream.close();
    }

}


