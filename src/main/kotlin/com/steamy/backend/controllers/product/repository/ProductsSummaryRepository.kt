package com.steamy.backend.controllers.product.repository

import com.steamy.backend.controllers.product.model.ProductsSummary
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository

interface ProductsSummaryRepository :
    PagingAndSortingRepository<ProductsSummary, Int>,
    JpaSpecificationExecutor<ProductsSummary> {
}
