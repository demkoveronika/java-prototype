public abstract class Building {
    // Площа будинку в квадратних метрах
    private double area;

    // Кількість поверхів
    private int floors;

    // Адреса будинку
    private String address;

    // Конструктор для заповнення характеристик будинку
    public Building(double area, int floors, String address) {
        this.area = area;
        this.floors = floors;
        this.address = address;
    }

    // Отримання площі будинку
    public double getArea() {
        return area;
    }

    // Зміна площі будинку
    public void setArea(double area) {
        this.area = area;
    }

    // Отримання кількості поверхів
    public int getFloors() {
        return floors;
    }

    // Зміна кількості поверхів
    public void setFloors(int floors) {
        this.floors = floors;
    }

    // Отримання адреси
    public String getAddress() {
        return address;
    }

    // Зміна адреси
    public void setAddress(String address) {
        this.address = address;
    }

    // Абстрактний метод для створення копії будинку
    public abstract Building copy();

    // Абстрактний метод для виведення інформації
    public abstract void displayInfo();
}
