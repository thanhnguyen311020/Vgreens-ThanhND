// package edu.poly.vgreens.controller.site;

// import java.util.Date;
// import java.util.Optional;

// import javax.servlet.http.HttpServletRequest;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

// import edu.poly.vgreens.entity.OrderDetail;
// import edu.poly.vgreens.entity.Product;
// import edu.poly.vgreens.entity.Review;
// import edu.poly.vgreens.service.OrderDetailService;
// import edu.poly.vgreens.service.OrderService;
// import edu.poly.vgreens.service.ReviewService;
// import org.springframework.web.bind.annotation.GetMapping;

// @Controller
// @RequestMapping("vgreens")
// public class ReviewController {
//     @Autowired
//     private OrderDetailService orderDetailService;
//     @Autowired
//     ReviewService reviewService;

//     @Autowired
//     OrderService orderService;

//     @GetMapping(value = "/product/{id}")
//     public String getMethod(@ModelAttribute("review") Review entity,@PathVariable("id") Integer id, Authentication auth, Model model) {
//     String username = auth.getName();
//     System.out.println(username);
//     OrderDetail od = orderDetailService.findOrderDetailByProductId(id);
//     Optional<Review> review = reviewService.findByUsernameAndProduct(username,
//     id);
//     model.addAttribute("rev", review);
//     System.out.println(review);
//     return "redirect:/vgreens/orderdetail/" + od.getOrder().getId();
//     }

//     @RequestMapping(value = "/product/{id}",method = RequestMethod.POST)
//     public String postRating(@ModelAttribute("review") Review entity, @PathVariable("id") Integer id,
//             HttpServletRequest request, Model model, Authentication auth) {
//         String username = auth.getName();
//         OrderDetail od = orderDetailService.findOrderDetailByProductId(id);
//         // Optional<Review> review = reviewService.findByUsernameAndProduct(username, id);

//         // if (review.isEmpty()) {
//             Product product = new Product();
//             product.setId(id);
//             entity.setProduct(product);
//             entity.setCreated_time(new Date());
//             entity.setTitle("Review");
//             entity.setUpdated_time(new Date());
//             entity.setUsername(username);
//             reviewService.save(entity);
//             // return "redirect:/vgreens/index";
//         //     return "redirect:/vgreens/orderdetail/" + od.getOrder().getId();
//         // } else {
//             // model.addAttribute("reviewPro", review);
//             return "redirect:/vgreens/orderdetail/" + od.getOrder().getId();
//         // }

//     }

// }
