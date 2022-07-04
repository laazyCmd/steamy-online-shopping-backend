package com.steamy.backend.controllers.product.repository

import com.steamy.backend.controllers.product.model.ProductsSummary
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface ProductsSummaryRepository : PagingAndSortingRepository<ProductsSummary, Int> {

    fun findAllByProdNameContaining( productName: String, pageable: Pageable ): Page<ProductsSummary>;
    fun findAllByProdCategory( productCategory: String, pageable: Pageable ): Page<ProductsSummary>;
    fun findAllByProdNameContainingAndProdCategory( productName: String, productCategory: String, pageable: Pageable ): Page<ProductsSummary>;
}