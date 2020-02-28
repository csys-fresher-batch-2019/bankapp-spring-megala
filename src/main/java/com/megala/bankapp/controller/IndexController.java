package com.megala.bankapp.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.domain.Register;
import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.dto.MessageDTO;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.service.CreditCardService;
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    
//    @Autowired
//    DataSource dataSource;
//    
//    
//    
    @RequestMapping("/")
    public String index() {        
       logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is a error message");
		logger.debug("this is a debug message");
        return "BankLogin.jsp";
    }
//    public void list() throws SQLException {
//    	@SuppressWarnings("rawtypes")
//		ArrayList name=new ArrayList<>();
//    	Connection connection = dataSource.getConnection();
//    	System.out.println(connection);
//    }
    
//    @GetMapping
//    public void Register(@RequestParam("name") String cusName,@RequestParam("street") String street,@RequestParam("city") String city,@RequestParam("accType") String accType,@RequestParam("mbleNo") long mbleNo,@RequestParam("email") String mailId,@RequestParam("password") String passWord)  {
//    	
//    	//JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//    	String sql = "insert into users (name,street,city) values (?,?,?)";
//    	Object[] params = {cusName, street, city};
//    	int rows = jdbcTemplate.update(sql,params);
//    	System.out.println(rows);
//    	
//    }
     
   
}