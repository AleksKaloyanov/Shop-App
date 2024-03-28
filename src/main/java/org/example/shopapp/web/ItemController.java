package org.example.shopapp.web;

import jakarta.validation.Valid;
import org.example.shopapp.model.binding.ItemAddBindingModel;
import org.example.shopapp.model.service.ItemServiceModel;
import org.example.shopapp.model.view.ItemViewModel;
import org.example.shopapp.service.ItemService;
import org.example.shopapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final CurrentUser currentUser;
    private final ItemService itemService;
    private final ModelMapper modelMapper;

    public ItemController(CurrentUser currentUser, ItemService itemService, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        if (currentUser.getId() == null) {
            return "index";
        }
        return "add-item";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ItemAddBindingModel itemAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("itemAddBindingModel", itemAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.itemAddBindingModel", bindingResult);

            return "redirect:add";
        }

        ItemServiceModel itemServiceModel =
                modelMapper.map(itemAddBindingModel, ItemServiceModel.class);


        itemService.add(itemServiceModel);

        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        if (!currentUser.isLogged()) {
            return "redirect:/";
        }
        ItemViewModel itemViewModel = itemService.findById(id);

        model.addAttribute("itemViewModel", itemViewModel);

        return "details-item";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (!currentUser.isLogged()) {
            return "redirect:/";
        }
        itemService.deleteById(id);

        return "redirect:/";
    }


    @ModelAttribute
    public ItemAddBindingModel itemAddBindingModel() {
        return new ItemAddBindingModel();
    }

}
