import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static String numberVacationGenerator() {
        Random random = new Random();
        String number = "";

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            number += digit;
        }
        return number;
    }

    public static void generateVacation(Scanner sc) {
        try {
            String requestNumber = numberVacationGenerator();
            BufferedWriter writer = new BufferedWriter(new FileWriter("vacationsDigitalRazgrad2023.csv", true));
            System.out.println("Моля въведи следната информация.");
            System.out.println("Две имена:");
            String name = sc.nextLine();
            System.out.println("Имейл:");
            String email = sc.nextLine();
            System.out.println("ЕГН:");
            String id = sc.nextLine();
            System.out.println("Дата за начало на отпуската(ден.месец.година):");
            String dataVacationBeginning = sc.nextLine();
            System.out.println("Дата за край на отпуската(ден.месец.година):");
            String dataVacationEnding = sc.nextLine();
            System.out.println("Тип на отпуската (платена/неплатена): ");
            String vacationType = sc.nextLine();
            String status = "изчаква";

            writer.write(requestNumber + "," + name + "," + email + "," + id + "," + dataVacationBeginning + "," + dataVacationEnding + "," + vacationType + "," + status + "\n");
            writer.close();

            System.out.println("Вие успешно заявихте отпуска за " + name);
        } catch (Exception e) {
            System.out.println("Грешка при подаването на заявление за отпуска..");
        }
    }

    public static void viewAllVacations() {
        try {
            File file = new File("vacationsDigitalRazgrad2023.csv");
            Scanner fileReader = new Scanner(file, "utf-8");
            System.out.println("Всички заявени отпуски във фирмата са:");
            Formatter formatter = new Formatter();
            System.out.println(formatter.format("%-15s %-15s %-15s %-10s %-15s %-15s %-15s %-25s\n", "Номер на заявката", "Две имена", "Имейл", "ЕГН", "Начална дата", "Крайна дата", "Тип-платена/неплатена", " Статус"));
            String line = " ";
            while (fileReader.hasNextLine()) {
                line = fileReader.nextLine();
                String[] employeeInfo = line.split(",");
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", employeeInfo[0], employeeInfo[1], employeeInfo[2], employeeInfo[3], employeeInfo[4], employeeInfo[5], employeeInfo[6], employeeInfo[7]);
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Грешка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void viewVacationForEmployee(Scanner sc) {
        try {
            System.out.print("Въведи име на служителя: ");
            String employeeName = sc.nextLine();
            File file = new File("vacationsDigitalRazgrad2023.csv");
            Scanner fileReader = new Scanner(file, "utf-8");

            System.out.println("Отпуски за служител с име " + employeeName + ":");
            Formatter formatter = new Formatter();
            System.out.println(formatter.format("%-15s %-15s %-15s %-10s %-15s %-15s %-15s %-25s\n", "Номер на заявката", "Две имена", "Имейл", "ЕГН", "Начална дата", "Крайна дата", "Тип-платена/неплатена", "Статус"));

            while (fileReader.hasNextLine()) {
                String vacation = fileReader.nextLine();
                String[] vacationDetails = vacation.split(",");

                if (vacationDetails[1].equalsIgnoreCase(employeeName)) {
                    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", vacationDetails[0], vacationDetails[1], vacationDetails[2], vacationDetails[3], vacationDetails[4], vacationDetails[5], vacationDetails[6], vacationDetails[7]);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Невалидни входни данни! Името е грешно или служителя няма заявена отпуска.");
        }
    }

    public static void viewVacationStatus() {
        try {
            File file = new File("vacationsDigitalRazgrad2023.csv");
            Scanner fileReader = new Scanner(file, "utf-8");
            System.out.println("Всички заявени отпуски във фирмата са:");
            Formatter formatter = new Formatter();
            System.out.println(formatter.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "Номер на заявката", "Две имена", "Имейл", "ЕГН", "Начална дата", "Крайна дата", "Тип-платена/неплатена", "Статус"));

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] employeeInfo = line.split(",");
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", employeeInfo[0], employeeInfo[1], employeeInfo[2], employeeInfo[3], employeeInfo[4], employeeInfo[5], employeeInfo[6], employeeInfo[7]);
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Грешка!");
        }
    }

    public static void changeVacationStatus(Scanner sc) {
        viewVacationStatus();
        try {
            System.out.print("Въведи номер на заявката: ");
            String vacationNumber = sc.nextLine();

            System.out.print("Въведи нов статус на отпуската (одобрена/отказана/изчаква): ");
            String newStatus = sc.nextLine();

            File file = new File("vacationsDigitalRazgrad2023.csv");
            Scanner fileReader = new Scanner(file, "utf-8");

            StringBuilder fileContent = new StringBuilder();
            while (fileReader.hasNextLine()) {
                String vacation = fileReader.nextLine();
                String[] vacationDetails = vacation.split(",");

                if (vacationDetails[0].equals(vacationNumber)) {
                    vacationDetails[7] = newStatus;
                    vacation = String.join(",", vacationDetails);
                }

                fileContent.append(vacation).append("\n");
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("vacationsDigitalRazgrad2023.csv"));
            writer.write(fileContent.toString());
            writer.close();

            System.out.println("Статусът на отпуската е успешно променен.");

            fileReader.close();
        } catch (IOException e) {
            System.out.println("Грешка при промяната на статуса на отпуската.");
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int menuNumber;

        do {
            System.out.println();
            System.out.println("Добре дошли в системата за отпуски на фирма DigitalRazgrad 2023. Моля изберете опция от менюто, като въведете число от 1 до 5:");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(" 1. Заяви отпуска\n 2. Виж всички отпуски \n 3. Виж отпуска за служител \n 4. Промени статус на отпуска \n 5. Изход");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Въведи избор:");
            menuNumber = input.nextInt();
            input.nextLine();

            switch (menuNumber) {
                case 1:
                    generateVacation(input);
                    break;
                case 2:
                    viewAllVacations();
                    break;
                case 3:
                    viewVacationForEmployee(input);
                    break;
                case 4:
                    changeVacationStatus(input);
                    break;
                case 5: {
                    System.out.println("Довиждане!");
                    System.exit(0);
                }
                default:
                    System.out.println("Невалиден избор. Опитайте отново.");
            }
        } while (menuNumber != 5);
    }
}