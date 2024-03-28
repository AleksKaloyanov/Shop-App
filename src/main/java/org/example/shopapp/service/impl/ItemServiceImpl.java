package org.example.shopapp.service.impl;

import org.example.shopapp.model.entity.ItemEntity;
import org.example.shopapp.model.service.ItemServiceModel;
import org.example.shopapp.model.view.ItemViewModel;
import org.example.shopapp.repository.ItemRepository;
import org.example.shopapp.service.CategoryService;
import org.example.shopapp.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ItemServiceImpl(ItemRepository itemRepository,
                           ModelMapper modelMapper,
                           CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void add(ItemServiceModel itemServiceModel) {
        ItemEntity item = modelMapper.map(itemServiceModel, ItemEntity.class);
        item.setCategory(categoryService.findCategory(itemServiceModel.getCategory()));

        itemRepository.save(item);
    }

    @Override
    public ItemViewModel findById(Long id) {
        return itemRepository.findById(id)
                .stream()
                .map(itemEntity -> modelMapper.map(itemEntity, ItemViewModel.class))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ItemViewModel> findAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(itemEntity -> modelMapper.map(itemEntity, ItemViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
