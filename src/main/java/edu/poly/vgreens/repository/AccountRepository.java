package edu.poly.vgreens.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.vgreens.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	public Account findByEmail(String email);

	@Query("SELECT a FROM Account a WHERE a.username = ?1")
	Optional<Account> findByUsername(String username);

	@Query("SELECT a FROM Account a WHERE a.verification_code = ?1")
	public Account findByVerificationCode(String code);
	
	@Query("SELECT a FROM Account a WHERE a.enable =?1")
	List<Account> getAccountByAvailabel(Boolean status);

	public Account findByResetPasswordToken(String token);

	@Query("SELECT DISTINCT ar.account from Authority ar WHERE ar.role.id IN ('DIRE','STAF','SHIP') AND ar.account.username != 'root'")
	public List<Account> getAdministrators();
}
