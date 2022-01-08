package de.wi2020sebgroup1.cinema.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import de.wi2020sebgroup1.cinema.entities.User;
import de.wi2020sebgroup1.cinema.repositories.UserRepository;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class UserServiceTest {
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	UserService userService;
    
    User getUser() {
    	UUID uuid = new UUID(2,2);
    	User s = new User();
    	s.setId(uuid);
    	return s;
    }
    
    Optional<User> getOptionalUser() {
    	User s = getUser();
    	return Optional.of(s);
    }
	
	@Test
	void testResolve() {
		when(userRepository.findById(getUser().getId())).thenReturn(getOptionalUser());
		assertDoesNotThrow(new Executable() {
            @Override
            public void execute() {
            	userService.resolve(getUser().getId());                
            }
        });
	}
	
	@Test
	void testResolveException() {
		assertDoesNotThrow(new Executable() {
            @Override
            public void execute() {
            	userService.resolve(getUser().getId());                
            }
        });
	}

}
