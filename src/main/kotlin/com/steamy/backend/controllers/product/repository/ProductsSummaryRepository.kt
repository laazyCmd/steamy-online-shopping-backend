package com.steamy.backend.controllers.product.repository

import com.steamy.backend.controllers.product.model.ProductsSummary
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface ProductsSummaryRepository : PagingAndSortingRepository<ProductsSummary, Int> {

    @Query( "SELECT * FROM products WHERE prod_name LIKE '%?1%'", nativeQuery = true )
    fun findByName( productName: String, pageable: Pageable ): Page<ProductsSummary>;

    @Query( "SELECT * FROM products WHERE prod_category = '?1'", nativeQuery = true )
    fun findByCategory( productCategory: String, pageable: Pageable ): Page<ProductsSummary>;

    @Query( "SELECT * FROM products WHERE prod_price <= ?1", nativeQuery = true )
    fun findByPrice( productPrice: Double, pageable: Pageable ): Page<ProductsSummary>;

    @Query( "SELECT * FROM products WHERE prod_name LIKE '%?1%' AND prod_category = '?2'", nativeQuery = true )
    fun findByNameAndCategory( productName: String, productCategory: String, pageable: Pageable ): Page<ProductsSummary>;

    @Query( "SELECT * FROM products WHERE prod_name LIKE '%?1%' AND prod_price <= ?2", nativeQuery = true )
    fun findByNameAndPrice( productName: String, productPrice: Double, pageable: Pageable ): Page<ProductsSummary>;

    @Query( "SELECT * FROM products WHERE prod_name LIKE '%?1%' AND prod_category = '?2' AND prod_price <= ?3", nativeQuery = true )
    fun findByNameCategoryAndPrice( productName: String, productCategory: String, productPrice: Double, pageable: Pageable ): Page<ProductsSummary>;
}