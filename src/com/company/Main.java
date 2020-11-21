package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.company.Company.*;
import static com.company.Company.findCompanyByDateFoundation;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "/Users/nikita/Documents/University/Programming/Java/Hometask-6/Hometask-6/Text.txt";
        System.out.println(fileName + "\n");

        try (FileWriter writer = new FileWriter("TextOut.txt", false)) {

            Scanner in = new Scanner(System.in);
            Date date = new Date();
            DateFormat formatter = new SimpleDateFormat();
            String formattedDate = formatter.format(date);
            writer.write("Time: " + formattedDate);


            String delimeter = ";";

            List<List<String>> stringListD = readUsingFileReader(fileName, delimeter);

            System.out.print("\nView a list of companies?\n1 - See\n2 - Skip\nEnter: ");
            int variant = in.nextInt();
            if (variant == 1) {
                for (int i = 0; i < stringListD.size(); ++i) {
                    System.out.print("\n" + i + ")");
                    for (int j = 0; j < stringListD.get(i).size(); ++j) {
                        System.out.print(stringListD.get(i).get(j) + "  ");
                    }
                }
            }

            //System.out.print("End");
            //for (String str : args) {
            //  System.out.println("Файл = " + str);
            //}

            // printToFileData();

            List<Company> companyList = goToListCompany(stringListD);

            System.out.println("\n1 - Use SQL request\n2 - Use list of companies");
            System.out.print("Enter: ");
            int variantUse = in.nextInt();

            if (variantUse == 1) {
                String fileNameSQL = "/Users/nikita/Documents/University/Programming/Java/Hometask-6/Hometask-6/RequestSQL.txt";
                findByRequestSQL(companyList, fileNameSQL);
            }
            if (variantUse == 2) {
                System.out.print("\n\n");
                System.out.println("1 - Find a company by it's short name\n2 - Find a company by it's industry\n3 - Find a company by it's activity\n4 - " +
                        "Find a company by it's date of foundation\n5 - Find a company by the number of staff\n6 - See the list of companies\n7 - Exit");
                System.out.print("Enter: ");
                int variantChoise = in.nextInt();

                if (variantChoise == 1) {
                        System.out.print("\nShort name of company: ");
                        String lineNameCompany = in.nextLine();
                        findCompany(companyList, lineNameCompany);
                        writer.write("Short name of company : " + lineNameCompany + "\n");
                }

                if (variantChoise == 2) {
                        System.out.print("\nIndustry of company: ");
                        String lineIndustryCompany = in.nextLine();
                        findCompanyByIndustry(companyList, lineIndustryCompany);
                        writer.write("Industry of company : " + lineIndustryCompany + "\n");
                }

                if (variantChoise == 3) {
                        System.out.print("\nType of activity: ");
                        String lineActivityCompany = in.nextLine();
                        findCompanyByActivity(companyList, lineActivityCompany);
                        writer.write("Activity of company : " + lineActivityCompany + "\n");
                }

                if (variantChoise == 4) {
                        try {
                            System.out.print("\nEnter the date of foundation (min): ");
                            int dateFoundationCompanyMin = in.nextInt();
                            System.out.print("\nEnter the date of foundation (max): ");
                            int dateFoundationCompanyMax = in.nextInt();
                            findCompanyByDateFoundation(companyList, dateFoundationCompanyMin, dateFoundationCompanyMax);
                            writer.write("Date of foundation (min) : " + dateFoundationCompanyMin + "\nDate of foundation (max) : " + dateFoundationCompanyMax);
                        }
                        catch (Exception e) {
                            System.out.print("Error: " + e.getMessage());
                        }
                }

                if (variantChoise == 5) {
                        try {
                            System.out.print("\nEnter number of staff (min): ");
                            int staffCompanyMin = in.nextInt();
                            System.out.print("\nEnter number of staff (max): ");
                            int staffCompanyMax = in.nextInt();
                            findCompanyByStaff(companyList, staffCompanyMin, staffCompanyMax);
                            writer.write("Number of staff (min) : " + staffCompanyMin + "\nNumber of staff (max) : " + staffCompanyMax);
                        } catch (Exception e) {
                            System.out.print("Error: " + e.getMessage());
                        }
                }

                if (variantChoise == 6) {
                        for (int i = 0; i < stringListD.size(); ++i) {
                            System.out.print("\n" + i + ")");
                            for (int j = 0; j < stringListD.get(i).size(); ++j) {
                                System.out.print(stringListD.get(i).get(j) + "  ");
                            }
                        }
                }
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("\n\nEnd of program");
    }
}

