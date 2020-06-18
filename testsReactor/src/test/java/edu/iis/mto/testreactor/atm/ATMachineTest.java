package edu.iis.mto.testreactor.atm;

import static edu.iis.mto.testreactor.atm.Banknote.PL_10;
import static org.hamcrest.MatcherAssert.assertThat;
import edu.iis.mto.testreactor.atm.bank.AuthorizationException;
import edu.iis.mto.testreactor.atm.bank.AuthorizationToken;
import edu.iis.mto.testreactor.atm.bank.Bank;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.objenesis.instantiator.basic.NewInstanceInstantiator;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ATMachineTest {

    @Mock
    private Bank bank;
    private ATMachine atMachine;
    private Currency currency;
    Currency DEFAULT_CURRENCY = Currency.getInstance("PLN");

    @BeforeEach
    public void setUp() throws Exception {
        atMachine = new ATMachine(bank, DEFAULT_CURRENCY);

    }

    @Test
    void checkIfGetATMOperation() throws AuthorizationException, ATMOperationException {

        Money anyMoney = new Money(10,DEFAULT_CURRENCY);

        PinCode anyCode = PinCode.createPIN(1, 1, 2, 3);
        Card anyCard = Card.create("12");
        atMachine.withdraw(anyCode, anyCard, anyMoney);

        AuthorizationToken test;
        test = bank.autorize(anyCode.getPIN(), anyCard.getNumber());
        BanknotesPack properBanknotes = new BanknotesPack(PL_10,1);
        atMachine.calculateWithdrawal(anyMoney);
        atMachine.performBankTransaction(test, anyMoney);

        //Withdrawal.create(atMachine.release());

        Assertions.assertTrue(test.equals(true));

//        assertFalse(test.equals(false));

//        assertThrows(ATMOperationException.class, () -> {
//            AuthorizationToken test;
//        });
    }


    @Test
    void checkAnyThing() throws ATMOperationException, AuthorizationException {
        Money anyMoney = new Money(10,DEFAULT_CURRENCY);
        PinCode anyCode = PinCode.createPIN(1, 1, 2, 3);
        Card anyCard = Card.create("");
        atMachine.calculateWithdrawal(anyMoney);
        atMachine.withdraw(anyCode, anyCard, anyMoney);
        AuthorizationToken test;
        test = bank.autorize(anyCode.getPIN(), anyCard.getNumber());



        Assertions.assertTrue(test.equals(true));
    }

    @Test
    public void itCompiles() {
        assertThat(true, Matchers.equalTo(true));
    }

}
