package com.farmawebservice.delivery.categories.controller;

import com.farmawebservice.delivery.categories.model.Category;
import com.farmawebservice.delivery.categories.model.dto.CategoryDTO;
import com.farmawebservice.delivery.categories.service.CategoriesService;
import com.farmawebservice.delivery.util.messages.dtos.MessageResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {


    @Autowired
    private CategoriesService categoriesService;



    @PostMapping
    @ApiOperation(value = "save only category for product", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500,message = "Error Internal")
    })
    private ResponseEntity<MessageResponseDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        if(this.categoriesService.saveCategory(categoryDTO)) {
            return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("" +
                    "Category Created", "Successful", HttpServletResponse.SC_ACCEPTED));
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("" +
                    "Category Don't Created", "\n" +
                    "Could not create category, try again", HttpServletResponse.SC_BAD_REQUEST));
        }
    }

    @GetMapping("/all")
    @ApiOperation(value = "get all categories", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully recovery"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500,message = "Error Internal")
    })
    public Iterable<Category> getAllCategories() {
        return this.categoriesService.getAllCategories();
    }

    @DeleteMapping
    @ApiOperation(value = "delete only category for product", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500,message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> deleteCategory(@RequestParam Integer id) {
        if(!this.categoriesService.deleteCategoryById(id)) {
            return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("" +
                    "Category Deleted", "Successful", HttpServletResponse.SC_ACCEPTED));
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(MessageResponseDTO.build("" +
                    "Category Don't Deleted", "\n" +
                    "Could not delete category, try again", HttpServletResponse.SC_NOT_FOUND));
        }
    }
}
