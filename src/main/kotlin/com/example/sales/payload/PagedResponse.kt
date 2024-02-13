package com.example.sales.payload

import org.springframework.data.domain.Page

class PagedResponse<Any>(
    var content: List<Any>,
    var page: Int,
    var size: Int,
    var totalElements: Long,
    var totalPages: Int
) {
    init {
        this.page += 1
    }

    constructor(resource: Page<Any> ): this(
        content = resource.content,
        page = resource.pageable.pageNumber,
        size = resource.pageable.pageSize,
        totalElements = resource.totalElements,
        totalPages = resource.totalPages)
}