/**
 * (C) Copyright 2021 Araf Karsh Hamid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fusion.air.microservice.adapters.service;

// Custom
import io.fusion.air.microservice.adapters.repository.ProductRepository;
import io.fusion.air.microservice.domain.entities.example.ProductEntity;
import io.fusion.air.microservice.domain.exceptions.DataNotFoundException;
import io.fusion.air.microservice.domain.models.example.Product;
import io.fusion.air.microservice.domain.ports.services.ProductService;
// Spring
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// Java
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * An Example of Standard CRUD Operations in a Jpa Repository
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    // Autowired using Constructor Injector
    private ProductRepository productRepository;

    /**
     * Autowired using Constructor Injector
     * @param productRepository
     */
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * WARNING:
     * This Method is ONLY For Demo Purpose. In Real World Scenario there should NOT be any
     * method which will return the whole data without any Conditions. Unless it's a very
     * small data set.
     *
     * Get All the Products
     * @return
     */
    @Override
    public List<ProductEntity> getAllProduct() {
        return this.productRepository.findAll();
    }

    /**
     * Search for the Product By the Product Names Like 'name'
     * @param name
     * @return
     */
    public List<ProductEntity> fetchProductsByName(String name) {
        name = name != null ? name.trim() : "%";
        List<ProductEntity> products = productRepository.findByProductNameContains(name);
        return checkProducts(products, name);
    }

    /**
     * Search for the Product By Price Greater Than or Equal To
     * @param price
     * @return
     */
    public List<ProductEntity> fetchProductsByPriceGreaterThan(BigDecimal price) {
        List<ProductEntity> products = productRepository.fetchProductsByPriceGreaterThan(price);
        return checkProducts(products, price);
    }

    /**
     * Returns Active Products Only
     * @return
     */
    public List<ProductEntity> fetchActiveProducts() {
        List<ProductEntity> products = productRepository.fetchActiveProducts();
        return checkProducts(products, "isActive");
    }

    /**
     * Checks if the Products List Contains Data
     * @param products
     * @return
     */
    private List<ProductEntity> checkProducts(List<ProductEntity> products, Object search) {
        if(products == null || products.isEmpty()) {
            throw new DataNotFoundException("No Data Found for the Search Query = ["+search+"]");
        }
        return products;
    }

    /**
     * Get Product By Product ID
     * @param productId
     * @return
     */
    @Override
    public ProductEntity getProductById(UUID productId) {
        Optional<ProductEntity> productDb = productRepository.findById(productId);
        if(productDb.isPresent()) {
            return productDb.get();
        }
        throw new DataNotFoundException("Data not found with id : " + productId);
    }

    /**
     * Create Product (from the DTO)
     * @param product
     * @return
     */
    public ProductEntity createProduct(Product product) {
        return productRepository.save(new ProductEntity(product));
    }

    /**
     * Create Product
     *
     * @param product
     * @return
     */
    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    /**
     * Create Products (from List of DTOs)
     * @param products
     * @return
     */
    public List<ProductEntity> createProducts(List<Product> products) {
        List<ProductEntity> productList = new ArrayList<>();
        for(Product p : products) {
            productList.add(new ProductEntity(p));
        }
        return productRepository.saveAll(productList);
    }

    /**
     * Create Products (from List of ProductEntity)
     * @param products
     * @return
     */
    @Transactional(rollbackFor = { SQLException.class })
    public List<ProductEntity> createProductsEntity(List<ProductEntity> products) {
        return productRepository.saveAll(products);
    }

    /**
     * Update Product
     *
     * @param product
     * @return
     */
    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public ProductEntity updateProduct(ProductEntity product) {
        productRepository.saveAndFlush(product);
        return product;
    }

    /**
     * Update the Product Price
     * @param product
     * @return
     */
    @Transactional(rollbackFor = { SQLException.class })
    public ProductEntity updatePrice(ProductEntity product) {
        ProductEntity productUpdate = getProductById(product.getUuid()) ;
        productUpdate.setProductPrice(product.getProductPrice());
        productRepository.saveAndFlush(productUpdate);
        return productUpdate;
    }

    /**
     * Update Product (Name & Details)
     *
     * @param product
     * @return
     */
    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public ProductEntity updateProductDetails(ProductEntity product) {
        ProductEntity productUpdate = getProductById(product.getUuid()) ;
        productUpdate.setProductName(product.getProductName());
        productUpdate.setProductDetails(product.getProductDetails());
        productRepository.saveAndFlush(productUpdate);
        return productUpdate;
    }

    /**
     * De Activate Product
     *
     * @param productId
     * @return
     */
    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public ProductEntity deActivateProduct(UUID productId) {
        ProductEntity product = getProductById(productId);
        product.deActivateProduct();
        productRepository.saveAndFlush(product);
        return product;
    }

    /**
     * Activate Product
     *
     * @param productId
     * @return
     */
    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public ProductEntity activateProduct(UUID productId) {
        ProductEntity product = getProductById(productId);
        product.activateProduct();
        productRepository.saveAndFlush(product);
        return product;
    }

    /**
     * Delete the product
     * @param productId
     */
    @Override
    @Transactional(rollbackFor = { SQLException.class })
    public void deleteProduct(UUID productId) {
        ProductEntity product = getProductById(productId);
        productRepository.delete(product);
    }
}
