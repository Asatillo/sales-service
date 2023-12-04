package com.example.sales.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sales")
class SalesController {
    @RequestMapping("/hello")
    fun hello(): String {
        return "Hello, World!"
    }
}