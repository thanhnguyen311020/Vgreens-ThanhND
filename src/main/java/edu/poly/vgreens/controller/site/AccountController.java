package edu.poly.vgreens.controller.site;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Addresses;
import edu.poly.vgreens.entity.Country;
import edu.poly.vgreens.model.AddressDTO;
import edu.poly.vgreens.service.AccountService;
import edu.poly.vgreens.service.AddressesService;
import edu.poly.vgreens.service.CountryService;

@Controller
@RequestMapping("vgreens")
public class AccountController {
	@Autowired
	private AddressesService addressService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private CountryService countryService;

	@GetMapping("account")
	public String index() {
		return "site/account/account";
	}
	@GetMapping("account/profile")
	public String profile(Model model, Authentication auth) {
		String username = auth.getName();
		Optional<Account> account = accountService.findByUsername(username);
		model.addAttribute("auth", account.get());
		return "site/account/profile";
	}

	@GetMapping("account/address")
	public String address(Model model, Authentication auth) {
		String username = auth.getName();
		System.out.println("username address: " + username);
		List<Addresses> listAddresses = addressService.findAddressesByAccount_Username(username);

		Optional<Account> account = accountService.findByUsername(username);
		boolean usePrimaryAddressAsDefault = true;

		for (Addresses addresses : listAddresses) {
			if (addresses.getAddress_default() == true) {
				usePrimaryAddressAsDefault = false;
				break;
			}
		}

		List<Country> listCountries = countryService.findAllByOrderByNameAsc();

		model.addAttribute("listcountries", listCountries);

		model.addAttribute("account", account.get());
		model.addAttribute("usePrimaryAddressAsDefault", usePrimaryAddressAsDefault);
		model.addAttribute("listaddress", listAddresses);
		model.addAttribute("address", new AddressDTO());
		// System.out.println("listaddress" +listAddresses );
		return "site/account/address";
	}

	@GetMapping("account/address/setdefault/{id}")
	public String setDefaultAddress(@PathVariable("id") Integer addressId, Model model, Authentication auth,
			RedirectAttributes ra) {
		String username = auth.getName();

		addressService.setDefaultAddress(addressId, username);

		ra.addFlashAttribute("success", "Đặt làm địa chỉ giao hàng thành công");
		return "redirect:/vgreens/account/address";
	}

//	@PostMapping("account/address/save")
//	public ModelAndView saveAddress(ModelMap model,@Valid @ModelAttribute("address") AddressDTO dto, BindingResult result,
//			RedirectAttributes ra, Authentication auth ) {
//		String username = auth.getName();
//		List<Addresses> listAddresses = addressService.findAddressesByAccount_Username(username);
//		
//		
//			if (result.hasErrors()) {
//				ra.addFlashAttribute("success", "sai");
//			}
//			else {
//				Account ac = new Account();
//				Country country = new Country();
//				ac.setUsername(username);
//				Addresses entity = new Addresses();
//				BeanUtils.copyProperties(dto, entity);
//				entity.setAccount(ac);
//				entity.setAddress_default(dto.getAddress_default());
//				country.setId(dto.getCountry_id());
//				entity.setCountry(country);
//
//				addressService.save(entity);
//				ra.addFlashAttribute("success", "Lưu địa chỉ thành công");
//
//				return new ModelAndView("redirect:/vgreens/account/address");
//			}
//			
//		
//
//	
//	
//		return new ModelAndView("redirect:/vgreens/account/address", model);
//		
//
//	}

	@GetMapping("account/address/edit/{id}")
	public String editAddress(@PathVariable("id") Integer addressId, Model model, Authentication auth) {
		String username = auth.getName();
		Addresses addresses = addressService.findByIdAndAccount(addressId, username);
		AddressDTO addressDTO = new AddressDTO();
		BeanUtils.copyProperties(addresses, addressDTO);

		List<Country> listCountries = countryService.findAllByOrderByNameAsc();
		model.addAttribute("address", addressDTO);
		model.addAttribute("listcountries", listCountries);
		return "site/account/address_form";
	}

	@GetMapping("account/address/delete/{id}")
	public String deleteAddress(@PathVariable("id") Integer addressId, Model model, RedirectAttributes ra,
			Authentication auth) {
		String username = auth.getName();
		try {
			addressService.deleteById(addressId);
			ra.addFlashAttribute("success", "Xóa địa chỉ thành công");
			return "redirect:/vgreens/account/address";
		} catch (Exception e) {
			// TODO: handle exception
			ra.addFlashAttribute("error", "Xóa địa chỉ không thành công");
			return "redirect:/vgreens/account/address";
		}

	}

	@GetMapping("account/changepassword")
	public String changepassword() {
		return "site/account/changepassword";
	}

}
