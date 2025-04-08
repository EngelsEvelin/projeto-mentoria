package desafio.java.payflow.services;

import desafio.java.payflow.domain.user.User;
import desafio.java.payflow.domain.user.UserType;
import desafio.java.payflow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transação");
        }
        if (sender.getBalance().compareTo(amount)< 0){
            throw new Exception("Saldo insuficiente");
        }
    }
}
