package com.ironsoft.catereo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ironsoft.catereo.api.Helpers.CategoryObject;
import com.ironsoft.catereo.api.Helpers.CurrentUserDetails;
import com.ironsoft.catereo.api.Helpers.MenuItemObject;
import com.ironsoft.catereo.api.Helpers.Order;

import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<MenuItemObject>> menuItemsData = new MutableLiveData<>();
    private final MutableLiveData<List<CategoryObject>> categoryItemsData = new MutableLiveData<>();
    private final MutableLiveData<CurrentUserDetails> currentUserItemsData = new MutableLiveData<>();
    private final MutableLiveData<List<Order>> orderItemsData = new MutableLiveData<>();


    public void setMenuItemsData(List<MenuItemObject> data) {
        this.menuItemsData.setValue(data);
    }

    public LiveData<List<MenuItemObject>> getMenuItemsData() {
        return menuItemsData;
    }

    public void setCategoryItemsData(List<CategoryObject> data){
        this.categoryItemsData.setValue(data);
    }

    public LiveData<List<CategoryObject>> getCategoryItemsData() {
        return categoryItemsData;
    }
    public void setCurrentUserItemsData(CurrentUserDetails data){
        this.currentUserItemsData.setValue(data);
    }
    public LiveData<CurrentUserDetails> getCurrentUserItemsData() {
        return currentUserItemsData;
    }

    public void setOrderItemsData(List<Order> data){
        this.orderItemsData.setValue(data);
    }
    public LiveData<List<Order>> getOrderItemsData() { return orderItemsData; }
}
