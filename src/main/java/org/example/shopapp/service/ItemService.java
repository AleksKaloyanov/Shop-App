package org.example.shopapp.service;

import org.example.shopapp.model.service.ItemServiceModel;
import org.example.shopapp.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {
    void add(ItemServiceModel itemServiceModel);

    ItemViewModel findById(Long id);

    List<ItemViewModel> findAllItems();

    void deleteById(Long id);
}
