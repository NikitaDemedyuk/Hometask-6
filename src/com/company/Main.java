package com.company;

import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "/Users/nikita/Documents/Hometask university/Java/Hometask 4/Text.csv";
        System.out.println(fileName + "\n");

        try (FileWriter writer = new FileWriter("TextOut.txt", false)) {

            Date date = new Date();
            DateFormat formatter = new SimpleDateFormat();
            String formattedDate = formatter.format(date);
            writer.write("Time: " + formattedDate);

            List<String> stringList = readUsingFileReader(fileName);
            List<List<String>> stringListD = findWords(stringList);

            for (int i = 0; i < stringListD.size(); ++i) {
                System.out.print("\n" + i + ")");
                for (int j = 0; j < stringListD.get(i).size(); ++j) {
                    System.out.print(stringListD.get(i).get(j) + "  ");
                }
            }

            //System.out.print("End");
            //for (String str : args) {
            //  System.out.println("Файл = " + str);
            //}

            // printToFileData();


            System.out.print("\nShort name of company: ");
            Scanner in = new Scanner(System.in);
            String lineNameCompany = in.nextLine();

            findCompany(stringListD, lineNameCompany);
            writer.write("Short name of company : " + lineNameCompany + "\n");

            System.out.print("\nIndustry of company: ");
            String lineIndustryCompany = in.nextLine();

            findCompany(stringListD, lineIndustryCompany);
            writer.write("Industry of company : " + lineIndustryCompany + "\n");

            System.out.print("\nType of activity: ");
            String lineActivityCompany = in.nextLine();

            findCompany(stringListD, lineActivityCompany);
            writer.write("Activity of company : " + lineActivityCompany + "\n");

            try {
                System.out.print("\nEnter number of staff (min): ");
                int staffCompanyMin = in.nextInt();
                System.out.print("\nEnter number of staff (max): ");
                int staffCompanyMax = in.nextInt();
                findCompanyPersonal(stringListD, staffCompanyMin, staffCompanyMax);
                writer.write("Number of staff (min) : " + staffCompanyMin+ "\nNumber of staff (max) : " + staffCompanyMax);
            } catch (Exception e) {
                System.out.print("Error: " + e.getMessage());
            }
        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<String> readUsingFileReader(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        List<String> stringList = new ArrayList();
        while ((line = br.readLine()) != null) {
            stringList.add(line);
        }
        br.close();
        fr.close();
        return stringList;
    }

    public static List<List<String>> findWords(List<String> stringList) throws IOException {
        String delimeter = ";";
        List<List<String>> stringListD = new ArrayList();
        for (int i = 0; i < stringList.size(); ++i) {
            stringListD.add(Arrays.asList(stringList.get(i).split(delimeter)));

        }
        return stringListD;
    }

    public static void findCompany(List<List<String>> stringListD, String value) {
        int companyFound = 0;
        for (int i = 0; i < stringListD.size(); ++i) {
            for (int j = 0; j < stringListD.get(i).size(); ++j) {
                if (value.equals(stringListD.get(i).get(j))) {
                    System.out.println("Company: " + stringListD.get(i).get(0));
                    ++companyFound;
                }
            }
        }
        if (companyFound == 0) {
            System.out.print("There aren't such companies in list\n");
        }
    }

    public static void findCompanyPersonal(List<List<String>> stringListD, int min, int max) {
        int[] arrayStaff = new int[stringListD.size() - 1];
        for (int i = 1; i < stringListD.size(); ++i) {
            arrayStaff[i - 1] = Integer.parseInt(stringListD.get(i).get(5));
        }

        for (int i = 1; i < stringListD.size(); ++i) {
            if ((arrayStaff[i - 1] > min) && (arrayStaff[i - 1] < max)) {
                System.out.println("Company: " + stringListD.get(i).get(0));
            }
        }
    }
}