package edu.poly.vgreens.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.vgreens.entity.Addresses;

@Repository
public interface AddressesRepository extends JpaRepository<Addresses, Integer> {

	  List<Addresses> findAddressesByAccount_Username(String username);

	    @Query("SELECT  a from Addresses a WHERE a.id =?1 and  a.account.username = ?2")
	    public Addresses findByIdAndAccount (Integer addressesId, String username);

	    @Query("Delete from Addresses a where a.id=?1 and a.account.username =?2")
	    public void  deleteByIdAndAccount(Integer addressesId,String username);

	    Addresses  deleteAddressesByIdAndAccount_Username(Integer id, String account_username);

	    @Query("SELECT  a from Addresses a WHERE a.address_default=true and a.account.username = ?1")
	    public Optional<Addresses>  findAddressDefault(String username);
	    
	    @Query("UPDATE Addresses a SET a.address_default= true WHERE a.id =?1")
	    @Modifying
	    @Transactional
	    public void setDefaultAddress(Integer addressdefaultId);
	    
	    
	    @Query("UPDATE Addresses a SET a.address_default =false WHERE a.id !=?1 AND a.account.username = ?2")
	    @Modifying
	    @Transactional
	    public void setNonDefaultForOthers(Integer addressdefaultId, String username);
}
