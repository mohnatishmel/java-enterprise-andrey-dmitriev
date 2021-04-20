package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.persistence.UnlockRequestMessageDao;
import org.springframework.beans.factory.annotation.Autowired;
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
        return (List<UnlockRequestMessage>) unlockRequestMessageDao.findAll();
    }

    public void createUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        unlockRequestMessageDao.save(request);
    }

    public void deleteUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        unlockRequestMessageDao.deleteById(request.getId());
    }

    public void deleteByUserId(int id) throws ApplicationBasedException {
        unlockRequestMessageDao.deleteByUserId(id);
    }
}
