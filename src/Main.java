import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class Main {
    public static void generateVacation(Scanner sc) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("vacationsDigitalRazgrad2023.csv", true));
            System.out.println("Моля въведи следната информация.");
            System.out.println("Две имена:");
            String name = sc.nextLine();
            System.out.println("Имейл:");
            String email = sc.nextLine();
            System.out.println("ЕГН:");
            String id = sc.nextLine();
            System.out.println("Дата за начало на отпуската(ден.месец.година):");
            String dataLeaveBeginning = sc.nextLine();
            System.out.println("Дата за край на отпуската(ден.месец.година):");
            String dataLeaveEnding = sc.nextLine();
            System.out.print("Тип на отпуската (платена/неплатена): ");
            String vacationType = sc.nextLine();

            writer.write(name + "," + email + "," + id + "," + dataLeaveBeginning + "," + dataLeaveEnding + "," + vacationType + "\n");
            writer.close();

            System.out.println("Вие успешно заявихте своята отпуска.");
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
            System.out.println(formatter.format("%-15s %-15s %-15s %-15s %-15s %-15s\n", "Две имена", "Имейл", "ЕГН", "Начална дата", "Крайна дата", "Тип-платена/неплатена"));

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] employeeInfo = line.split(",");
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", employeeInfo[0], employeeInfo[1], employeeInfo[2], employeeInfo[3], employeeInfo[4], employeeInfo[5]);
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Грешка!");
        }
    }

    public static void viewVacationForEmployee(Scanner sc) {
        try {
            System.out.print("Въведи ЕГН на служителя: ");
            String egn = sc.nextLine();
            File file = new File("vacationsDigitalRazgrad2023.csv");
            Scanner fileReader = new Scanner(file, "utf-8");

            System.out.println("Отпуски за служител с ЕГН " + egn + ":");
            Formatter formatter = new Formatter();
            System.out.println(formatter.format("%-15s %-15s %-15s %-15s %-15s %-15s\n", "Две имена", "Имейл", "ЕГН", "Начална дата", "Крайна дата", "Тип-платена/неплатена"));

            while (fileReader.hasNextLine()) {
                String vacation = fileReader.nextLine();
                String[] vacationDetails = vacation.split(",");

                if (vacationDetails[2].equals(egn)) {
                    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", vacationDetails[0], vacationDetails[1], vacationDetails[2], vacationDetails[3], vacationDetails[4], vacationDetails[5]);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Невалидни входни данни!");
        }
    }

    public static void main(String[] args) {
        System.out.println("Добре дошли в системата за отпуски на фирма DigitalRazgrad 2023. Моля изберете опция от менюто, като въведете число от 1 до 5:");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" 1. Заяви отпуска\n 2. Виж всички отпуски \n 3. Виж отпуска за служител \n 4. Промени статус на отпуска \n 5. Изход");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        Scanner input = new Scanner(System.in);

        System.out.println("Въведи избор:");
        int menuNumber = input.nextInt();
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
//            case 4:
//                changeVacationStatus(input);
//                break;
            case 5: {
                System.out.println("Довиждане!");
                System.exit(0);
            }
            default:
                System.out.println("Невалиден избор. Опитайте отново.");
        }
    }
}