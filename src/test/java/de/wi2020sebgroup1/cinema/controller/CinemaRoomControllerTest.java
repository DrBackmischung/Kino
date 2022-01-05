package de.wi2020sebgroup1.cinema.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.wi2020sebgroup1.cinema.configurationObject.CinemaRoomConfigurationObject;
import de.wi2020sebgroup1.cinema.entities.Cinema;
import de.wi2020sebgroup1.cinema.entities.CinemaRoom;
import de.wi2020sebgroup1.cinema.entities.CinemaRoomSeatingPlan;
import de.wi2020sebgroup1.cinema.entities.City;
import de.wi2020sebgroup1.cinema.repositories.CinemaRepository;
import de.wi2020sebgroup1.cinema.repositories.CinemaRoomRepository;
import de.wi2020sebgroup1.cinema.repositories.CinemaRoomSeatingPlanRepository;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureMockMvc
public class CinemaRoomControllerTest {
	
	MockMvc mvc;
	
	@MockBean
	CinemaRoomRepository repo;
	
	@MockBean
	CinemaRepository cinemaRepository;
	
	@MockBean
	CinemaRoomSeatingPlanRepository cinemaRoomSeatingPlanRepository;
    
    @Autowired
    WebApplicationContext wac;
	
	JacksonTester<CinemaRoom> jt;
	JacksonTester<CinemaRoomConfigurationObject> jtco;
	
	static UUID uuid;
	
	@BeforeAll
	static void beforeAll() {
		uuid = new UUID(2, 2);
	}

    @BeforeEach
    void beforeEach() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    CinemaRoom getCinemaRoom() {
    	CinemaRoom c = new CinemaRoom(2, true, "testRoom");
    	c.setId(uuid);
    	return c;
    }
    
    Optional<CinemaRoom> getOptionalCinemaRoom() {
    	CinemaRoom c = getCinemaRoom();
    	return Optional.of(c);
    }
    
    CinemaRoomSeatingPlan getCinemaRoomSeatingPlan() {
    	CinemaRoomSeatingPlan c = new CinemaRoomSeatingPlan(20);
    	c.setReihen(5);
    	c.setSeats(20);
    	c.setId(uuid);
    	return c;
    }
    
    Optional<CinemaRoomSeatingPlan> getOptionalCinemaRoomSeatingPlan() {
    	CinemaRoomSeatingPlan c = getCinemaRoomSeatingPlan();
    	return Optional.of(c);
    }
    
    Cinema getCinema() {
    	Cinema c = new Cinema("Kino", "Kinostrasse", "11D", 5, 2, new City(0, null));
    	c.setId(uuid);
    	return c;
    }
    
    Optional<Cinema> getOptionalCinema() {
    	Cinema c = getCinema();
    	return Optional.of(c);
    }
    
    @Test
    void testGetAll() throws Exception {
    	when(repo.findAll()).thenReturn(new ArrayList<CinemaRoom>());
        mvc.perform(get("/cinemaRoom/getAll"))
                .andExpect(status().isOk());
    }
    
    @Test
    void testGetById() throws Exception {
        when(repo.findById(uuid)).thenReturn(getOptionalCinemaRoom());
        MockHttpServletResponse response = mvc.perform(get("/cinemaRoom/"+uuid)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse();
        assertEquals(jt.write(getCinemaRoom()).getJson(), response.getContentAsString());
    }
    
    @Test
    void testGetByIdException() throws Exception {
        mvc.perform(get("/cinemaRoom/"+new UUID(0, 0))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void testPut() throws Exception{
        
        mvc.perform(
            put("/cinemaRoom/add/").contentType(MediaType.APPLICATION_JSON).content(jt.write(getCinemaRoom()).getJson()))
        		.andExpect(status().isCreated());
        
        when(cinemaRepository.findById(uuid)).thenReturn(getOptionalCinema());
        when(cinemaRoomSeatingPlanRepository.findById(uuid)).thenReturn(getOptionalCinemaRoomSeatingPlan());
        mvc.perform(
            put("/cinemaRoom/add/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new CinemaRoomConfigurationObject(2, true, "testRoom", uuid, uuid)).getJson()))
        		.andExpect(status().isCreated());

    }

    @Test
    void testPutException() throws Exception{
        
        mvc.perform(
            put("/cinemaRoom/add/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new CinemaRoomConfigurationObject(2, true, "testRoom", null, uuid)).getJson()))
        		.andExpect(status().isNotFound());
        
        mvc.perform(
            put("/cinemaRoom/add/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new CinemaRoomConfigurationObject(2, true, "testRoom", uuid, null)).getJson()))
        		.andExpect(status().isNotFound());

    }

    @Test
    void testUpdate() throws Exception{

        when(repo.findById(uuid)).thenReturn(getOptionalCinemaRoom());
        when(cinemaRepository.findById(uuid)).thenReturn(getOptionalCinema());
        when(cinemaRoomSeatingPlanRepository.findById(uuid)).thenReturn(getOptionalCinemaRoomSeatingPlan());
        mvc.perform(
            put("/cinemaRoom/"+uuid+"/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new CinemaRoomConfigurationObject(2, true, "testRoom", uuid, uuid)).getJson()))
        		.andExpect(status().isOk());
        mvc.perform(
            put("/cinemaRoom/"+uuid+"/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new CinemaRoomConfigurationObject(2, true,"testRoom", null, null)).getJson()))
        		.andExpect(status().isOk());

    }

    @Test
    void testUpdateException() throws Exception{
    	
        mvc.perform(
                put("/cinemaRoom/"+uuid+"/")
                	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new CinemaRoomConfigurationObject(2, true, "testRoom", uuid, uuid)).getJson()))
            		.andExpect(status().isNotFound());
    	
        mvc.perform(
                put("/cinemaRoom/"+uuid+"/")
                	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new CinemaRoomConfigurationObject(2, true, "testRoom", null, uuid)).getJson()))
            		.andExpect(status().isNotFound());
    	
        mvc.perform(
                put("/cinemaRoom/"+uuid+"/")
                	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new CinemaRoomConfigurationObject(2, true, "testRoom", uuid, null)).getJson()))
            		.andExpect(status().isNotFound());

        when(repo.findById(uuid)).thenReturn(getOptionalCinemaRoom());
        mvc.perform(
            put("/cinemaRoom/"+uuid+"/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new CinemaRoomConfigurationObject(2, true, "testRoom", null, uuid)).getJson()))
        		.andExpect(status().isNotFound());

        when(repo.findById(uuid)).thenReturn(getOptionalCinemaRoom());
        mvc.perform(
            put("/cinemaRoom/"+uuid+"/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new CinemaRoomConfigurationObject(2, true, "testRoom", uuid, null)).getJson()))
        		.andExpect(status().isNotFound());

    }

    @Test
    void testDelete() throws Exception{

    	when(repo.findById(uuid)).thenReturn(getOptionalCinemaRoom());
        mvc.perform(
            delete("/cinemaRoom/"+uuid+"/"))
        		.andExpect(status().isOk());

    }

    @Test
    void testDeleteException() throws Exception{
        
        mvc.perform(
            delete("/cinemaRoom/"+uuid+"/"))
        		.andExpect(status().isNotFound());

    }
}
