abstract class Employee {
    protected String employeeID;
    public abstract String getFullInfo();

    public Employee(String employeeID) {
        this.employeeID = employeeID;
    }
}

class SalariedEmployee extends Employee {
    protected String socialSecurityNumber;

    public SalariedEmployee(String employeeID, String socialSecurityNumber) {
        super(employeeID);
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public String getFullInfo() {
        return String.format("{ID: %s, SSN: %s}", employeeID, socialSecurityNumber);
    }
}

class ContractEmployee extends Employee {
    protected String federalTaxIDMember;

    public ContractEmployee(String employeeID, String federalTaxIDMember) {
        super(employeeID);
        this.federalTaxIDMember = federalTaxIDMember;
    }

    @Override
    public String getFullInfo() {
        return String.format("{ID: %s, TaxID: %s}", employeeID, federalTaxIDMember);
    }
}
