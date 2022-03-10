public class Employee extends  Staff implements ICalculator {
    private final int  overtime; // number of overtime hours

    public Employee(String id, String name, int age, double cS, String doe, String dow, int nLeave, int overtime) {
        super(id, name, age, cS, doe, dow, nLeave);
        this.overtime = overtime;
    }

    public int getOvertime() {
        return overtime;
    }

    @Override
    public int calculateSalary() {
        return  (int)(3e6 * super.getCS() + 2e5 * overtime);
    }

    public void displayInformation() {
        System.out.println("Mã nhân viên: " + super.getId() + ", Tên: " + super.getName() + ", Tuổi: " + super.getAge()
                + ", Hệ số lương: " + super.getCS() + ", Ngày vào làm việc: " + super.getDoe() + ", Phòng ban: "
                +  super.getDow() + ", Số ngày nghỉ phép: " + super.getNLeave() + ",Số giờ làm thêm: " + overtime
                + ", Lương: " + this.calculateSalary());
    }
}
