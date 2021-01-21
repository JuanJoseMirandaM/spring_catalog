package bo.juanjose.catalog.service;

import bo.juanjose.catalog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    public Page<User> listAllUser(Pageable pageable);
    public Optional<User> getUser(Long id);
    public User createUser(User user);
    public Optional<User> updateUser(User user);
    public Optional<User> deleteUser(Long id);
}
