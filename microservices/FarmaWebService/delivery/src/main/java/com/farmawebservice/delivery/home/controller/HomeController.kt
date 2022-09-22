package com.farmawebservice.delivery.home.controller

import com.farmawebservice.delivery.home.model.dto.HomeItemDTO
import com.farmawebservice.delivery.home.service.HomeService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bff/api/home")
class HomeController {


    @Autowired
    private lateinit var homeService: HomeService

    @GetMapping("/itens")
    @ApiOperation(value = "recovery all items for client", notes = "")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successufully recovery items"),
        ApiResponse(code = 404, message = "Not found"), ApiResponse(code = 500, message = "Error Internal")])
    fun getAllItemsFromHome(@RequestHeader("accessToken") accessToken: String, @RequestBody dto: HomeItemDTO) {
        this.homeService.getAllItemsFromHome(accessToken,dto = dto)
    }
}