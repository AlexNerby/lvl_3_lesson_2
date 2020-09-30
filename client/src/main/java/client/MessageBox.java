package client;

import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageBox {

    private static final String rootPath = "MessageBox";
    private static FileWriter writer;
    private static BufferedReader reader;


    public static void addMessage(String msg) {
        try {
            writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
//    BufferedReader reader = new BufferedReader(new FileReader("demo.txt"))

    public static void clear(String login) {
        try {
            writer = new FileWriter(fileName(login));
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String returnMessage(String login) {

        try {
            reader = new BufferedReader(new FileReader(fileName(login)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File file = new File(fileName(login));
        if (file.exists()) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] arr = new String[100];
            try {
                for (int i = 0; i < arr.length; i++)
                    if (reader.readLine() != null) {
                        stringBuilder.append(reader.readLine()).append("\n");
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }
        System.out.println("file not found");
        return "";
    }

    public static String fileName(String login) {
        return rootPath + "/history_" + login + ".txt";
    }

    public static void create(String login) {
        try {
            writer = new FileWriter(fileName(login), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
