package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.Request;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ContactController {
    private ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactRepository contactRepository){
        this.contactRepository=contactRepository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String home (Map<String,Object> model) throws SQLException, ClassNotFoundException {
        List<Contact> contacts = contactRepository.findAll();
        model.put("contacts",contacts);
        return "home";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String submit (Contact contacts) throws SQLException, ClassNotFoundException {
        contactRepository.save(contacts);
        return "redirect:/";
    }
}
