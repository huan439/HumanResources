public class Manager extends Staff implements  ICalculator {
    private final String title;

    public Manager(String id, String name, int age, double cS, String doe, String dow, int nLeave, String title) {
        super(id, name, age, cS, doe, dow, nLeave);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int calculateSalary() {
        double responsibilitySalary = switch (title) {
            case "Business Leader" -> 8e6;
            case "Project Leader" -> 5e6;
            case "Technical Leader" -> 6e6;
            default -> 0;
        };
        return  (int)(5e6 * super.getCS() + responsibilitySalary);
    }

    public void displayInformation() {
        System.out.println("Mã nhân viên: " + super.getId() + ", Tên: " + super.getName() + ", Tuổi: " + super.getAge()
                + ", Hệ số lương: " + super.getCS() + ", Ngày vào làm việc: " + super.getDoe() + ", Phòng ban: "
                +  super.getDow() + ", Số ngày nghỉ phép: " + super.getNLeave() + ", Chức danh: " + title + ", Lương: "
                + this.calculateSalary());
    }
}
