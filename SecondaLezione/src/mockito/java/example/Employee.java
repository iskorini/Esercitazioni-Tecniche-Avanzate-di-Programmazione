package mockito.java.example;

/**
 * Created by Federico on 12/10/2016.
 */
public class Employee {
    private  String name;
    private  String bankId;
    private  int salary;
    private boolean isPaid;

    public Employee(String name, String bankId, int salary) {

        this.name = name;
        this.bankId = bankId;
        this.salary = salary;
    }

    public Employee() {

    }

    public int getSalary() {
        return salary;
    }

    public String getBankId() {
        return bankId;
    }

    public String getName() {
        return name;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
