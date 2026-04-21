package com.wishlist.service;

import com.wishlist.model.User;
import com.wishlist.model.Wishlist;
import com.wishlist.model.WishlistItem;
import com.wishlist.repository.WishlistRepository;
import com.wishlist.repository.WishlistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    
    @Autowired
    private WishlistRepository wishlistRepository;
    
    @Autowired
    private WishlistItemRepository wishlistItemRepository;
    
    public Wishlist createWishlist(User user, String title, String description) {
        Wishlist wishlist = new Wishlist(title, description, user);
        return wishlistRepository.save(wishlist);
    }
    
    public Wishlist addItemToWishlist(Long wishlistId, String name, String description, String link) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
            .orElseThrow(() -> new RuntimeException("Wishlist not found"));
        WishlistItem item = new WishlistItem(name, description, link, wishlist);
        wishlist.getItems().add(item);
        return wishlistRepository.save(wishlist);
    }
    
    public void reserveItem(Long itemId) {
        WishlistItem item = wishlistItemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setReserved(true);
        wishlistItemRepository.save(item);
    }
    
    public List<Wishlist> getUserWishlists(User user) {
        return wishlistRepository.findByUserId(user.getId());
    }
}
