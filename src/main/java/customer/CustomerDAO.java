package customer;

import java.util.List;

public interface CustomerDAO {
	public void addCustomer(Customer customer) ;
	public List<Customer> display();
	public void deleteCustomer(int id);
	public void updateCustomer(String name, int id);



}
