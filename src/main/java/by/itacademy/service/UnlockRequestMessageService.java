package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.persistence.jpa.dao.impl.UnlockRequestMessageJpaDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnlockRequestMessageService {
    @Autowired
    public UnlockRequestMessageService(UnlockRequestMessageJpaDao unlockRequestMessageJpaDao) {
        this.unlockRequestMessageJpaDao = unlockRequestMessageJpaDao;
    }

    private UnlockRequestMessageJpaDao unlockRequestMessageJpaDao;

    public List<UnlockRequestMessage> getUnlockRequestMessages() throws ApplicationBasedException {
        return unlockRequestMessageJpaDao.getAll();
    }

    public void createUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        unlockRequestMessageJpaDao.create(request);
    }

    public void deleteUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        unlockRequestMessageJpaDao.delete(request.getId());
    }
}
