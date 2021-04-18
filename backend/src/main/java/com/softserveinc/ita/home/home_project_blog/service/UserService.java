package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.models.UpdateUser;
import com.softserveinc.ita.home.home_project_blog.models.User;
import com.softserveinc.ita.home.home_project_blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private UserRepository repository;

    @Override
//    public List<User> findAll() {
//        return (List<User>) repository.findAll();
//    }
    public Page<User> findAll(Integer pageNum, Integer pageSize, String sortBy) {
        Pageable paging;
        if (sortBy.charAt(0) == '-') {
            paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy.substring(1)).descending());
        } else {
            if (sortBy.charAt(0) == '+') {
                sortBy = sortBy.substring(1);
            }
            paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy).ascending());
        }
        return repository.findAll(paging);
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
    public Optional<User> update(Long id, UpdateUser user) {
        //return repository.findById(id).map(value -> repository.save(value)).orElseThrow();

        if (repository.existsById(id)) {
            User oldUser = repository.findById(id).get();
            oldUser.setLastName(user.getLastName());
            oldUser.setFirstName(user.getFirstName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setRole(user.getRole());
            return Optional.of(repository.save(oldUser));
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
