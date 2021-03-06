package com.alexeymiroshnikov.graduation.service;

import com.alexeymiroshnikov.graduation.model.User;
import com.alexeymiroshnikov.graduation.repository.UserRepository;
import com.alexeymiroshnikov.graduation.util.UserUtil;
import com.alexeymiroshnikov.graduation.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.alexeymiroshnikov.graduation.AuthorizedUser;
import com.alexeymiroshnikov.graduation.to.UserTo;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.alexeymiroshnikov.graduation.util.ValidationUtil.checkNotFound;
import static com.alexeymiroshnikov.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(UserUtil.prepareToSave(user, passwordEncoder));
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return ValidationUtil.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(UserUtil.prepareToSave(user, passwordEncoder)), user.getId());
    }

    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = UserUtil.updateFromTo(get(userTo.getId()), userTo);
        repository.save(UserUtil.prepareToSave(user, passwordEncoder));
    }

    @Override
    public List<User> getAll() {
        return repository.findAll(SORT_NAME);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

}
