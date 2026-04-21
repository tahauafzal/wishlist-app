package com.wishlist.controller;

import com.wishlist.model.User;
import com.wishlist.model.Wishlist;
import com.wishlist.repository.UserRepository;
import com.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishlistController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WishlistService wishlistService;
    
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // For demo, use first user
        User user = userRepository.findById(1L).orElse(null);
        if (user == null) {
            // Create a demo user
            user = new User("Demo User", "demo@example.com");
            user = userRepository.save(user);
        }
        
        model.addAttribute("user", user);
        model.addAttribute("wishlists", wishlistService.getUserWishlists(user));
        return "dashboard";
    }
    
    @PostMapping("/wishlist/create")
    public String createWishlist(@RequestParam String title, 
                                  @RequestParam String description) {
        User user = userRepository.findById(1L).orElse(null);
        if (user != null) {
            wishlistService.createWishlist(user, title, description);
        }
        return "redirect:/dashboard";
    }
    
    @PostMapping("/wishlist/{id}/add-item")
    public String addItem(@PathVariable Long id,
                          @RequestParam String name,
                          @RequestParam String description,
                          @RequestParam String link) {
        wishlistService.addItemToWishlist(id, name, description, link);
        return "redirect:/dashboard";
    }
    
    @PostMapping("/item/{id}/reserve")
    public String reserveItem(@PathVariable Long id) {
        wishlistService.reserveItem(id);
        return "redirect:/dashboard";
    }
}
