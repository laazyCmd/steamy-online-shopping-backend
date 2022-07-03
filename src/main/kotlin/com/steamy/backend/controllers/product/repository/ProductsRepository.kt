package com.steamy.backend.controllers.product.repository

import com.steamy.backend.controllers.product.model.Products
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductsRepository : JpaRepository<Products, Int> {

    @Query( value = "SELECT prod_image FROM products WHERE prod_id = ?1", nativeQuery = true )
    fun getProductImage( product_id: Int ): ByteArray?;
}