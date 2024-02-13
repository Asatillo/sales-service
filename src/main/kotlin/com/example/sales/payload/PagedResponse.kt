package com.example.sales.payload

import org.springframework.data.domain.Page

class PagedResponse<Any>(
    private var content: List<Any>,
    private var page: Int,
    private var size: Int,
    private var totalElements: Long,
    private var totalPages: Int
) {

        val list: List<Any>  = resource.content

        return PagedResponse<Any>(list, resource.pageable.pageNumber, resource.pageable.pageSize, resource.totalElements, resource.totalPages)
    }
}