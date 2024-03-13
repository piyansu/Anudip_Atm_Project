package atm.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID")
    private int transactionID;

    @Column(name = "AccountID")
    private int accountID;

    @Column(name = "ATM_ID")
    private int atmID;

    @Column(name = "TransactionType")
    private String transactionType;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "AccountID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "ATM_ID")
    private ATM atm;
}
