package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ContactRepository {
    private JdbcTemplate jdbc;
    @Autowired
    public ContactRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }
    public List<Contact> findAll() {
        return jdbc.query("select id,firstName,lastName,phoneNumber,emailAddress from contacts order by lastName",
                new RowMapper<Contact>() {
                    @Override
                    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
                        Contact contact = new Contact();
                        contact.setId(resultSet.getLong(1));
                        contact.setFirstName(resultSet.getString(2));
                        contact.setLastName(resultSet.getString(3));
                        contact.setPhoneNumber(resultSet.getString(4));
                        contact.setEmailAddress(resultSet.getString(5));
                        return contact;
                    }
                });
    }

    public void save(Contact contacts) {
        System.out.println("firstName : "+ contacts.getFirstName()+"===="+contacts.getEmailAddress()+contacts.getLastName());
        jdbc.update(
                "insert into contacts " +
                        "(firstName,lastName,phoneNumber,emailAddress) "+ "values(?,?,?,?)",
                contacts.getFirstName(),contacts.getLastName(),
                contacts.getPhoneNumber(),contacts.getEmailAddress()
        );
    }


    /* <ul th:each="contact : ${contacts}}">
        <li>
            <span th:text="${contacts.firstName}">First</span>
            <span th:text="${contacts.lastName}">Last</span>
            <span th:text="${contacts.phoneName}">Phone</span>
            <span th:text="${contacts.emailAddress}">email</span>
        </li>
    </ul>
    public List<Contact> findAll() throws ClassNotFoundException, SQLException {
        List<Contact> list = new ArrayList<Contact>();
        Connection con = getConnection();
        String sql="select * from contact order by id";
        try {
            Statement stateMent = con.createStatement();
            ResultSet set = stateMent.executeQuery(sql);
            while (set.next()){
               Contact contact = new Contact();
               contact.setId(set.getLong(1));
               contact.setFirstName(set.getString(2));
               contact.setLastName(set.getString(3));
               contact.setPhoneNumber(set.getString(4));
               contact.setEmailAddress(set.getString(5));
               list.add(contact);
            }
            con.close();
            return  list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Contact contacts) throws ClassNotFoundException, SQLException {
        Connection con = getConnection();
        String sql="insert into contact (firstName,lastName,phoneNumber,emailAddress)" +
                "values(?,?,?,?)";
        try {
            PreparedStatement stateMent = con.prepareStatement(sql);
            stateMent.setString(2,contacts.getFirstName());
            stateMent.setString(3,contacts.getLastName());
            stateMent.setString(4,contacts.getPhoneNumber());
            stateMent.setString(5,contacts.getEmailAddress());
            stateMent.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        con.close();
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/wenda";
            Connection con = DriverManager.getConnection("","root","668402");
            return con;

    }*/
}
