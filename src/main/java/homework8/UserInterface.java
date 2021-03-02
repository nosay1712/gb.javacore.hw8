package homework8;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class UserInterface implements IUserInterface {

    IController controller = new Controller();

    @Override
    public void showMenu() {
        while (true) {
            System.out.println(" ");
            System.out.println("Какую информацию Вы хотите получить?");
            System.out.println("\n1 - получение сведений о погоде в определенном городе\n2 - получение архивных данных предыдущих запросов\n \nвведите 'exit' для выхода");

            Scanner scanner = new Scanner(System.in);

            String menuChoice = scanner.nextLine();

            checkIsExit(menuChoice);


            if (menuChoice.equalsIgnoreCase("2")) {
                try {
                    controller.onCommandChosen(3);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Введите название города на латинице:");
                String userResponse = scanner.nextLine();

                try {
                    controller.onCityInput(userResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                System.out.println("Введите команду\n1 - для получения погоды на текущий день\n2 - для получения погоды на 5 дней");


                System.out.println(" ");
                int selectedCommand = scanner.nextInt();

                try {
                    controller.onCommandChosen(selectedCommand);
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    private void checkIsExit(String userResponse) {
        if (userResponse.equalsIgnoreCase("exit") ||
                userResponse.equalsIgnoreCase("выход")) {

            System.out.println("Завершаю работу....");
            System.exit(0);
        }
    }
}
