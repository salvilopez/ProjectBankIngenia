package com.ingenia.projectbank.dao.DaoImpl;

import com.ingenia.projectbank.dao.PrestamDao;
import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.Movement;
import com.ingenia.projectbank.model.Prestam;
import com.ingenia.projectbank.model.User;
import com.ingenia.projectbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PrestamDaoImpl implements PrestamDao {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Prestam> findPrestamsByUserId(Long id) {
        if(id!=null) {
            User user = userRepository.findById(id).get();

            List<Prestam>prestams=new ArrayList<>();
            if (user != null) {
                for (int i = 0; i <user.getAccounts().size() ; i++) {
                    for (int j = 0; j < user.getAccounts().get(i).getPrestamsIncomes().size(); j++) {
                        prestams.add(user.getAccounts().get(i).getPrestamsIncomes().get(j));
                    }

                }
                return prestams;
            }
        }
        return new ArrayList<>();
    }
}
