/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytb.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author baodu
 */
public class CartObject implements Serializable{
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    // add one to one item
    public boolean addItemToCart(String id){
        boolean result = false;
        //1. cart ko bao gio chua data
        // chi co ngan chua do moi chua data ma thoi
        // this is just example: check id ma thoi
        // database: quantity and status
        // id hieu luc: != null va ko chua space
        if (id == null){
            return result;
        }
        if (id.trim().isEmpty()){
            return result;
        }
        //2. check if items exists
        // kiem tra "chua ton tai", tao moi
        if (this.items == null){
            this.items = new HashMap<>();
        }
        //3. check if item already existed\
        int quantity = 1;
        if (this.items.containsKey(id)){
            quantity = this.items.get(id) + 1;
        }
        //4. drop item into items
        this.items.put(id, quantity);
        result = true;
        return result;
    }
    
    public boolean removeItemFromCart(String id){
        boolean result = false;
        //1. check if co items
        // items MUST HAVE in this case
        if (this.items != null){
            //2. check if item exists
            if (this.items.containsKey(id)){
                //3. remove
                this.items.remove(id);
                // if removed item is the last then set this collection null
                // for later purposes
                if (this.items.isEmpty()){
                    this.items = null;
                }
                result = true;
            }
        }
        /*
        
        */
        
        return result;
    }
}
