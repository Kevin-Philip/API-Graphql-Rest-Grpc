package com.jxc.readapis.grpc.services;

import com.jxc.readapis.grpc.UserInfosDTO;
import com.jxc.readapis.grpc.UserRequest;
import com.jxc.readapis.grpc.generated.UserServiceGrpc;
import com.jxc.readapis.grpc.mappers.UserMapper;
import com.jxc.readapis.services.UserService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    UserService userService;

    @Override
    public void findUserByEmail(UserRequest request, StreamObserver<UserInfosDTO> responseObserver) {
        com.jxc.readapis.dto.UserInfosDTO user = userService.getUserByEmail(request.getEmail());
        UserInfosDTO response = UserMapper.convertToUserInfosDTOgrpc(user);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
