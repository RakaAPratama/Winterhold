package com.winterhold.mvc.controllers;

import com.winterhold.mvc.dtos.customer.CustomerDTO;
import com.winterhold.mvc.dtos.customer.CustomerGridDTO;
import com.winterhold.mvc.dtos.customer.UpdateInsertCustomerDTO;
import com.winterhold.mvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("index")
    public String getAllCustomers(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "") String id,
                                  @RequestParam(defaultValue = "") String fullName,
                                  Model model){
        Page<CustomerDTO> allCustomers =customerService.findByIdAndName(id, fullName, page);
        List<CustomerGridDTO> customerGridDTOS = CustomerGridDTO.convertCustomerGrid(allCustomers.getContent());

        model.addAttribute("customers", customerGridDTOS);
        model.addAttribute("breadCrumbs", "Customer / Index");
        model.addAttribute("totalPage", allCustomers.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("id", id);
        model.addAttribute("fullName", fullName);
        return "customer/customer-index";
    }

    @GetMapping("upsert-form")
    public String updateInsertForm(@RequestParam(required = false) String id, Model model){
        if (id != null){
            model.addAttribute("customer", customerService.findByCustomerId(id));
            model.addAttribute("breadCrumbs", "Customer / Update Existing Cutomer");
        }else {
            model.addAttribute("customer", new UpdateInsertCustomerDTO());
            model.addAttribute("breadCrumbs", "Customer / Insert New Customer");
        }
        return "customer/customer-upsert";
    }

    @PostMapping("upsert")
    public String updateInsert(@Valid @ModelAttribute("customer") UpdateInsertCustomerDTO customer,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            model.addAttribute("customer", customer);
            return "customer/customer-upsert";
        }
        customerService.saveCustomer(customer);
        redirectAttributes.addFlashAttribute("succes", "Customer has been saved");
        return "redirect:/customer/index";
    }

    @GetMapping("delete")
    public String delete(@RequestParam(required = false) String id, RedirectAttributes redirectAttributes){
        if (id != null){
            try {
                customerService.deleteCustomer(id);
                redirectAttributes.addFlashAttribute("succes", "Deleted");
            }catch(Exception e){
                redirectAttributes.addFlashAttribute("error", "Failed delete customer");
                return "redirect:/customer/index";
            }
        }
        return "redirect:/customer/index";
    }

    @GetMapping("extend")
    public String extend(@RequestParam String id, RedirectAttributes redirectAttributes){
        if (id != null){
            try {
                customerService.extendCustomer(id);
                redirectAttributes.addFlashAttribute("succes", "Successful extend Membership Number for " + id);
            }catch (Exception e){
                redirectAttributes.addFlashAttribute("error", "Failed extend customer");
                return "redirect:/customer/index";
            }
        }
        return "redirect:/customer/index";
    }
}
