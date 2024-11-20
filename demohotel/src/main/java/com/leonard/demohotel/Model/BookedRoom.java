package com.leonard.demohotel.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "check_in")
    private LocalDate checkInDate;

    @Column(name = "check_out")
    private LocalDate checkOutDate;

    @Column(name = "guest_fullname")
    private String guestFullName;

    @Column(name = "guest_email")
    private String guestEmail;

    @Column(name = "adults")
    private int NumberofAdult;

    @Column(name = "children")
    private int NumberofChildren;

    @Column(name = "total_guest")
    private int totalNumberofGuest;

    @Column(name = "confirmation_code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    public void calculateNumberOfGuest() {
        this.totalNumberofGuest = NumberofAdult + NumberofChildren;
    }

    public void setNumberofAdult(int numberofAdult) {
        NumberofAdult = numberofAdult;
        calculateNumberOfGuest();
    }

    public void setNumberofChildren(int numberofChildren) {
        NumberofChildren = numberofChildren;
        calculateNumberOfGuest();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
