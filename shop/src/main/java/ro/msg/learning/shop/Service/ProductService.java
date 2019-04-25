package ro.msg.learning.shop.Service;

import ro.msg.learning.shop.Model.DTO.ProductDTO;
import ro.msg.learning.shop.Model.Product;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO product);
    ProductDTO updateProduct(Integer id, ProductDTO product);
    void deleteProduct(Integer id);
    ProductDTO findById(Integer id);
    List<ProductDTO> getProducts();
}
