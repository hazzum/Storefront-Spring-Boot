package com.hazzum.storefront.rest.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazzum.storefront.entity.DetailedOrder;
import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.rest.exceptionHandler.InternalServerErrorException;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;
import com.hazzum.storefront.service.user.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> index() {
        List<User> theUsers = userService.index();
        if (theUsers.isEmpty())
            throw new NotFoundException("No users found");
        return theUsers;
    }

    @GetMapping("{userId}")
    public User getUser(@PathVariable int userId) {
        User theUser = userService.getUser(userId);
        if(theUser==null) {
            throw new NotFoundException("User id not found - " + userId);
        }
        return theUser;
    }

    @GetMapping("{userId}/orders/active")
    public List<DetailedOrder> showActiveOrders(@PathVariable int userId) {
        List<DetailedOrder> theOrders = userService.getActiveOrders(userId);
        if(theOrders.isEmpty()) {
            return new ArrayList<DetailedOrder>();
        }
        return theOrders;
    }

    @GetMapping("{userId}/orders/completed")
    public List<DetailedOrder> showCompleteOrders(@PathVariable int userId) {
        List<DetailedOrder> theOrders = userService.getHistory(userId);
        if(theOrders.isEmpty()) {
            return new ArrayList<DetailedOrder>();
        }
        return theOrders;
    }

    @PutMapping("{userId}")
    public User updatUser(@RequestBody User theUser, @PathVariable int userId) {
        User tempUser = userService.getUser(userId);
        // throw exception if null
        if (tempUser == null)
            throw new NotFoundException("User id not found - " + userId);
        // update user
        theUser.setId(userId);
        try {
            return userService.updateUser(theUser);
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not update user");
        }
    }

    // add mapping Delete /users/{userId} - delete existing user
    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable int userId) {
        User tempUser = userService.getUser(userId);
        // throw exception if null
        if (tempUser == null)
            throw new NotFoundException("User id not found - " + userId);
        try {
            userService.deleteUser(userId);
            return "Deleted User id - " + userId;
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not delete user");
        }
    }
}
