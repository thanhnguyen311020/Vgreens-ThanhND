package edu.poly.vgreens.restcontroller.admin;


import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.entity.OrderTrack;
import edu.poly.vgreens.service.OrderService;
import edu.poly.vgreens.service.OrderTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vgreens/rest/ordertrackAdmin")
@CrossOrigin("*")
public class OrderTrackRestController {
    @Autowired
     OrderTrackService orderTrackService;

    @GetMapping("findall")
    public ResponseEntity<List<Order>> findallorderByOrderTrack(){
        return ResponseEntity.ok(orderTrackService.findOrderByShipper());
    }

    @PostMapping("order_shipper/update/{id}/{status}")
    public Response updateOrderStatus(@PathVariable("id") Integer orderId, @PathVariable("status") Integer status, Authentication auth){
           	String name =auth.getName();
    		orderTrackService.updateStatus(orderId,status,name);

            return new Response(orderId,status);
    }

    class Response{
        private  Integer orderId;
        private Integer status;

        public Response(Integer orderId, Integer status) {
            this.orderId = orderId;
            this.status = status;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

}