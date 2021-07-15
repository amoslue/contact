package com.bupt.proj.service;

import com.bupt.proj.dao.ContactDAO;
import com.bupt.proj.model.enums.ContactStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bupt.proj.model.Contact;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactDAO contactDAO;
    public List<Contact> getAllContact(){return contactDAO.selectAll(); }
    public int addContact(Contact contact){return contactDAO.addContact(contact);}
    public void deleteContact (int id){contactDAO.updateContactStatus(id, ContactStatusEnum.DELETE.getValue());}
    public void recoverContact(int id){contactDAO.updateContactStatus(id, ContactStatusEnum.NORMAL.getValue());}


}
