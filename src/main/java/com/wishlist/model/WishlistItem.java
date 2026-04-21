package com.wishlist.model;

import jakarta.persistence.*;

@Entity
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private String link;
    private boolean reserved;
    
    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;
    
    public WishlistItem() {}
    
    public WishlistItem(String name, String description, String link, Wishlist wishlist) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.wishlist = wishlist;
        this.reserved = false;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public boolean isReserved() { return reserved; }
    public void setReserved(boolean reserved) { this.reserved = reserved; }
    public Wishlist getWishlist() { return wishlist; }
    public void setWishlist(Wishlist wishlist) { this.wishlist = wishlist; }
}
