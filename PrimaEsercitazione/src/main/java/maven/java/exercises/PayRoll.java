package maven.java.exercises;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Federico on 12/10/2016.
 */
public class PayRoll {


    final static Logger LOGGER = Logger.getLogger(PayRoll.class);

    private EmployeeDB db;
    private BankService bankService;
    public PayRoll(EmployeeDB db, BankService bankService) {
        this.db = db;

        this.bankService = bankService;
    }

    public int monthlyPayments() {
        List<Employee> employees = db.getAllEmployees();
        for (Employee employee: employees) {
            try {
                bankService.makePayment(employee.getBankId(), employee.getSalary());
                employee.setPaid(true);
            }
            catch (RuntimeException e) {
                employee.setPaid(false);
                LOGGER.error("ERROR IN CLASS PAYROLL", e);

            }
        }
        return employees.size();

    }
}
