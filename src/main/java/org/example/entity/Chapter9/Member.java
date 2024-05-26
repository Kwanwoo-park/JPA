package org.example.entity.Chapter9;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;

    @Embedded
    Address homeAddress;
//    private String name;

//    @Embedded Period workPeriod;
//    @Embedded PhoneNumber phoneNumber;
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
//            @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
//            @AttributeOverride(name = "zipcode", column = @Column(name = "COMPANY_ZIPCODE"))
//    })
//    Address companyAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOODS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressesHistory = new ArrayList<>();

//    @Temporal(TemporalType.DATE)
//    Date startDate;
//    @Temporal(TemporalType.DATE)
//    Date endDate;
//
//    private String city;
//    private String street;
//    private String zipcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public void setStreet(String street) {
//        this.street = street;
//    }
//
//    public String getZipcode() {
//        return zipcode;
//    }
//
//    public void setZipcode(String zipcode) {
//        this.zipcode = zipcode;
//    }

//    public Period getWorkPeriod() {
//        return workPeriod;
//    }
//
//    public void setWorkPeriod(Period workPeriod) {
//        this.workPeriod = workPeriod;
//    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

//    public PhoneNumber getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(PhoneNumber phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public Address getCompanyAddress() {
//        return companyAddress;
//    }
//
//    public void setCompanyAddress(Address companyAddress) {
//        this.companyAddress = companyAddress;
//    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressesHistory() {
        return addressesHistory;
    }

    public void setAddressesHistory(List<Address> addressesHistory) {
        this.addressesHistory = addressesHistory;
    }
}
