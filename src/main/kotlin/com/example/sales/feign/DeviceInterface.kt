package com.example.sales.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "CRM-SERVICE", url = "http://localhost:8765", path = "/crm/devices")
interface DeviceInterface {
    @GetMapping("/{id}")
    fun getById(
        @RequestHeader(value = "Authorization", required = true) authorizationHeader: String,
        @PathVariable id: Long): ResponseEntity<Any>;
}