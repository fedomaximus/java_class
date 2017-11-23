
import java.io.IOException;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.Map;

/*
        Если вам нужно считывать с файла, опираясь на разделитель, то желательно использовать
        класс Scanner.
        Если файл небольшой, то используем класс Files.
        Если нужно считать информация с больших файлов, то отлично подойдет BufferedReader */

public class Main {
    //Подготавливаем метод Main, чтобы не заморачиваться с обработкой исключений пропишем IOException
    public static void main(String[] args) throws IOException {
        String filenameIn = "./IO.files/input.txt";
        String fileNameOut = "./IO.files/output.txt";

        System.out.println("Staring  by reading created File in Java");
        System.out.println("+++++++++ Start +++++++++++++++");

        // Reading from a input file by a method
        ReadFile Reading = new ReadFile(filenameIn);
        //I like Scanner as it dies not takes /r/n symbols
        String ContentOfFile = Reading.byRead_UsingScanner(Reading.InputFilewithContent);
        //String ContentOfFile = Reading.byRead_UsingBytes(Reading.InputFilewithContent).toString();
        System.out.println(ContentOfFile);
        System.out.println("+++++++++++ End +++++++++++++++");
        System.out.println("+++++++++ Calculated Result +++++++++++++++");

        // Calculating how many times each character is met in the file
        Calculus Operate = new Calculus();
        Map CalculatedCharsFrequencyResult = Operate.byUsingMap_CharsInString(ContentOfFile); //for Alex


        //Writing to a file using different methods
        //   http://www.baeldung.com/java-write-to-file
        WriteFile Writing = new WriteFile(fileNameOut);
        Writing.toFile_UsingFileChannel(fileNameOut, CalculatedCharsFrequencyResult);
        Writing.toConsole_UsingPrintCharsMap(CalculatedCharsFrequencyResult);
        System.out.println("++++++++++ End of Results ++++++++++++++");

    }


}


