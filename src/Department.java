public class Department {
    private final int id; // department ID
    private final String name; // department name
    private int number = 1; // current number of employees

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Tăng số lượng nhân viên lên 1
    public void increaseNumber() {
        number++;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Mã phòng ban: " + id + ", Tên phòng ban: " + name + ", Số lượng nhân viên: " + number;
    }
}
