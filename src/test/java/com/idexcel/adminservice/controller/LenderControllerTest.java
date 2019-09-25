package com.idexcel.adminservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idexcel.adminservice.dao.LenderRepo;
import com.idexcel.adminservice.dto.*;
import com.idexcel.adminservice.entity.Address;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.entity.PrimaryContact;
import com.idexcel.adminservice.enums.LenderStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
public class LenderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LenderRepo repo;


    @Before
    public void setup(){
        Lender lender = new Lender("user-111","John Doe", new Address("PKY", "Tysons", "VA", "USA", 21223 ),
                new PrimaryContact("Mary Jane", "mj@idexcel.net", "757-663-2122"), LenderStatus.ACTIVE, "Hrishi G",
                Calendar.getInstance().getTime(), "Hrishi G", Calendar.getInstance().getTime());
        repo.save(lender);
        Lender lender1 = new Lender("userSuspended","Wills Doe", new Address("PKY", "Tysons", "VA", "USA", 21223 ),
                new PrimaryContact("Mary Jae", "lj@idexcel.net", "357-663-2122"), LenderStatus.SUSPENDED, "Hrishi G",
                Calendar.getInstance().getTime(), "Hrishi G", Calendar.getInstance().getTime());
        repo.save(lender1);
    }

    @After
    public void cleanUp(){
        repo.deleteAll();
    }

    @Test
    public void findAll() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/lenders")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("user-111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("John Doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("ACTIVE")));
    }

    @Test
    public void findById() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/lenders/user-111")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("user-111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("John Doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("ACTIVE")));

    }

    @Test
    public void findById404() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/lenders/111")).andExpect(MockMvcResultMatchers.status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.get("/lenders/userSuspended")).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void createLender() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        LenderCreateDTO lender = new LenderCreateDTO();
        lender.setName("Commercial");
        lender.setAddress(new AddressDTO("PKY", "Tysons", "VA", "USA", 21223));
        lender.setPrimaryContact(new PrimaryContactDTO("Mary Watson", "mww@idexcel.net", "777-663-2122"));

        mvc.perform(MockMvcRequestBuilders.post("/lenders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(lender)))
        .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(MockMvcRequestBuilders.get("/lenders")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("user-111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("John Doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("ACTIVE")));
    }

    @Test
    public void create409() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        LenderCreateDTO lender = new LenderCreateDTO();
        lender.setName("John Doe");
        lender.setAddress(new AddressDTO("PKY", "Tysons", "VA", "USA", 21223));
        lender.setPrimaryContact(new PrimaryContactDTO("Mary Watson", "mww@idexcel.net", "777-663-2122"));

        mvc.perform(MockMvcRequestBuilders.post("/lenders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(lender)))
                .andExpect(MockMvcResultMatchers.status().isConflict());

    }

    @Test
    public void updateLender() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        LenderUpdateDTO lender = new LenderUpdateDTO();
        lender.setId("user-111");
        lender.setAddress(new AddressDTO("Herndon", "Ashburn", "VA", "USA", 21211));
        lender.setPrimaryContact(new PrimaryContactDTO("Mary Jade", "mww@idexcel.net", "777-663-2122"));
        lender.setName("John Wick");
        lender.setStatus(LenderStatus.ACTIVE);

        mvc.perform(MockMvcRequestBuilders.put("/lenders/user-111")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(lender)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mvc.perform(MockMvcRequestBuilders.get("/lenders/user-111")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("user-111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("John Wick")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("ACTIVE")));
    }

    @Test
    public void updateLender404() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        LenderUpdateDTO lender = new LenderUpdateDTO();
        lender.setId("user-111");
        lender.setAddress(new AddressDTO("Herndon", "Ashburn", "VA", "USA", 21211));
        lender.setPrimaryContact(new PrimaryContactDTO("Mary Jade", "mww@idexcel.net", "777-663-2122"));
        lender.setName("John Wick");
        lender.setStatus(LenderStatus.ACTIVE);

        mvc.perform(MockMvcRequestBuilders.put("/lenders/user-100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(lender)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void deleteLender() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/lenders/user-111"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mvc.perform(MockMvcRequestBuilders.get("/lenders/user-111"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @Test
    public void deleteLender404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/lenders/user"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void patchLender() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        LenderPatchDTO lender = new LenderPatchDTO();
        lender.setId("user-111");
        lender.setStatus(LenderStatus.SUSPENDED);

        mvc.perform(MockMvcRequestBuilders.patch("/lenders/user-111")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(lender)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mvc.perform(MockMvcRequestBuilders.get("/lenders/user-111"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void patchLender404() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        LenderPatchDTO lender = new LenderPatchDTO();
        lender.setId("user-10001");
        lender.setStatus(LenderStatus.ACTIVE);

        mvc.perform(MockMvcRequestBuilders.patch("/lenders/user-10001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(lender)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void getStatus() throws Exception{
        mvc.perform(MockMvcRequestBuilders.head("/lenders/user-111")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getStatus404() throws Exception{
        mvc.perform(MockMvcRequestBuilders.head("/lenders/ddfghjf")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}