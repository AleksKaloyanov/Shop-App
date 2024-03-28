package org.example.shopapp.web;

import org.example.shopapp.model.view.ItemViewModel;
import org.example.shopapp.service.ItemService;
import org.example.shopapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ItemService itemService;

    public HomeController(CurrentUser currentUser, ItemService itemService) {
        this.currentUser = currentUser;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!currentUser.isLogged()) {
            return "index";
        }

        List<ItemViewModel> items = itemService.findAllItems();

        model.addAttribute("items", items)
                .addAttribute("count", items.size());

        return "home";
    }

}
