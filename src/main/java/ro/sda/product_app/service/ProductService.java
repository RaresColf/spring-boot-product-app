package ro.sda.product_app.service;

import org.springframework.stereotype.Service;
import ro.sda.product_app.dto.Product;
import ro.sda.product_app.exception.custom_exceptions.AlreadyExistedException;
import ro.sda.product_app.exception.custom_exceptions.NonFoundException;
import ro.sda.product_app.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product p) {
        Optional<Product> optionalProduct = productRepository.findByName(p.getName());
        if(optionalProduct.isPresent()) {
            throw  new AlreadyExistedException();
        }
        productRepository.save(p);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findProductsWithPriceGraterThen(int price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }

    public Product findByID(int id){
        Optional<Product> opt = productRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }else{
            throw new NonFoundException();
        }

//        public Product findById(int id){
//            return productRepository.findById(id).orElseThrow(() ->new NonFoundException());
//        }

    }
}
