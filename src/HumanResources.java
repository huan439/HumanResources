import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class HumanResources {
    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Manager> managers = new ArrayList<>();
    public static ArrayList<Department> departments = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static int menu = 1;

    //Main
    public static void main(String[] args) {

        while (menu != 0) {
            displayMenu(); // Hiển thị menu
            menu = sc.nextInt();
            // Switch menu
            switch (menu) {
                case 1 -> displayListOfEmployees();
                case 2 -> displayDepartments();
                case 3 -> displayEmployeesByDepartments();
                case 4 -> addStaff();
                case 5 -> searchByNameOrId();
                case 6 -> displaySalary();
                case 7 -> displaySalaryByOrder();
            }
            if(menu != 4 && menu != 0) {
                selection();
            }
        }
    }

    // Hiển thị menu
    public static void displayMenu() {
        System.out.println("\n:::::::::::::  Mời bạn chọn chức năng  :::::::::::::");
        System.out.println("1 - Hiển thị danh sách nhân viên hiện có trong công ty.");
        System.out.println("2 - Hiển thị các bộ phận trong công ty.");
        System.out.println("3 - Hiển thị các nhân viên theo từng bộ phận.");
        System.out.println("4 - Thêm nhân viên mới vào công ty.");
        System.out.println("5 - Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên.");
        System.out.println("6 - Hiển thị bảng lương của nhân viên toàn công ty.");
        System.out.println("7 - Hiển thị bảng lương của nhân viên theo thứ tự tăng/giảm dần.");
        System.out.println("0 - Thoát.");
        System.out.print("Bạn chọn: ");
    }

    // Hiển thị danh sách nhân viên
    public static void displayListOfEmployees() {
        System.out.println("\nThông tin các nhân viên hiện có trong công ty:");
        for (Employee employee : employees) employee.displayInformation();
        for (Manager manager : managers) manager.displayInformation();
    }

    // Hiển thị danh sách phòng ban
    public static void displayDepartments() {
        System.out.println("\nThông tin các bộ phận có trong công ty:");
        for(Department department : departments)
            System.out.println(department.toString());
    }

    // Hiển thị danh sách nhân viên theo Phòng ban
    public static void displayEmployeesByDepartments() {
        for (Department department : departments) {
            System.out.println("\nPhòng ban " + department.getName() + " :");
            for (Employee employee : employees)
                if (employee.getDow().equals(department.getName()))
                    employee.displayInformation();
            for (Manager manager : managers)
                if (manager.getDow().equals(department.getName()))
                    manager.displayInformation();
        }
    }

    // Thêm nhân viên
    public static void addStaff() {
        int command = 2;
        while (command == 2) {
            System.out.print("\nNhập mã nhân viên: ");
            String id = sc.next();
            sc.nextLine();
            System.out.print("Nhập tên: ");
            String name = sc.nextLine();
            System.out.print("Nhập tuổi: ");
            int age = sc.nextInt();
            System.out.print("Nhập hệ số lương: ");
            double cS = sc.nextDouble(); // coefficients salary
            sc.nextLine();
            System.out.print("Nhập ngày vào làm việc: ");
            String doe = sc.nextLine(); // date of employment
            System.out.print("Nhập phòng ban làm việc: ");
            String dow = sc.nextLine(); // department of work

            // Kiểm tra nếu tên phòng ban vừa nhập đã có thì tăng số lượng nhân viên lên 1
            // Ngược lại tạo thêm phòng ban mới
            boolean check = false;
            for (Department department : departments)
                if(department.getName().equals(dow)) {
                    department.increaseNumber();
                    check = true;
                    break;
                }
            if (!check)
                departments.add(new Department(departments.size() + 1, dow));

            System.out.print("Nhập số ngày nghỉ phép: ");
            int nLeave = sc.nextInt(); // number of days of leave
            System.out.print("Employee or Manager (E or M)? ");
            String staff = sc.next();

            // Nếu là nhân viên thường thì nhập thêm số giờ làm thêm
            // Ngược lại nhập chức danh
            if (staff.equals("E") || staff.equals("e")) {
                System.out.print("Nhập số giờ làm thêm: ");
                int overtime = sc.nextInt();
                employees.add(new Employee(id, name, age, cS, doe, dow, nLeave, overtime));
            } else {
                sc.nextLine();
                System.out.print("Nhập chức danh (Business Leader - Project Leader - Technical Leader): ");
                String title = sc.nextLine();
                managers.add(new Manager(id, name, age, cS, doe, dow, nLeave, title));
            }

            // Hiển thị menu chọn
            System.out.print("2 - Tiếp tục\n1 - Trở lại\n0 - Thoát\nBạn chọn: ");
            command = sc.nextInt();
            if(command == 0)
                menu = 0;
        }
    }

    // Tìm kiếm theo tên hoặc Mã nhân viên
    public static void searchByNameOrId() {
        System.out.print("\nNhập tên hoặc mã nhân viên cần tìm kiếm: ");
        sc.nextLine();
        String search = sc.nextLine();
        for (Employee employee : employees)
            if (employee.getId().equals(search) || employee.getName().equals(search))
                employee.displayInformation();
        for (Manager manager : managers)
            if (manager.getId().equals(search) || manager.getName().equals(search))
                manager.displayInformation();
    }

    // Hiển thị Bảng lương
    public static void displaySalary() {
        System.out.println("\nBảng lương công ty:");
        for (Employee employee : employees)
            System.out.println("Mã nhân viên: " + employee.getId() + ", Tên: " + employee.getName() + ", Số giờ làm thêm: " + employee.getOvertime() + ", Lương: " + employee.calculateSalary());
        for (Manager manager : managers)
            System.out.println("Mã nhân viên: " + manager.getId() + ", Tên: " + manager.getName() + ", Chức danh: " + manager.getTitle() + ", Lương: " + manager.calculateSalary());
    }

    // Hiển thị bảng lương có sắp xếp
    public static void displaySalaryByOrder() {
        // Tạo ArrayList salaryList lưu lương của toàn công ty
        ArrayList<Integer> salaryList = new ArrayList<>();
        for (Employee employee : employees) salaryList.add(employee.calculateSalary());
        for (Manager manager : managers)  salaryList.add(manager.calculateSalary());
        // Sắp xếp tăng dần
        Collections.sort(salaryList);
        System.out.print("\n2 - Tăng dần\n1 - Giảm dần\nBạn chọn: ");
        int command =sc.nextInt();
        boolean[] checkEmployee = new boolean[employees.size()];
        boolean[] checkManager = new boolean[managers.size()];
        // Nếu lựa chọn Giảm dần thì reverse lại
        if(command == 1)
            Collections.reverse(salaryList);
        // Duyệt từng phần tử trong salaryList
        // Kiểm tra từng phần tử trong employees và managers nếu trùng lương thì in ra
        for (Integer salary : salaryList) {
            boolean check = false;
            for (int i = 0; i < employees.size(); i++) {
                if(employees.get(i).calculateSalary() == salary && !checkEmployee[i]) {
                    employees.get(i).displayInformation();
                    checkEmployee[i] = true;
                    check = true;
                    break;
                }
            }
            if(!check)
                for (int i = 0; i < managers.size(); i++) {
                    if (managers.get(i).calculateSalary() == salary && !checkManager[i]) {
                        managers.get(i).displayInformation();
                        checkManager[i] = true;
                        break;
                    }
                }
        }
    }

    // Lựa chọn tiếp theo Trở lại hoặc Thoát
    public static void selection() {
        System.out.print("\n1 - Trở lại\n0 - Thoát\nBạn chọn: ");
        menu = sc.nextInt();
    }
}
