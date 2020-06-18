package edu.iis.mto.testreactor.atm;

import static org.hamcrest.MatcherAssert.assertThat;

import edu.iis.mto.testreactor.atm.bank.Bank;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Currency;

@ExtendWith(MockitoExtension.class)
public class ATMachineTest {

    @Mock
    private Bank bank;
    private Currency currency;

    @BeforeEach
    public void setUp() throws Exception {
        ATMachine atMachine = new ATMachine(bank, currency);
    }

    @Test
    public void itCompiles() {
        assertThat(true, Matchers.equalTo(true));
    }

}
