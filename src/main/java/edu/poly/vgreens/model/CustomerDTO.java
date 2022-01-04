package edu.poly.vgreens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable {
	@NotEmpty(message = "Tên tài khoản không được bỏ trống")
	@Length(min = 5, message = "Độ dài tên tài khoản phải lớn hơn 5")
	String username;

	@NotEmpty(message = "Mật khẩu không được bỏ trống")
	@Length(min = 5, message = "Độ dài mật khẩu phải lớn hơn 5")
	String password;
	@NotEmpty(message = "Email không được bỏ trống")
	@Email(message = "Không đúng định dạng Email")
	String email;
	String photo;
	Boolean enable;

	String verification_code;

	Date register_date = new Date();

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
	
	@NotNull(message = "Tỉnh/Thành phố không được bỏ trống")
	Integer country_id;

}
