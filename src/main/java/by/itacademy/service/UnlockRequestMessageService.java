package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.model.message.UnlockRequestMessage;
import by.itacademy.persistance.jdbc.dao.message.UnlockRequestMessageJdbcDao;

import java.util.List;

public class UnlockRequestMessageService {

    private static UnlockRequestMessageService instance;

    private UnlockRequestMessageJdbcDao unlockRequestMessageJdbcDao;

    {
        unlockRequestMessageJdbcDao = UnlockRequestMessageJdbcDao.getInstance();
    }

    private UnlockRequestMessageService() {
    }

    public List<UnlockRequestMessage> getUnlockRequestMessages() throws ApplicationBasedException {
        return unlockRequestMessageJdbcDao.getAll();
    }

    public void createUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        unlockRequestMessageJdbcDao.create(request);
    }

    public void deleteUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        unlockRequestMessageJdbcDao.delete(request.getId());
    }

    public static UnlockRequestMessageService getInstance() {
        if (instance == null) {
            instance = new UnlockRequestMessageService();
        }
        return instance;
    }
}
