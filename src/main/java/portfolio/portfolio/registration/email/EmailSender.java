package portfolio.portfolio.registration.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.portfolio.model.Email;

@Repository
public interface EmailSender  {

    void send (String to, String email);
}
