package com.bupt.proj.controllers;

import com.bupt.proj.service.ContactService;
import com.bupt.proj.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bupt.proj.model.Contact;
import com.bupt.proj.model.User;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private HostService hostService;

    @RequestMapping(path={"/index"},method  = {RequestMethod.GET})

    public String contactList(Model model){

        User host =hostService.getUser();
        if (host!=null){
            model.addAttribute("host",host);

        }
        loadAllContactView(model);
        return "contact/contacts";
    }
    @RequestMapping(path={"/contacts/add"},method = {RequestMethod.GET})
    public String addContact(){
        return "contact/addContact";
        }

    @RequestMapping(path={"contacts/add/do"},method = {RequestMethod.POST})
public String adAddContact(
        @RequestParam("name") String name,
        @RequestParam("phoneNumber") String phoneNumber,
        @RequestParam("relationShip") String relationShip)
        //@RequestParam("picture") String picture)
    {
        Contact contact =new Contact();
        contact.setName(name);
        contact.setPhoneNumber(phoneNumber);
        contact.setRelationShip(relationShip);
       // contact.setPicture(picture);
        contactService.addContact(contact);
        return "redirect:/index";
    }

    @RequestMapping(path={"/contacts/{contactId[0-9]+}/delete"},method  ={RequestMethod.GET})
    public String deleteContact(
            @PathVariable("contactID") int contactId
    ){
        contactService.deleteContact(contactId);
        return "redirect:/index";
    }



    @RequestMapping(path = {"/contacts/{contactId[0-9]+}/recover"}, method = {RequestMethod.GET})
    public String recoverBook(
            @PathVariable("contactID") int contactID
    ) {

        contactService.recoverContact(contactID);
        return "redirect:/index";

    }


    private void loadAllContactView(Model model){

        model.addAttribute("contacts",contactService.getAllContact());
    }
}


