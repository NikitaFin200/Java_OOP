package ru.academits.findyurov.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try {
            String csvFilePath = "fileForTaskCSV.csv";
            String htmlFilePath = "CSV.html";

            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            FileWriter writer = new FileWriter(htmlFilePath);

            writer.write("<!DOCTYPE html>\r");
            writer.write("<html lang=\"ru\">\r");
            writer.write("  <head>\r");
            writer.write("      <meta charset=\"UTF-8\">\r");
            writer.write("  </head>\r");
            writer.write("  <body>\r");
            writer.write("      <table>\r");

            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(","); // разбиение строки на ячейки по запятой

                writer.write("          <tr>\r"); // начало строки таблицы

                for (String cell : cells) {
                    if (Objects.equals(cell, "")) {
                        continue;
                    }

                    cell = cell.replaceAll("\"", "");
                    writer.write("              <td>");
                    writer.write(cell);
                    writer.write("</td>\r"); // добавление ячейки
                }

                writer.write("          </tr>\r"); // конец строки таблицы
            }

            reader.close();

            writer.write("      </table>\r");
            writer.write("  </body>\r");
            writer.write("</html>");
            writer.close();

            System.out.println("The conversion was completed successfully");
        } catch (IOException e) {
            System.out.println("Conversion error: " + e.getMessage());
        }
    }
}