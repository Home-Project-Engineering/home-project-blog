package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.models.User;
import com.softserveinc.ita.home.home_project_blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User save(User user) {
        if (repository.existsById(user.getId())) {
            user.setId(-1L);
        }
        return repository.save(user);
    }

    @Override
    public Optional<User> update(User user) {
        //return repository.findById(id).map(value -> repository.save(value)).orElseThrow();
        if (repository.existsById(user.getId())) {
            return Optional.of(repository.save(user));
        }
        return Optional.empty();

//        User oldUser = repository.findById();
//        if (Objects.nonNull(oldUser)) {
//            oldUser.setName(user.getName());
//        }
//        return Optional.ofNullable(oldUser);
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;//throw?
    }
/*
    //@Override
    public Set<User> getByName(String name) {
        Map<Long,User> respUser = new HashMap<Long,User>();
        for (Long key: users.keySet()){
            User user = users.get(key);
            if (user.getName().equalsIgnoreCase(name)){
                respUser.put(key,user);
            }
        }
        return new HashSet<User>(respUser.values());
    }
*/
}
