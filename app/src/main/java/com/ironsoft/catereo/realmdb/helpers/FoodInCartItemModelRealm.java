package com.ironsoft.catereo.realmdb.helpers;

import android.graphics.Bitmap;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class FoodInCartItemModelRealm extends RealmObject {

    private Integer menuItemId;
    private String imageBitmap; // Resource ID obrazu
    @Required  // Realm wymaga, aby pola String nie były null, chyba że jawnie zezwolono
    private String title; // Nazwa dania
    private float rating; // Ocena dania
    private double price; // Cena dania
    private int quantity; // Ilość dania

    // Konstruktor domyślny wymagany przez Realm
    public FoodInCartItemModelRealm() {
    }

    // Konstruktor do tworzenia obiektów (nie używany bezpośrednio przez Realm)
    public FoodInCartItemModelRealm(Integer menuItemId, String imageBitmap, String title, float rating, double price, int quantity) {
        this.menuItemId = menuItemId;
        this.imageBitmap = imageBitmap;
        this.title = title;
        this.rating = rating;
        this.price = price;
        this.quantity = quantity;
    }

    // Gettery i settery
    public String getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(String imageUrl) {
        this.imageBitmap = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }
}
