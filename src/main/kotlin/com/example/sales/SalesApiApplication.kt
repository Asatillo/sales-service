package com.example.sales

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class SalesApplication

fun main(args: Array<String>) {
	runApplication<SalesApplication>(*args)
}
