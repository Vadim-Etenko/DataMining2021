package laba2.txt_reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TxtReader {

    private static final String SPACE = " ";
    private static final HashMap<Integer, String> typeMap = new HashMap<>();
    private static final String PATH_HAM = "output/sms-ham-corpus.txt";
    private static final String PATH_SPAM = "output/sms-spam-corpus.txt";

    public TxtReader() {
        typeMap.put(1, PATH_HAM);
        typeMap.put(2, PATH_SPAM);
    }

    public ArrayList<String> getWords(int type) {
        File file = new File(typeMap.get(type));
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String i = sc.nextLine();
                list.add(i);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


    public ArrayList<String> getAllWords() {
        File file1 = new File(PATH_HAM);
        File file2 = new File(PATH_SPAM);
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file1);
            while (sc.hasNextLine()) {
                String i = sc.nextLine();
                list.add(i);
            }
            sc.close();

            sc = new Scanner(file2);
            while (sc.hasNextLine()) {
                String i = sc.nextLine();
                list.add(i);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


}
