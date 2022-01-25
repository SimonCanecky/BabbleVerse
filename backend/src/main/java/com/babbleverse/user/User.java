package com.babbleverse.user;

import com.babbleverse.request.Request;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String password;
    @ManyToMany
    @Column(name = "friends")
    private List<User> friends = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    @Column(name = "sent_requests")
    private List<Request> sentRequests = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    @Column(name = "received_requests")
    private List<Request> receivedRequests = new ArrayList<>();


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(){

    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nickname) {
        this.name = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
    public void addFriend(User user){
        this.friends.add(user);
    }

    public List<Request> getSentRequests() {
        return sentRequests;
    }

    public void setSentRequests(List<Request> sentRequests) {
        this.sentRequests = sentRequests;
    }

    public List<Request> getReceivedRequests() {
        return receivedRequests;
    }

    public void setReceivedRequests(List<Request> receivedRequests) {
        this.receivedRequests = receivedRequests;
    }

    public void addSentRequest(Request request){
        sentRequests.add(request);
    }

    public void addReceivedRequest(Request request){
        receivedRequests.add(request);
    }

}

