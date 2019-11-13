package controller;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CustomerImpl;
import service.CustomerService;

@Controller
public class CustomerController {
    private CustomerService customerService = new CustomerImpl();

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "index";
    }
    @GetMapping("/customer/create")
    public String create(Model model){
        model.addAttribute("customer",new Customer());
        return "create";
    }
    @PostMapping("/customer/save")
    public String save(Customer customer, RedirectAttributes redirect){
        customer.setId((int)(Math.random()*1000));
        customerService.save(customer);
        redirect.addFlashAttribute("succeed","Save customer succeedfully!");
        return "redirect:/index";
    }
    @GetMapping("/customer/{id}/edit")
    public String edit(@PathVariable int id,Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "edit";
    }
    @PostMapping("/customer/update")
    public String update(Customer customer,RedirectAttributes redirect){
        customerService.update(customer.getId(),customer);
        redirect.addFlashAttribute("success", "Modified customer successfully!");
        return "redirect:/index";
    }
    @GetMapping("/customer/{id}/delete")
    public String delete(@PathVariable int id,Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "delete";
    }
    @PostMapping("/customer/delete")
    public String delete(Customer customer,RedirectAttributes redirect){
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Remove customer successfully!");
        return "redirect:/index";
    }
    @GetMapping("/customer/{id}/view")
    public String view(@PathVariable int id,Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "view";
    }



}
