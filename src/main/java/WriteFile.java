import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Map;

public class WriteFile {
     String OutputFilewithContent;
    public WriteFile(String RelativeFileName) throws FileNotFoundException {
        // определяем объект для каталога
        this.exists(RelativeFileName);
        this.OutputFilewithContent = RelativeFileName;
    }

    //Для проверки на существование файла создадим метод,
    private void exists(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }



    public String WriteFile(String fileName) {
        File file = new File(fileName);

        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.getName();
    }

    public void toFile_UsingPrintWriter(String fileName, Map CalculatedStringinMap) {
        //PrintWriter prints formatted representations of objects to a text-output stream.
        // This class implements all of the print methods found in PrintStream.
        // It does not contain methods for writing raw bytes, for which a program should use
        // unencoded byte streams.
        //Определяем файл
        File file = new File(fileName);

        //PrintWriter обеспечит возможности записи в файл
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            //Записываем текст у файл
            for (int i = 0; i < 255; i++) {
                if (CalculatedStringinMap.containsKey((char) i)) {
                    out.println((char) i + " counts " + CalculatedStringinMap.get((char) i) + " times");
                }
            }

            //После чего мы должны закрыть файл
            //Иначе файл не запишется
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void toFile_UsingBufferedWriter(String fileName, Map CalculatedStringinMap)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        //Записываем текст у файл
        for (int i = 0; i < 255; i++) {
            if (CalculatedStringinMap.containsKey((char) i)) {
                writer.write(((char) i + " counts " + CalculatedStringinMap.get((char) i) + " times"));
                writer.newLine();
            }
        }
        writer.close();
    }


    public void byWriterOnlyLetter_UsingFileWriter(String fileName, Map CalculatedStringinMap)
        //In such situations the constructors in this class will fail if the file involved
        // is already open.
            throws IOException {
        FileWriter WriterintheFile = new FileWriter(fileName);

        for (int i = 0; i < 255; i++) {
            if (CalculatedStringinMap.containsKey((char) i)) {
                if (Character.isLetter((char) i)) {
                    WriterintheFile.write(((char) i + " counts " + CalculatedStringinMap.get((char) i) + " times"));
                    WriterintheFile.append("\r");
                }
            }
        }
        WriterintheFile.close();
    }
    public  void toFile_UsingFileOutputStream(String fileName, Map CalculatedStringinMap)
            throws IOException {
        FileOutputStream StreamToFile = new FileOutputStream(fileName);
        String StringtoWrite = "";
        String Separator = System.getProperty("line.separator");
        for (int i = 0; i < 255; i++) {
            StringtoWrite = (char) i + " counts " + CalculatedStringinMap.get((char) i) + " times";
            StringtoWrite = StringtoWrite.trim().replaceAll("[\n]{2,}", "");
            if (CalculatedStringinMap.containsKey((char) i)) {
                StreamToFile.write(StringtoWrite.getBytes());
                StreamToFile.write(Separator.getBytes());
            }
        }

        StreamToFile.close();
    }

    public  void toFile_UsingDataOutputStream(String fileName, Map CalculatedStringinMap)
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
    public  void toFile_UsingFileChannel(String fileName, Map CalculatedStringinMap)
            throws IOException {
        // открываем файл с возможностью как чтения, так и записи
        RandomAccessFile stream = new RandomAccessFile(fileName, "rw");

        FileChannel ChannelToFile = stream.getChannel();
        String Separator = System.getProperty("line.separator");
        String StringtoWrite = "";

        FileLock lockmyOutputFile = null;
        try {
            lockmyOutputFile = ChannelToFile.lock();
        } catch (final OverlappingFileLockException e) {
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


    public void toConsole_UsingPrintCharsMap(Map countObject) {

        for (int i = 0; i < 255; i++) {
            if (countObject.containsKey((char) i)) {
                System.out.println((char) i + " matches " + countObject.get((char) i) + " times");
            }
        }
    }


    //My method a) checks if file exists b) if not creates c) of not throws exception
    // d) locking file before writing e) shows /r /n


}
