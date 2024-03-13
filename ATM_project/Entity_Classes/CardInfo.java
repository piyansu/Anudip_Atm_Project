package atm.entity;

import javax.persistence.*;

@Entity
@Table(name = "cardinfo")
public class CardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID")
    private int accountID;

    @Column(name = "cardno", unique = true)
    private String cardNumber;

    @Column(name = "pin")
    private String pin;

    @OneToOne
    @JoinColumn(name = "AccountID")
    private Account account;
}
