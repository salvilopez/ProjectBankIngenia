package com.ingenia.projectbank.dao.DaoImpl;

import com.ingenia.projectbank.dao.BankCardDao;
import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.BankCard;
import com.ingenia.projectbank.model.User;
import com.ingenia.projectbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BankCardDaoImpl implements BankCardDao {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<BankCard> findBankCardsByUser(User user) {
        User userOpt = manager.find(User.class,user.getId());

        if(userOpt != null){
            List<Account> accounts = userOpt.getAccounts();
            for(Account account: accounts){
               return account.getCards();
            }
        }
        return null;
    }

    @Override
    public List<BankCard> findBankCardsByAccount(Account account) {
        Account accountOpt = manager.find(Account.class,account.getId());

        if(accountOpt!= null){
            List<BankCard> bankCards = accountOpt.getCards();
            return bankCards;
        }
        return null;
    }

    @Override
    public List<BankCard> findBankCardsByUserId(Long id) {

        if(id!=null) {
            User userOpt = manager.find(User.class, id);
            List<BankCard> bankCards = new ArrayList<>();
            if (userOpt != null) {
                for (int i = 0; i < userOpt.getAccounts().size(); i++) {


                    for (int j = 0; j < userOpt.getAccounts().get(i).getCards().size(); j++) {
                        bankCards.add(userOpt.getAccounts().get(i).getCards().get(j));
                    }

                }
                return bankCards;
            }

        }

        return null;
    }

}

