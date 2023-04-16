package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option;
        do {
            System.out.println("Menu\n1 Dodaj notatkę.\n2 Wyświetl notatkę.\n3 Usuwanie notatki.\n4 Wyszukiwanie notatki.\n5 Wyjście.");
            option = scanner.next();
            switch (option) {
                case "1":
                    System.out.println("Dodaj notatkę.");
                    scanner.nextLine();
                    String fileName = scanner.nextLine();
                    try {
                        File file = new File(fileName);
                        System.out.println("Wpisz treść: ");
                        String content = scanner.nextLine();
                        java.nio.file.Files.write(file.toPath(), content.getBytes());
                        System.out.println("Plik został zapisany.");
                    } catch (IOException e) {
                        System.out.println("Błąd: "+ e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Wyświetl notatkę.");
                    scanner.nextLine();
                    fileName = scanner.nextLine();
                    try {
                        File file = new File(fileName);
                        Scanner fileScanner = new Scanner(file);

                        while (fileScanner.hasNext()) {
                            String line = fileScanner.nextLine();
                            System.out.println(line);
                        }

                        fileScanner.close();
                    }catch (FileNotFoundException e) {
                        System.out.println("Błąd: " + e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("Usuwanie notatki.");
                    System.out.println("Wpisz nazwe pliku.");
                    scanner.nextLine();
                    fileName = scanner.nextLine();

                    try {
                        File file = new File(fileName);
                        if(file.delete()) {
                            System.out.println("Udało się usunąć plik.");
                        }else {
                            System.out.println("Nie udało się usunąć pliku.");
                        }
                    } catch (Exception e) {
                        System.out.println("Błąd" + e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Wyszukiwanie notatki.");
                    System.out.println("Wpisz notatkę która chcesz wyszukać");
                    String search = scanner.nextLine();

                    try {
                        File folder = new File(".");
                        File[] files = folder.listFiles();
                        boolean found = false;

                        assert files != null;
                        for (File file : files) {
                            if (file.getName().toLowerCase().contains(search.toLowerCase())) {
                                Scanner fileScanner = new Scanner(file);

                                while (fileScanner.hasNextLine()) {
                                    String line = fileScanner.nextLine();
                                    System.out.println(line);
                                }

                                fileScanner.close();
                                found = true;
                            }
                        }

                        if (!found) {
                            System.out.println("Nie znaleziono pliku.");
                        }
                    } catch (Exception e) {
                        System.out.println("Błąd: " + e.getMessage());
                    }

                    break;
                case "5":
                    System.out.println("Wyjście." +option);
                    break;
                default:
            }
        }while (!"5".equals(option));
    }
}
