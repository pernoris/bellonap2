package com.portfolio.nb.Service;


import com.portfolio.nb.Entity.Hys;
import com.portfolio.nb.Repository.RHys;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class SHys {
    @Autowired
    RHys rHys;

    public List<Hys> list() {
        return rHys.findAll();
    }

    public Optional<Hys> getOne(int id) {
        return rHys.findById(id);
    }

    public void save(Hys skill) {
        rHys.save(skill);
    }

    public void delete(int id) {
        rHys.deleteById(id);
    }

    public boolean existsById(int id) {
        return rHys.existsById(id);
    }      
}