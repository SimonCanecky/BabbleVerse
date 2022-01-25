package com.babbleverse.request;

import com.babbleverse.user.User;
import com.babbleverse.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class RequestServiceImpl implements RequestService{

    private RequestRepository requestRepository;
    private UserService userService;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, UserService userService) {
        this.requestRepository = requestRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Request> getRequestInfo(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public Request createNewRequest(Request request) {
        request.setSender(userService.getCurrentUser());
        request.getSender().addSentRequest(request);
        request.getReceiver().addReceivedRequest(request);
        return requestRepository.save(request);
    }

    @Override
    public void requestAccepted(Request request1) {
        Request request = requestRepository.findById(request1.getId()).orElseThrow();
        switch (request.getRequestType()){
            case groupInvite:

            case friendRequest:
                if (request.getReceiver().equals(userService.getCurrentUser()))
                    request.getSender().addFriend(request.getReceiver());
        }
    }

    @Override
    public void requestRejected(Request request) {
        request.getSender().getReceivedRequests().remove(request);
        request.setRequestIsActive(false);
    }
}
