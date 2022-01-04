package edu.poly.vgreens.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @Column(length = 255)
    String username;
    
    @Column( nullable = false)
    String password;
    
    @Column( nullable = false)
    String email;
    
    String photo;
    
    Boolean enable;
    
    @Column(length = 64)
    String verification_code;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Register_date")
    Date register_date = new Date();
    

    @Column(length = 45, nullable = false)
    String first_name;
    
    @Column(length = 45, nullable = false)
    String last_name;
    
    @Column(length = 15, nullable = true)
    String phone_number;
    
    @Column(length = 64, nullable = true)
    String address_line;

    @Column(length = 45, nullable = true)
    String city;
    
    @Column(length = 45, nullable = false)
    String state;
    
    @Column(length = 10, nullable = true)
    String postal_code;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    List<Authority> authorities;

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Addresses> addresses;

    // tạo mã thông báo đặt lại mật khẩu ngẫu nhiên
    @Column(name = "reset_password_token", nullable = true)
    String resetPasswordToken;
    // thời gian hết hạn mật khẩu
    @Column(name = "password_changed_time", nullable = true)
    Date passwordChangedTime;
    private static final long PASSWORD_EXPIRATION_TIME = 30L * 24L * 60L * 60L * 1000L; // 30 days
    // sử dụng để kiểm tra xem mật khẩu của người dùng có hết hạn hay không.

    public boolean isPasswordExpired() {
        if (this.passwordChangedTime == null)
            return false;

        long currentTime = System.currentTimeMillis();
        long lastChangedTime = this.passwordChangedTime.getTime();

        return currentTime > lastChangedTime + PASSWORD_EXPIRATION_TIME;
    }
    
    @Transient
    String img;
}
