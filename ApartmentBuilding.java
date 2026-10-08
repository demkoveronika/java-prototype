import java.util.ArrayList;
import java.util.List;

public class ApartmentBuilding extends Building {
    // Список власників квартир
    private List<Owner> owners;

    // Конструктор багатоквартирного будинку
    public ApartmentBuilding(double area, int floors, String address) {
        // Передаємо характеристики батьківському класу
        super(area, floors, address);

        // Створюємо порожній список власників
        this.owners = new ArrayList<>();
    }

    // Додавання нового власника до списку
    public void addOwner(Owner owner) {
        owners.add(owner);
    }

    // Отримання списку власників
    public List<Owner> getOwners() {
        return new ArrayList<>(owners);
    }

    // Створення незалежної копії будинку (Prototype)
    @Override
    public Building copy() {
        // Створюємо новий будинок із такими самими характеристиками
        ApartmentBuilding copy = new ApartmentBuilding(
            getArea(),
            getFloors(),
            getAddress()
        );

        // Копіюємо кожного власника окремо
        for (Owner owner : owners) {
            copy.addOwner(owner.copy());
        }

        // Повертаємо готову копію будинку
        return copy;
    }

    // Виведення інформації про багатоквартирний будинок
    @Override
    public void displayInfo() {
        System.out.println("Тип будинку: Багатоквартирний будинок");
        System.out.println("Площа: " + getArea() + " м²");
        System.out.println("Кількість поверхів: " + getFloors());
        System.out.println("Адреса: " + getAddress());

        // Перевіряємо, чи є власники
        if (owners.isEmpty()) {
            System.out.println("Власників поки немає.");
        } else {
            System.out.println("Список власників:");

            // Виводимо кожного власника з порядковим номером
            for (int i = 0; i < owners.size(); i++) {
                System.out.println((i + 1) + ". " + owners.get(i));
            }
        }
    }
}
