package com.steamy.backend.messages

class NotFound( customMessage: String = "Not found" ) {

    val status: Int = 404;
    val message: String;

    init {
        this.message = customMessage;
    }
}