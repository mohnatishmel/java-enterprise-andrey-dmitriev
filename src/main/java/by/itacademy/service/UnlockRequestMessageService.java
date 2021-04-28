package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.persistence.UnlockRequestMessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnlockRequestMessageService {

    private UnlockRequestMessageDao unlockRequestMessageDao;

    @Autowired
    public UnlockRequestMessageService(UnlockRequestMessageDao unlockRequestMessageDao) {
        this.unlockRequestMessageDao = unlockRequestMessageDao;
    }


    public List<UnlockRequestMessage> getUnlockRequestMessages() throws ApplicationBasedException {
        try {
            return (List<UnlockRequestMessage>) unlockRequestMessageDao.findAll();

        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public void createUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        try {
            unlockRequestMessageDao.save(request);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public void deleteUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        try {
            unlockRequestMessageDao.deleteById(request.getId());
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public void deleteByUserId(int id) throws ApplicationBasedException {
        unlockRequestMessageDao.deleteByUserId(id);
    }
}
