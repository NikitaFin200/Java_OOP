package ru.academits.findyurov.arraylisthome;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> stringsList = new ArrayList<>();

        try {
            stringsList = getStringsFromFile("file.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file");
        }

        System.out.println("Strings from file:");
        System.out.println(stringsList);

        System.out.println("The stringsList is cleared");
        stringsList.clear();
        System.out.println();

        ArrayList<Integer> numbersList1 = new ArrayList<>(Arrays.asList(10, 20, 20, 40, 50, 60, 11, 21, 31, 30, 33, 53,
                55, 100, 100, 101, 102));

        System.out.println("The ArrayList that has just been filled in:");
        System.out.println(numbersList1);
        System.out.println();

        deleteEvenNumbers(numbersList1);
        System.out.println("List without even numbers:");
        System.out.println(numbersList1);
        System.out.println();

        ArrayList<Integer> numbersList2 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 10, 14, 10));
        System.out.println("List with integer numbers, that are repeated:");
        System.out.println(numbersList2);
        System.out.println();

        System.out.println("List without repeat numbers:");
        System.out.println(getListWithoutRepeats(numbersList2));
        System.out.println();
    }

    public static ArrayList<String> getStringsFromFile(String line) throws IOException {
        ArrayList<String> stringsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(line))) {
            String string;

            while ((string = reader.readLine()) != null) {
                stringsList.add(string);
            }
        }

        return stringsList;
    }

    public static void deleteEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == null) {
                throw new IllegalArgumentException("There is an empty cell in the list.");
            }

            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> getListWithoutRepeats(ArrayList<Integer> list) {
        ArrayList<Integer> listWithoutRepeats = new ArrayList<>(list.size());

        for (Integer number : list) {
            if (!listWithoutRepeats.contains(number)) {
                listWithoutRepeats.add(number);
            }
        }

        return listWithoutRepeats;
    }
}
