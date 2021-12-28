package de.wi2020sebgroup1.cinema.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.wi2020sebgroup1.cinema.entities.Seat;
import de.wi2020sebgroup1.cinema.helper.SemaphoreVault;
import de.wi2020sebgroup1.cinema.repositories.BookingRepositroy;
import de.wi2020sebgroup1.cinema.repositories.SeatRepository;
import de.wi2020sebgroup1.cinema.repositories.ShowRepository;
import de.wi2020sebgroup1.cinema.repositories.TicketRepository;

@Service
public class SeatService {
	
	@Autowired
	SeatRepository seatRepository;
	
	@Autowired
	ShowRepository showRepository;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	BookingRepositroy bookingRepositroy;
	
	@Autowired
	SemaphoreVault semaphoreVault;
	
	public boolean reserveSeats(ArrayList<UUID> seats, UUID showId) {
		
		boolean allFreeAndReserved = true;
		ArrayList<Seat> booked = new ArrayList<>();
		
		try {
			semaphoreVault.getSemaphore(showId).acquire();
			for(UUID seat:seats) {
				
				Seat toBook = seatRepository.findById(seat).get();
				if(!toBook.isBlocked()) {
					toBook.setBlocked(true);
					seatRepository.save(toBook);
					booked.add(toBook);
				}else {
					allFreeAndReserved = false;
					break;
				}
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			semaphoreVault.getSemaphore(showId).release();
		}
		
		
		if(allFreeAndReserved) {
			return true;
		}else {
			for(Seat seat:booked) {
				seat.setBlocked(false);
				seatRepository.save(seat);
			}
			return false;
		}
		
	}

}
