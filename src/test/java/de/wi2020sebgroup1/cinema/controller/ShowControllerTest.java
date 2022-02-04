package de.wi2020sebgroup1.cinema.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

import de.wi2020sebgroup1.cinema.configurationObject.ShowConfigurationObject;
import de.wi2020sebgroup1.cinema.entities.Cinema;
import de.wi2020sebgroup1.cinema.entities.CinemaRoom;
import de.wi2020sebgroup1.cinema.entities.CinemaRoomSeatingPlan;
import de.wi2020sebgroup1.cinema.entities.City;
import de.wi2020sebgroup1.cinema.entities.Movie;
import de.wi2020sebgroup1.cinema.entities.Price;
import de.wi2020sebgroup1.cinema.entities.Seat;
import de.wi2020sebgroup1.cinema.entities.SeatsBluePrint;
import de.wi2020sebgroup1.cinema.entities.Show;
import de.wi2020sebgroup1.cinema.enums.SeatState;
import de.wi2020sebgroup1.cinema.enums.SeatType;
import de.wi2020sebgroup1.cinema.repositories.CinemaRepository;
import de.wi2020sebgroup1.cinema.repositories.CinemaRoomRepository;
import de.wi2020sebgroup1.cinema.repositories.CinemaRoomSeatingPlanRepository;
import de.wi2020sebgroup1.cinema.repositories.MovieRepository;
import de.wi2020sebgroup1.cinema.repositories.SeatBluePrintRepository;
import de.wi2020sebgroup1.cinema.repositories.SeatRepository;
import de.wi2020sebgroup1.cinema.repositories.ShowRepository;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureMockMvc
public class ShowControllerTest {
	
	MockMvc mvc;
	
	@InjectMocks
	private ShowController showcontroller;
	
	@MockBean
	ShowRepository repo;
	
	@MockBean
	CinemaRepository cinemaRepository;
	
	@MockBean
	MovieRepository movieRepository;
	
	@MockBean
	CinemaRoomRepository cinemaRoomRepository;
	
	@MockBean
	CinemaRoomSeatingPlanRepository seatingPlanRepository;
	
	@MockBean
	SeatBluePrintRepository seatBluePrintRepository;
	
	@MockBean
	SeatRepository seatRepository;
    	
    @Autowired
    WebApplicationContext wac;
	
	JacksonTester<Show> jt;
	JacksonTester<ShowConfigurationObject> jtco;
	JacksonTester<CinemaRoomSeatingPlan> jt_seatingPlan;
	
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
    
    Show getShow() {
    	Show s = new Show();
    	s.setId(uuid);
    	return s;
    }
    
    Optional<Show> getOptionalShow() {
    	Show s = getShow();
    	return Optional.of(s);
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
    
    Movie getMovie() {
    	Movie m = new Movie("Shrek 3", "deutsch", null, 2.5, "Kitty Blume", "Ein Film", "localhost/img", null, null, null, null, 0);
    	m.setId(uuid);
    	return m;
    }
    
    Optional<Movie> getOptionalMovie() {
    	Movie m = getMovie();
    	return Optional.of(m);
    }
    
    CinemaRoom getCinemaRoom() {
    	CinemaRoom c = new CinemaRoom(2, true, "testRoom");
    	c.setCinemaRoomSeatingPlan(getCinemaRoomSeatingPlan());
    	c.setId(uuid);
    	return c;
    }
    
    Optional<CinemaRoom> getOptionalCinemaRoom() {
    	CinemaRoom c = getCinemaRoom();
    	return Optional.of(c);
    }
    
    Optional<CinemaRoom> getOptionalCinemaRoomWithoutLayout() {
    	CinemaRoom c = getCinemaRoom();
    	c.setCinemaRoomSeatingPlan(null);
    	return Optional.of(c);
    }
    
    Optional<List<Seat>> getOptionalSeatList() {
    	List<Seat> l = new ArrayList<>();
    	l.add(new Seat(0, 0, SeatType.PARQUET, SeatState.RESERVED, 0, getCinemaRoomSeatingPlan(), getShow()));
    	l.add(new Seat(1, 1, SeatType.PARQUET, SeatState.RESERVED, 0, getCinemaRoomSeatingPlan(), getShow()));
    	l.add(new Seat(3, 2, SeatType.PREMIUM, SeatState.RESERVED, 0, getCinemaRoomSeatingPlan(), getShow()));
    	return Optional.of(l);
    }
	
	List<SeatsBluePrint> getSBP() {
		List<SeatsBluePrint> l = new ArrayList<>();
		Price p = new Price();
		p.setId(uuid);
		CinemaRoom c = new CinemaRoom();
		c.setId(uuid);
		CinemaRoomSeatingPlan cr = new CinemaRoomSeatingPlan();
		c.setSeatingPlan(cr);
		cr.setId(uuid);
		SeatsBluePrint s1 = new SeatsBluePrint(1, 1, SeatType.LODGE, p, c, cr);
		s1.setId(uuid);
		SeatsBluePrint s2 = new SeatsBluePrint(2, 1, SeatType.LODGE, p, c, cr);
		s2.setId(uuid);
		l.add(s1);
		l.add(s2);
		return l;
	}
    
    @Test
    void testGetAll() throws Exception {
    	when(repo.findAll()).thenReturn(new ArrayList<Show>());
        mvc.perform(get("/show/getAll"))
                .andExpect(status().isOk());
    }
    
    @Test
    void testGetById() throws Exception {
        when(repo.findById(uuid)).thenReturn(getOptionalShow());
        MockHttpServletResponse response = mvc.perform(get("/show/"+uuid)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse();
        assertEquals(jt.write(getShow()).getJson(), response.getContentAsString());
    }
    
    @Test
    void testGetByIdException() throws Exception {
        mvc.perform(get("/show/"+new UUID(0, 0))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
    
    @Test
    void testGetSeatsById() throws Exception {
        when(repo.findById(uuid)).thenReturn(getOptionalShow());
        when(seatRepository.findAllByShow(getShow())).thenReturn(getOptionalSeatList());
        mvc.perform(get("/show/"+uuid+"/seats")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    
    @Test
    void testGetSeatsByIdException() throws Exception {
        mvc.perform(get("/show/"+new UUID(0, 0)+"/seats")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
        
        when(repo.findById(uuid)).thenReturn(getOptionalShow());
        mvc.perform(get("/show/"+uuid+"/seats")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        mvc.perform(get("/show/"+new UUID(0, 0)+"/seats")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        
        when(seatRepository.findAllByShow(getShow())).thenThrow(new NoSuchElementException());
        mvc.perform(get("/show/"+uuid+"/seats")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void testPut() throws Exception{
        
        mvc.perform(
            put("/show/add/").contentType(MediaType.APPLICATION_JSON).content(jt.write(getShow()).getJson()))
        		.andExpect(status().isCreated());

        when(cinemaRepository.findById(uuid)).thenReturn(getOptionalCinema());
        when(cinemaRoomRepository.findById(uuid)).thenReturn(getOptionalCinemaRoom());
        when(movieRepository.findById(uuid)).thenReturn(getOptionalMovie());
        when(seatingPlanRepository.findById(uuid)).thenReturn(getOptionalCinemaRoomSeatingPlan());
        when(seatingPlanRepository.findByCinemaRoom(getCinemaRoom())).thenReturn(getOptionalCinemaRoomSeatingPlan());
        mvc.perform(
            put("/show/add/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new ShowConfigurationObject(new Date(1), new Time(1), new Time(1), uuid, uuid, uuid)).getJson()))
        		.andExpect(status().isCreated());

    }

    @Test
    void testPut2() throws Exception{

        when(cinemaRepository.findById(uuid)).thenReturn(getOptionalCinema());
        when(movieRepository.findById(uuid)).thenReturn(getOptionalMovie());
        when(cinemaRoomRepository.findById(uuid)).thenReturn(getOptionalCinemaRoom());
        when(seatBluePrintRepository.findAllByCinemaRoom(any())).thenReturn(getSBP());
        mvc.perform(
            put("/show/add/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new ShowConfigurationObject(new Date(1), new Time(1), new Time(1), uuid, uuid, uuid)).getJson()))
        		.andExpect(status().isCreated());

    }

    @Test
    void testPutException() throws Exception{
        
        mvc.perform(
            put("/show/add/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new ShowConfigurationObject(new Date(1), new Time(1), new Time(1), null, uuid, null)).getJson()))
        		.andExpect(status().isNotFound());
        
        mvc.perform(
            put("/show/add/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new ShowConfigurationObject(new Date(1), new Time(1), new Time(1), uuid, null, null)).getJson()))
        		.andExpect(status().isNotFound());
        
        mvc.perform(
            put("/show/add/")
            	.contentType(MediaType.APPLICATION_JSON).content(jtco.write(new ShowConfigurationObject(new Date(1), new Time(1), new Time(1), null, null, uuid)).getJson()))
        		.andExpect(status().isNotFound());

    }

    @Test
    void testDelete() throws Exception{

    	when(repo.findById(uuid)).thenReturn(getOptionalShow());
        mvc.perform(
            delete("/show/"+uuid+"/"))
        		.andExpect(status().isOk());

    }

    @Test
    void testDeleteException() throws Exception{
        
        mvc.perform(
            delete("/show/"+uuid+"/"))
        		.andExpect(status().isNotFound());

    }
    
}
