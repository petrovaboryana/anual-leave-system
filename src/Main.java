import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void generateVacation(Scanner sc){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("vacationsDigitalRazgrad2023.txt", true));
            System.out.println("Моля въведи следната информация.");
            System.out.println("Въведи Две имена:");
            String name = sc.nextLine();
            System.out.println("Имейл:");
            String email = sc.nextLine();
            System.out.println("ЕГН:");
            String id = sc.nextLine();
            System.out.println("Дата за начало на отпуската(ден.месец.година):");
            String dataLeaveBegining = sc.nextLine();
            System.out.println("Дата за край на отпуската(ден.месец.година):");
            String dataLeaveEnding = sc.nextLine();
            System.out.print("Тип на отпуската (платена/неплатена): ");
            String vacationType = sc.nextLine();

            writer.write(name + "," + email + "," + id + "," + dataLeaveBegining + "," + dataLeaveEnding + "," + vacationType + "\n");
            writer.close();

            System.out.println("Вие успешно заявихте своята отпуска.");
        }catch (Exception e){
            System.out.println("Грешка при подаването на заявление за отпуска..");
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
//            case 2:
//                viewAllVacations();
//                break;
//            case 3:
//                viewVacationForEmployee(scanner);
//                break;
//            case 4:
//                changeVacationStatus(scanner);
//                break;
            case 5:
                System.out.println("Довиждане!");
                System.exit(0);
            default:
                System.out.println("Невалиден избор. Опитайте отново.");
                break;
        }
    }
}