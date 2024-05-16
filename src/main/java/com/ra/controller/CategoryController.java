package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category")
    public String index(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "category";
    }
    @GetMapping("/add-category")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "add-category";
    }
    @PostMapping("/add-category")
    public String save(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes){
        if(categoryService.create(category)){
            redirectAttributes.addFlashAttribute("success","Thêm mới thành công");
            return "redirect:/category";
        }
        return "add-category";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "edit-category";
    }
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("category") Category category,RedirectAttributes redirectAttributes){
        if(categoryService.update(category)){
            redirectAttributes.addFlashAttribute("success","Cập nhật thành công");
            return "redirect:/category";
        }
        return "edit-category";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        categoryService.delete(id);
        return "redirect:/category";
    }
}
