package edu.poly.vgreens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO  implements Serializable {
	
 

	Integer id;
	@NotEmpty(message = "Tên người dùng không được bỏ trống")
    String first_name;
	@NotEmpty(message = "Tên người dùng không được bỏ trống")
    String last_name;
   
	@NotEmpty(message = "Số điện thoại không được bỏ trống")
	@Size(max = 10, min = 10, message = "Số điện thoại phải có 10 chữ số")
	@Pattern(regexp = "[0-9][0-9]{9}", message = "Số điện thoại không hợp lệ")
    String phone_number;
	@NotEmpty(message = "Địa chỉ không được bỏ trống")
    String address_line;
   
  
    String city;
    @NotEmpty(message = "Quận/Huyện không được bỏ trống")
    String state;
    
    @NotEmpty(message = "Mã bưu điện không được bỏ trống")
    String postal_code;
	@NotNull(message = "Tỉnh/Thành phố không được bỏ trống")
    Integer country_id;
    
    Boolean address_default = false ;
    
   
}
