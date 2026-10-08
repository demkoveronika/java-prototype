public class Owner {
    // Ім'я власника
    private String name;

    // Номер телефону власника
    private String phone;

    // Конструктор для створення власника
    public Owner(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // Метод для отримання імені
    public String getName() {
        return name;
    }

    // Метод для отримання телефону
    public String getPhone() {
        return phone;
    }

    // Метод для зміни імені
    public void setName(String name) {
        this.name = name;
    }

    // Метод для зміни телефону
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Метод для створення копії власника
    public Owner copy() {
        return new Owner(name, phone);
    }

    // Метод для виведення інформації про власника
    @Override
    public String toString() {
        return name + " (тел.: " + phone + ")";
    }
}
