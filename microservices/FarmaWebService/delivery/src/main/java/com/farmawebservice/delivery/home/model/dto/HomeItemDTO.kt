package com.farmawebservice.delivery.home.model.dto

import com.farmawebservice.delivery.address.model.dto.AddressDTO
import com.farmawebservice.delivery.base.BaseDTO

data class HomeItemDTO(
        var addressDTO: AddressDTO? = null
) : BaseDTO()
