package beneficiary;

import java.util.List;

public interface BeneficiaryDAO {
	public int addBeneficiary(Beneficiary beneficiary) ;
	public List<Beneficiary> displayBeneficiary();
	public void updateBeneficiary(String beneficiaryName,long accNo) ;
	public int deleteBeneficiary(long accNo) ;
	public List<Beneficiary> searchByBeneficiaryName(String name); 
	public List<Beneficiary> displayParBeneficiary(long cusAccNo) ;
}
