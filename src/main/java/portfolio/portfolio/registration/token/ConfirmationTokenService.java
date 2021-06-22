package portfolio.portfolio.registration.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.portfolio.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {


    private final ConfirmationTokenRepository confirmationTokenRepository;


    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public void deleteOldToken(User user){
        confirmationTokenRepository.deleteByUser(user);
    }
}
