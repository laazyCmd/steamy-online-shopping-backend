package com.steamy.backend.controllers.product.model

import javax.persistence.*
import kotlin.jvm.Transient

@Entity
class Products {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    val prod_id: Int = 0;

    @Column
    val prod_name: String = "";

    @Column
    val prod_description: String = "";

    @Transient
    var prod_image: String? = null;

    @Column
    val prod_rating: Double = 0.0;

    @Column
    val prod_price: Double = 0.0;

    @Column
    val prod_sales: Int = 0;

    @Column
    val prod_stocks: Int = 0;

    @Column
    val prod_category: String = "";

    @Column
    val prod_brand: String = "";

    @Column
    val date_created: String = "";

    @Column
    val seller_name: String = "";

    @Column
    val country_origin: String = "";

    @Column
    val shipping_origin: String = "";

    @Column
    val shipping_fee: Double = 0.0;

    @Column
    val is_popular: Boolean = false;
}