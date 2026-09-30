import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task3 {

    public static void main(String[] args) {
        String[] names = {"Иванов Иван Иванович",
                "Петр Петрович",
                "Снежная Анастасия Федоровна",
                "Базаров Евгений",
                "Сидоров Михаил Юрьевич"};
        Integer[] ages = {45, 23, 30, 19, 20};
        String[] departments = new Random()
                .ints(5, 1000, 5000)
                .mapToObj(Integer::toString)
                .toArray(String[]::new);
        Double[] salaries = new Random()
                .doubles(5, 50000, 200000)
                .boxed()
                .toArray(Double[]::new);
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Employee employee = new Employee();
            employee.setFullName(names[i]);
            employee.setAge(ages[i]);
            employee.setDepartment(departments[i]);
            employee.setSalary(salaries[i]);
            employees.add(employee);
        }
        boolean filteredEmp = employees.stream().anyMatch(
                emp -> emp.getSalary() > 100000.00
        );
        if (filteredEmp)
            System.out.println("Да, есть");
        else
            System.out.println("Таких сотрудников нет");
    }
}

class Employee {
    private String fullName;
    private Integer age;
    private String department;
    private Double salary;

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
