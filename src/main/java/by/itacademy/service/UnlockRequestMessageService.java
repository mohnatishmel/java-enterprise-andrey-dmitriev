package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.persistance.jpa.dao.impl.UnlockRequestMessageJpaDao;

import java.util.List;

public class UnlockRequestMessageService {

    private static UnlockRequestMessageService instance;

    private UnlockRequestMessageJpaDao unlockRequestMessageJpaDao;

    {
        unlockRequestMessageJpaDao = UnlockRequestMessageJpaDao.getInstance();
    }

    private UnlockRequestMessageService() {
    }

    public List<UnlockRequestMessage> getUnlockRequestMessages() throws ApplicationBasedException {
        return unlockRequestMessageJpaDao.getAll();
    }

    public void createUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        unlockRequestMessageJpaDao.create(request);
    }

    public void deleteUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        unlockRequestMessageJpaDao.delete(request.getId());
    }

    public static UnlockRequestMessageService getInstance() {
        if (instance == null) {
            instance = new UnlockRequestMessageService();
        }
        return instance;
    }
}
