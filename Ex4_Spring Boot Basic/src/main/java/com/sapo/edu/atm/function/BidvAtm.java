package com.sapo.edu.atm.function;

import com.sapo.edu.customer.Model.Customer;
import com.sapo.edu.print.Interface.Printer;
import com.sapo.edu.atm.interfaceATM.Atm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class BidvAtm implements Atm {

    @Autowired
    @Qualifier("printerConsole")
    private  Printer printer2 ;
    @Autowired
    @Qualifier("printerFile")
    private  Printer printerFile;

//    public BidvAtm( @Qualifier("printerConsole") Printer printer2, @Qualifier("printerFile") Printer printerFile) {
//        this.printer2 = printer2;
//        this.printerFile = printerFile;
//    }
// Lấy giá trị trong application.yml
    @Value("${atm.balance}")
    private BigDecimal moneyAtm ;

    /**
     * Rút tiền
     * @param customer
     * @param amount
     */
    @Override
    public void withDraw(Customer customer, BigDecimal amount) { // Rút tiền khỏi ATM
        if (amount == null || amount.compareTo(new BigDecimal(0)) < 0) { // Giá trị amount không hợp lệ ( < 0 hoặc null )
            printer2.printMessage(" Amount is invalid");
            printerFile.printMessage("Customer "+ customer.getAcctNo()+" : Amount is invalid");
            return;
        }
        if (customer.getBalance().compareTo(amount) < 0) {
            printer2.printMessage("Insufficient Balance");
            printerFile.printMessage("Customer "+ customer.getAcctNo()+" : Insufficient Balance");
            return;
        }
        if (amount.compareTo(moneyAtm) >= 0) {
            printer2.printMessage("ATM is out of money");
            printerFile.printMessage("Customer "+ customer.getAcctNo()+" : ATM is out of money");
            return;
        }

        BigDecimal currentBalance = customer.getBalance();
        customer.setBalance(currentBalance.subtract(amount));
        moneyAtm = moneyAtm.subtract(amount);
    }

    /**
     * Hiển thị số tiền trong ATM
     */
    @Override
    public void printCurrentMoney() { // Hiển thị số tiền trong ATM
        printer2.printMessage("Current ATM money is " + moneyAtm.toString());
        printerFile.printMessage("Current ATM money is " + moneyAtm.toString());
    }

    /**
     * Nạp tiền vào tài khoản KH
     * @param customer
     * @param amount
     */
    @Override
    public void deposit(Customer customer, BigDecimal amount) { // Nạp tiền vào
        if (amount == null || amount.compareTo(new BigDecimal(0)) <= 0) {
            printer2.printMessage("Amount is invalid");
            printerFile.printMessage("Customer "+ customer.getAcctNo()+"Amount is invalid");
            return;
        }
        BigDecimal currentBalance = customer.getBalance();
        customer.setBalance(currentBalance.add(amount));
        moneyAtm = moneyAtm.add(amount);
    }

    /**
     * Hiển thị tài khoản khách
     * @param customer
     */
    @Override
    public void displayCustomerInfo(Customer customer) {
        printer2.printCustoner(customer);
        printerFile.printMessage("CustomerId: " + customer.getAcctNo() + ", balance: " + customer.getBalance().toString());
    }
}
