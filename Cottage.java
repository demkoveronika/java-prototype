public class Cottage extends Building {
    // Власник котеджу
    private Owner owner;

    // Конструктор для створення котеджу
    public Cottage(double area, int floors, String address, Owner owner) {
        // Передаємо характеристики батьківському класу Building
        super(area, floors, address);

        // Зберігаємо власника котеджу
        this.owner = owner;
    }

    // Отримання власника
    public Owner getOwner() {
        return owner;
    }

    // Зміна власника
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    // Створення незалежної копії котеджу (патерн Prototype)
    @Override
    public Building copy() {
        return new Cottage(
            getArea(),
            getFloors(),
            getAddress(),
            owner.copy()
        );
    }

    // Виведення інформації про котедж
    @Override
    public void displayInfo() {
        System.out.println("Тип будинку: Котедж");
        System.out.println("Площа: " + getArea() + " м²");
        System.out.println("Кількість поверхів: " + getFloors());
        System.out.println("Адреса: " + getAddress());
        System.out.println("Власник: " + owner);
    }
}
