import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Список для зберігання всіх будинків
    private static List<Building> buildings = new ArrayList<>();

    // Scanner для введення даних користувачем
    private static Scanner scanner = new Scanner(System.in);

    // Головний метод, з якого починається програма
    public static void main(String[] args) {
        int choice;

        // Меню працює, поки користувач не вибере вихід
        do {
            System.out.println("\n===== МЕНЮ БУДИНКІВ =====");
            System.out.println("1. Додати котедж");
            System.out.println("2. Додати багатоквартирний будинок");
            System.out.println("3. Переглянути всі будинки");
            System.out.println("4. Створити копію будинку");
            System.out.println("5. Редагувати будинок");
            System.out.println("0. Вийти");
            System.out.print("Ваш вибір: ");

            // Перевіряємо, чи введено ціле число
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Помилка! Введіть число.");
                scanner.nextLine();
                choice = -1;
            }

            // Виконуємо дію відповідно до вибору
            switch (choice) {
                case 1:
                    addCottage();
                    break;
                case 2:
                    addApartmentBuilding();
                    break;
                case 3:
                    showBuildings();
                    break;
                case 4:
                    cloneBuilding();
                    break;
                case 5:
                    editBuilding();
                    break;
                case 0:
                    System.out.println("Програму завершено.");
                    break;
                default:
                    System.out.println("Невідомий пункт меню.");
            }

        } while (choice != 0);

        scanner.close();
    }

    // Метод для додавання котеджу
    private static void addCottage() {
        System.out.println("\n--- Новий котедж ---");

        double area = readPositiveDouble("Площа (м²): ");
        int floors = readPositiveInt("Кількість поверхів: ");
        String address = readText("Адреса: ");

        String name = readText("Ім'я власника: ");
        String phone = readText("Телефон власника: ");

        Owner owner = new Owner(name, phone);

        // Створюємо котедж і додаємо його до списку
        buildings.add(new Cottage(area, floors, address, owner));

        System.out.println("Котедж успішно додано!");
    }

    // Метод для додавання багатоквартирного будинку
    private static void addApartmentBuilding() {
        System.out.println("\n--- Новий багатоквартирний будинок ---");

        double area = readPositiveDouble("Площа (м²): ");
        int floors = readPositiveInt("Кількість поверхів: ");
        String address = readText("Адреса: ");

        ApartmentBuilding building =
            new ApartmentBuilding(area, floors, address);

        int count = readNonNegativeInt("Кількість власників: ");

        // Додаємо власників по одному
        for (int i = 0; i < count; i++) {
            System.out.println("Власник №" + (i + 1));

            String name = readText("Ім'я: ");
            String phone = readText("Телефон: ");

            building.addOwner(new Owner(name, phone));
        }

        buildings.add(building);
        System.out.println("Багатоквартирний будинок додано!");
    }

    // Метод для перегляду всіх будинків
    private static void showBuildings() {
        if (buildings.isEmpty()) {
            System.out.println("Список будинків порожній.");
            return;
        }

        for (int i = 0; i < buildings.size(); i++) {
            System.out.println("\nБудинок №" + (i + 1));
            buildings.get(i).displayInfo();
        }
    }

    // Метод копіювання будинку за патерном Prototype
    private static void cloneBuilding() {
        if (buildings.isEmpty()) {
            System.out.println("Немає будинків для копіювання.");
            return;
        }

        showBuildings();

        int index = readPositiveInt("Номер будинку для копіювання: ") - 1;

        if (index < 0 || index >= buildings.size()) {
            System.out.println("Будинку з таким номером немає.");
            return;
        }

        // Викликаємо метод copy() потрібного будинку
        Building copiedBuilding = buildings.get(index).copy();

        // Додаємо незалежну копію до списку
        buildings.add(copiedBuilding);

        System.out.println("Копію будинку успішно створено!");
    }

    // Метод редагування характеристик будинку
    private static void editBuilding() {
        if (buildings.isEmpty()) {
            System.out.println("Немає будинків для редагування.");
            return;
        }

        showBuildings();

        int index = readPositiveInt("Номер будинку для редагування: ") - 1;

        if (index < 0 || index >= buildings.size()) {
            System.out.println("Будинку з таким номером немає.");
            return;
        }

        Building building = buildings.get(index);

        // Змінюємо характеристики вибраного будинку
        building.setArea(readPositiveDouble("Нова площа (м²): "));
        building.setFloors(readPositiveInt("Нова кількість поверхів: "));
        building.setAddress(readText("Нова адреса: "));

        // Якщо це котедж, дозволяємо змінити його власника
        if (building instanceof Cottage) {
            Cottage cottage = (Cottage) building;

            String name = readText("Нове ім'я власника: ");
            String phone = readText("Новий телефон власника: ");

            cottage.setOwner(new Owner(name, phone));
        }

        // Якщо це багатоквартирний будинок,
        // додаємо можливість редагувати власників
        if (building instanceof ApartmentBuilding) {
            ApartmentBuilding apartment = (ApartmentBuilding) building;

            List<Owner> owners = apartment.getOwners();

            if (!owners.isEmpty()) {
                System.out.println("Власники квартир:");

                for (int i = 0; i < owners.size(); i++) {
                    System.out.println((i + 1) + ". " + owners.get(i));
                }

                int ownerIndex = readNonNegativeInt(
                    "Номер власника для редагування (0 - пропустити): "
                );

                if (ownerIndex > 0 && ownerIndex <= owners.size()) {
                    Owner owner = owners.get(ownerIndex - 1);

                    owner.setName(readText("Нове ім'я: "));
                    owner.setPhone(readText("Новий телефон: "));
                } else if (ownerIndex != 0) {
                    System.out.println("Власника з таким номером немає.");
                }
            }
        }

        System.out.println("Редагування завершено!");
    }

    // Безпечне введення додатного цілого числа
    private static int readPositiveInt(String message) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();

                if (value > 0) {
                    return value;
                }
            } else {
                scanner.nextLine();
            }

            System.out.println("Помилка! Введіть ціле число більше 0.");
        }
    }

    // Безпечне введення невід'ємного цілого числа
    private static int readNonNegativeInt(String message) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();

                if (value >= 0) {
                    return value;
                }
            } else {
                scanner.nextLine();
            }

            System.out.println("Помилка! Введіть ціле число від 0.");
        }
    }

    // Безпечне введення додатного дробового числа
    private static double readPositiveDouble(String message) {
        while (true) {
            System.out.print(message);

            String input = scanner.nextLine().trim().replace(',', '.');

            try {
                double value = Double.parseDouble(input);

                if (Double.isFinite(value) && value > 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Якщо введено не число, повторюємо запит
            }

            System.out.println("Помилка! Введіть число більше 0.");
        }
    }

    // Введення непорожнього тексту
    private static String readText(String message) {
        while (true) {
            System.out.print(message);

            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Помилка! Поле не може бути порожнім.");
        }
    }
}
