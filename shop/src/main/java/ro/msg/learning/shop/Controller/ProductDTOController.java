package ro.msg.learning.shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.Model.DTO.ProductDTO;
import ro.msg.learning.shop.Model.Product;
import ro.msg.learning.shop.Model.ProductCategory;
import ro.msg.learning.shop.Model.Supplier;
import ro.msg.learning.shop.Persistence.ProductCategoryRepository;
import ro.msg.learning.shop.Persistence.SupplierRepository;
import ro.msg.learning.shop.Service.ProductServiceImpl;

import java.util.List;

@RestController
public class ProductDTOController {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/products")
    List<ProductDTO> findAll() {
        return productService.getProducts();
    }

    @PostMapping("/products")
    ProductDTO save(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/products/{id}")
    ProductDTO findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    ProductDTO update(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/products/{id}")
    void delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

//    @GetMapping("/categs")
//    List<ProductCategory> findCategs(){
//        return productCategoryRepository.findAll();
//    }
//    @GetMapping("/suppliers")
//    List<Supplier> findSuppliers(){
//        return supplierRepository.findAll();
//    }
}
