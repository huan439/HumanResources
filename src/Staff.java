public abstract class Staff {
    private final String id; // mã nhân viên
    private final String name;
    private final int age;
    private final double cS; // coefficients salary
    private final String doe; // date of employment
    private final String dow; // department of work
    private final int nLeave; // number of days of leave

    public Staff(String id, String name, int age, double cS, String doe, String dow, int nLeave) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.cS = cS;
        this.doe = doe;
        this.dow = dow;
        this.nLeave = nLeave;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getCS() {
        return cS;
    }

    public String getDoe() {
        return doe;
    }

    public String getDow() {
        return dow;
    }

    public int getNLeave() {
        return nLeave;
    }

    public abstract void displayInformation();
}
