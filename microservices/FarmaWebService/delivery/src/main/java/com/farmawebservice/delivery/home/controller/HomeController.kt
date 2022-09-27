package com.farmawebservice.delivery.home.controller

import com.farmawebservice.delivery.home.model.dto.HomeItemDTO
import com.farmawebservice.delivery.home.service.HomeService
import com.farmawebservice.delivery.util.messages.dtos.MessageResponseDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/bff/api/home")
class HomeController {


    @Autowired
    private lateinit var homeService: HomeService

    @GetMapping("/itens")
    @ApiOperation(value = "recovery all items for client", notes = "")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successufully recovery items"),
        ApiResponse(code = 404, message = "Not found"), ApiResponse(code = 500, message = "Error Internal")])
    fun getAllItemsFromHome(@RequestHeader("accessToken") accessToken: String, @RequestBody dto: HomeItemDTO): ResponseEntity<MessageResponseDTO>? {
        var result: ResponseEntity<MessageResponseDTO>? = null
        this.homeService.getAllItemsFromHome(accessToken, dto = dto, onResponse = { resourceMessage ->
            result = if (resourceMessage?.isError == true) {
                ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage.messageValidator, resourceMessage.messageValidator, HttpServletResponse.SC_BAD_REQUEST))
            } else {
                ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage?.messageValidator, resourceMessage?.descriptionValidator, HttpServletResponse.SC_ACCEPTED, resourceMessage?.responseDTO))
            }
        })
        return result
    }

    @GetMapping("/search")
    @ApiOperation(value = "recovery all productsShop for client", notes = "")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successufully recovery items"),
        ApiResponse(code = 404, message = "Not found"), ApiResponse(code = 500, message = "Error Internal")])
    fun getAllProductsByShop(@RequestHeader("accessToken") accessToken: String, @RequestBody dto: HomeItemDTO): ResponseEntity<MessageResponseDTO>? {
        var result: ResponseEntity<MessageResponseDTO>? = null
        this.homeService.getAllProductsByShop(accessToken, dto = dto, onResponse = { resourceMessage ->
            result = if (resourceMessage?.isError == true) {
                ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage.messageValidator, resourceMessage.messageValidator, HttpServletResponse.SC_BAD_REQUEST))
            } else {
                ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage?.messageValidator, resourceMessage?.descriptionValidator, HttpServletResponse.SC_ACCEPTED, resourceMessage?.responseDTO))
            }
        })
        return result
    }

    @GetMapping("/categories/all")
    @ApiOperation(value = "recovery all categories for client", notes = "")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successufully recovery items"),
        ApiResponse(code = 404, message = "Not found"), ApiResponse(code = 500, message = "Error Internal")])
    fun getAllCategoriesByShop(@RequestHeader("accessToken") accessToken: String, @RequestBody dto: HomeItemDTO): ResponseEntity<MessageResponseDTO>? {
        var result: ResponseEntity<MessageResponseDTO>? = null
        this.homeService.getAllCategoriesByShop(accessToken, dto = dto, onResponse = { resourceMessage ->
            result = if (resourceMessage?.isError == true) {
                ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage.messageValidator, resourceMessage.messageValidator, HttpServletResponse.SC_BAD_REQUEST))
            } else {
                ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage?.messageValidator, resourceMessage?.descriptionValidator, HttpServletResponse.SC_ACCEPTED, resourceMessage?.responseDTO))
            }
        })
        return result
    }

    @GetMapping("/products/highlight/all")
    @ApiOperation(value = "recovery all products highlights for client", notes = "")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successufully recovery items"),
        ApiResponse(code = 404, message = "Not found"), ApiResponse(code = 500, message = "Error Internal")])
    fun getAllHighLightsByUser(@RequestHeader("accessToken") accessToken: String, @RequestBody dto: HomeItemDTO): ResponseEntity<MessageResponseDTO>? {
        var result: ResponseEntity<MessageResponseDTO>? = null
        this.homeService.getAllProductsHighLightByShop(accessToken, dto = dto, onResponse = { resourceMessage ->
            result = if (resourceMessage?.isError == true) {
                ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage.messageValidator, resourceMessage.messageValidator, HttpServletResponse.SC_BAD_REQUEST))
            } else {
                ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body<MessageResponseDTO>(MessageResponseDTO.build(resourceMessage?.messageValidator, resourceMessage?.descriptionValidator, HttpServletResponse.SC_ACCEPTED, resourceMessage?.responseDTO))
            }
        })
        return result
    }
}