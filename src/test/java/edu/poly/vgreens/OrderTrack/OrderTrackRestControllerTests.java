package edu.poly.vgreens.OrderTrack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderTrackRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "vgreendemo", password = "123456789" , authorities = {"DIRE"})
    public void testUpdateOrderStatus() throws Exception{
        Integer orderId = 36;
        Integer status =4;

        String requestURL = "/vgreens/rest/ordertrackAdmin/order_shipper/update/"+orderId+"/"+status;


        mockMvc.perform(post(requestURL).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
