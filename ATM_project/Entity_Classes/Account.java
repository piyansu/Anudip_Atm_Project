package atm.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID")
    private int accountID;

    @Column(name = "CustomerID")
    private int customerID;

    @Column(name = "AccountType")
    private String accountType;

    @Column(name = "OpeningDate")
    private Date openingDate;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @OneToOne(mappedBy = "account")
    private CardInfo cardInfo;
}
