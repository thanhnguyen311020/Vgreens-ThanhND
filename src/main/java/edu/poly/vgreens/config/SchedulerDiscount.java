package edu.poly.vgreens.config;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import edu.poly.vgreens.entity.Discount;
import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.service.DiscountService;
import edu.poly.vgreens.service.ProductService;

@Configuration
@EnableScheduling
public class SchedulerDiscount {

	@Autowired
	DiscountService discountService;
	@Autowired
	ProductService productService;

	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	// chuyển discount về == 0 nếu thời gian discount > hiện tại
	@Scheduled(cron = "0 57-59 19 * * ?") // 19h 57-59m 0s | tất cả các ngày =>Hàm sẽ đc chạy
	public void setDiscountForProductEqualsZeroWhenDicountEndDateLessDateNow() {
		List<Discount> discounts = discountService.findAll();
		for (Discount d : discounts) {
			if(d.getId() == 16 || d.getId() == 17 ) { // với 16-17 là mã không khuyến mãi và khuyến mãi hàng xắp hết hạn
				continue;	
			}
			
//			format.format(d.getEndDate()).compareTo(format.format(new Date())) <= 0
			if (d.getEndDate().before(new Date()) == true) {
				System.out.println("Check: "+(d.getEndDate().before(new Date())));
				
				d.setPercent_discount(0);
				discountService.save(d);
			}
		}

	}

	SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy/MM/dd");
	
	// ẩn sản phẩm khi lô hàng hết hạn sử dụng
	@Scheduled(cron = "0 20-22 23 * * ? ") // 20h 12->14m 0s || tất cả các ngày =>Hàm sẽ đc chạy
	public void disableProductWhenConsignmentExpired() {
		List<Product> products = productService.findAll();
		for (Product p : products) {
			//p.getConsignment().getExpiry_time().before(new Date())
			if (dateFmt.format(p.getConsignment().getExpiry_time()).compareTo(dateFmt.format(new Date())) <= 0) {
				p.setAvailable(false);
				productService.save(p);
			}
		}
	}
	
	
	// thêm discount khi hạn dử dụng còn 10 ngày"
	@Scheduled(cron = "0 35-37 20 * * ?") // 20h 35->37m 0s || tất cả các ngày =>Hàm sẽ đc chạy
	public void setDiscoutProductWhenConsignmentAboutToExpire() {
		List<Product> products = productService.findAll();
		Discount discount = findDisountByNameLiquidation(17); // id là mã khuyến mãi muốn thêm
		if (discount == null) {
			return;
		} else {
			for (Product p : products) {
				// lấy số thời gian còn lại:  (hạn - hiện tại)
				long days = p.getConsignment().getExpiry_time().getTime() - (new Date().getTime());
				TimeUnit time = TimeUnit.DAYS;
				long diffrence = time.convert(days, TimeUnit.MILLISECONDS); // đổi sang số
				if (diffrence <= 10) {
					if(p.getDiscount().getPercent_discount() == 0) {
						p.setDiscount(discount);
						productService.save(p);
					}
				}
			}
		}

	}
	
	// lấy discount thanh lý hàng mặt định là 17 "sửa theo database của mỗi người"
	// không thích thì truy vấn theo tên "thanh lý"
	private Discount findDisountByNameLiquidation(int id) {
		Discount discount = discountService.findById(id).get();
		if (discount == null) {
			return null;
		}
		return discount;
	}
}
