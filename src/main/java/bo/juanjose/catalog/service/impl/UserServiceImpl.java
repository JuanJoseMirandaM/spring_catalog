package bo.juanjose.catalog.service.impl;

import bo.juanjose.catalog.entity.User;
import bo.juanjose.catalog.repository.UserRepository;
import bo.juanjose.catalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> listAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setStatus("CREATED");
        user.setUser(User.builder().id(1L).build());

        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateUser(User user) {
        Optional<User> userDB = getUser(user.getId());
        return userDB.map(userdb -> {
            userdb.setName(user.getName());
            userdb.setUsername(user.getUsername());
            userdb.setEmail(user.getEmail());
            userdb.setPassword(user.getPassword());

            userdb.setUpdatedAt(new Date());
            userdb.setUser(User.builder().id(1L).build());

            return userRepository.save(userdb);
        });
    }

    @Override
    public Optional<User> deleteUser(Long id) {
        Optional<User> userDeleted = getUser(id);
        return userDeleted.map(user -> {
            user.setStatus("DELETED");
            user.setUpdatedAt(new Date());
            user.setUser(User.builder().id(1L).build());

            return userRepository.save(user);
        });
    }
}
