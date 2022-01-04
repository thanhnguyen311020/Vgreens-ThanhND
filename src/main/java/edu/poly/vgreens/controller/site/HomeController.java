package edu.poly.vgreens.controller.site;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.service.ProductService;


import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.service.AccountService;

@Controller
@RequestMapping("vgreens")
public class HomeController {

    @Autowired
    private ProductService proSer;

    @GetMapping("shop")
    public String shop(Model model, @RequestParam(name = "name", required = false) String name,
                       @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size) {

        //tim kiem theo ten va phan trang
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(16);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
        Page<Product> resultPage = null;

        if (StringUtils.hasText(name)) {
            resultPage = proSer.findProductByNameContainingAndAndAvailableTrue(name, pageable);
            model.addAttribute("name", name);
            model.addAttribute("size", size);
            model.addAttribute("page", page);
        } else {
            resultPage = proSer.findAllByAvailableIsTrue(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > 5) {
                if (end == totalPages) start = end - 5;
                else if (start == 1) end = start + 5;


            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());


            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("shop", "co"); // biet url nay co dang su dung ko
        model.addAttribute("name", name);
        model.addAttribute("currentPage", currentPage);// lay page hien tai
        model.addAttribute("totalpage", totalPages); // lay tong trang
        model.addAttribute("products", resultPage);// lay tong so products


        return "site/home/shop";
    }

    @GetMapping("shop/categories")
    public String shoplistbycategoryid(Model model, @RequestParam("cid") Optional<Integer> cid, @RequestParam(name = "name", required = false) String name,
                                       @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size) {

        ///tim kiem theo ten va phan trang
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(16);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
        Page<Product> resultPage = null;

        if (StringUtils.hasText(name)) {
            resultPage = proSer.findProductByNameContainingAndAndAvailableTrue(name, pageable);
            model.addAttribute("name", name);
        } else {
            Page<Product> list = proSer.findByCategoryId(cid.get(), pageable);
            resultPage = list;
        }


        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > 5) {
                if (end == totalPages) start = end - 5;
                else if (start == 1) end = start + 5;


            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());


            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("cates", "co");
        model.addAttribute("currentPage", currentPage);// lay page hien tai
        model.addAttribute("totalpage", totalPages); // lay tong trang
        model.addAttribute("products", resultPage);
        model.addAttribute("cid", cid.get());


        return "site/home/shop";


    }


    @GetMapping("shop/findbyprice")
    public String shopfindbyprice(ModelMap model, @RequestParam(name = "name", required = false) String name,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size,
                                  @RequestParam("selling_price1") Float price1, @RequestParam("selling_price2") Float price2
    ) {

        ///tim kiem theo ten va phan trang
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(16);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Product> resultPage = null;

        if (!StringUtils.hasText(name)) {
            Page<Product> list = proSer.findProductByPriceBetwen(price1, price2, pageable);
            resultPage = list;
            model.addAttribute("price1",price1);
            model.addAttribute("price2",price2);


        } else {
            resultPage = proSer.findAllByAvailableIsTrue(pageable);

        }


        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > 5) {
                if (end == totalPages) start = end - 5;
                else if (start == 1) end = start + 5;


            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());


            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("findbyprice", "co");
        model.addAttribute("currentPage", currentPage);// lay page hien tai
        model.addAttribute("totalpage", totalPages); // lay tong trang
        model.addAttribute("products", resultPage);
		model.addAttribute("price1",price1);
		model.addAttribute("price2",price2);
        model.addAttribute("name", name);


        return "site/home/shop";


    }

    @GetMapping("shop/filter")
    public String sapxeptiengiamdan(Model model, @RequestParam(name = "filter", required = true) String filter,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size) {

        //tim kiem theo ten va phan trang
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(100);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Product> resultPage = null;

        if (Objects.equals(filter, "sapxep-tiengiamdan")) {
            resultPage = proSer.findProductBySellingPriceDesc(pageable);
        }
        else if (Objects.equals(filter, "sapxep-tientangdan")) {
            resultPage = proSer.findProductBySellingPriceAsc(pageable);
        }
        else if (Objects.equals(filter, "sapxep-tenAtoZ")) {
            resultPage = proSer.findProductOrderByNameAsc(pageable);
        }
        else if (Objects.equals(filter, "sapxep-tenZtoA")) {
            resultPage = proSer.findProductOrderByNameDesc(pageable);
        }
        else if (Objects.equals(filter, "sapxep-sanphamkhuyenmai")) {
            resultPage = proSer.findProductByDiscountIsNotNullAndAndAvailableIsTrue(pageable);
        }

        else {
            resultPage = proSer.findAllByAvailableIsTrue(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > 5) {
                if (end == totalPages) start = end - 5;
                else if (start == 1) end = start + 5;


            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());


            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("filter", "co"); // biet url nay co dang su dung ko
        model.addAttribute("currentPage", currentPage);// lay page hien tai
        model.addAttribute("totalpage", totalPages); // lay tong trang
        model.addAttribute("products", resultPage);// lay tong so products
        model.addAttribute("filters",filter);

        return "site/home/shop";

    }

    
    @GetMapping("shop/sale/{id}")
    public String shop(Model model,@PathVariable("id") Integer id ,
                       @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size) {

        //tim kiem theo ten va phan trang
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(16);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
        Page<Product> resultPage = null;

        
         resultPage = proSer.findProductByDiscountId(id,pageable);
        

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > 5) {
                if (end == totalPages) start = end - 5;
                else if (start == 1) end = start + 5;


            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());


            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("sale", "co"); // biet url nay co dang su dung ko
    
        model.addAttribute("currentPage", currentPage);// lay page hien tai
        model.addAttribute("totalpage", totalPages); // lay tong trang
        model.addAttribute("products", resultPage);// lay tong so products


        return "site/home/shop";
    }
    

}
