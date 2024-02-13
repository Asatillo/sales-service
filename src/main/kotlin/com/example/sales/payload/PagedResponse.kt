package com.example.sales.payload

import org.springframework.data.domain.Page

class PagedResponse<Any>(
    var content: List<Any>,
    var page: Int,
    var size: Int,
    var totalElements: Long,
    var totalPages: Int
) {

        val list: List<Any>  = resource.content

        return PagedResponse<Any>(list, resource.pageable.pageNumber, resource.pageable.pageSize, resource.totalElements, resource.totalPages)
    }
}