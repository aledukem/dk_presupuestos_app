package dkpc.projects.service;

import dkpc.projects.dao.UserRepository;
import dkpc.projects.model.User;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UserRepository userRepository;

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }
}
