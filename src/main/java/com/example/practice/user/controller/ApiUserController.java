package com.example.practice.user.controller;

import com.example.practice.notice.entity.Notice;
import com.example.practice.notice.entity.NoticeLike;
import com.example.practice.notice.model.NoticeResponse;
import com.example.practice.notice.model.ResponseError;
import com.example.practice.notice.repository.NoticeLikeRepository;
import com.example.practice.notice.repository.NoticeRepository;
import com.example.practice.user.entity.User;
import com.example.practice.user.exception.ExistsEmailException;
import com.example.practice.user.exception.PasswordNotMathException;
import com.example.practice.user.exception.UserNotFoundException;
import com.example.practice.user.model.UserInput;
import com.example.practice.user.model.UserInputFind;
import com.example.practice.user.model.UserInputPassword;
import com.example.practice.user.model.UserResponse;
import com.example.practice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ApiUserController {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;

    private final NoticeLikeRepository noticeLikeRepository;

//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach((e)->{
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//        return ResponseEntity.ok().build();
//    }

//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach((e) -> {
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = User.builder()
//                .email(userInput.getEmail())
//                .userName(userInput.getUserName())
//                .password(userInput.getPassword())
//                .phone(userInput.getPhone())
//                .regDate(LocalDateTime.now())
//                .build();
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }

    @PutMapping("api/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid User userUpdate, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });

            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        user.setPhone(userUpdate.getPhone());
        user.setUpdateDate(LocalDateTime.now());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> UserNotFoundExceptionHandler(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/user/{id}")
    public UserResponse getUser(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

//        UserResponse userResponse = new UserResponse(user);
        UserResponse userResponse = UserResponse.of(user);
        return userResponse;
    }

    @GetMapping("/api/user/{id}/notice")
    public List<NoticeResponse> userNotice(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        List<Notice> noticeList = noticeRepository.findByUser(user);

        List<NoticeResponse> noticeResponseList = new ArrayList<>();

        noticeList.stream().forEach((e) -> {
            noticeResponseList.add(NoticeResponse.of(e));
        });

        return noticeResponseList;
    }

//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//        if (errors.hasErrors()) {
//            errors.getAllErrors().stream().forEach((e) -> {
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        if (userRepository.countByEmail(userInput.getEmail()) > 0) {
//            throw new ExistsEmailException("이미 존재하는 이메일 입니다.");
//        }
//
//        User user = User.builder()
//                .email(userInput.getEmail())
//                .userName(userInput.getUserName())
//                .phone(userInput.getPhone())
//                .password(userInput.getPassword())
//                .regDate(LocalDateTime.now())
//                .build();
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }

    @ExceptionHandler(value = {ExistsEmailException.class, PasswordNotMathException.class})
    public ResponseEntity<?> ExistsEmailExceptionHandler(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/api/user/{id}/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable Long id, @RequestBody UserInputPassword userInputPassword, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByIdAndPassword(id, userInputPassword.getPassword())
                .orElseThrow(() -> new PasswordNotMathException("비밀번호가 일치하지 않습니다."));

        user.setPassword(userInputPassword.getPassword());

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    private String getEncryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    @PostMapping("/api/user")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.countByEmail(userInput.getEmail()) > 0) {
            throw new ExistsEmailException("이미 존재하는 이메일 입니다.");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptPassword = getEncryptPassword(userInput.getPassword());

        User user = User.builder()
                .email(userInput.getEmail())
                .userName(userInput.getUserName())
                .phone(userInput.getPhone())
                .password(encryptPassword)
                .regDate(LocalDateTime.now())
                .build();
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));


        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            String message = "제약조건에 문제가 발생하였습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            String message = "회원 탈퇴 중 문제가 발생하였습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/user")
    public ResponseEntity<?> findUser(@RequestBody UserInputFind userInputFind) {

        User user = userRepository.findByUserNameAndPhone(userInputFind.getUserName(), userInputFind.getPhone())
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        UserResponse userResponse = UserResponse.of(user);

        return ResponseEntity.ok().body(userResponse);
    }

    private String getResetPassword() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    @GetMapping("/api/user/{id}/password/reset")
    public ResponseEntity<?> resetUserPassword(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        String resetPassword = getResetPassword();
        String resetEncryptPassword = getEncryptPassword(resetPassword);
        user.setPassword(resetEncryptPassword);
        userRepository.save(user);

        String message = String.format("[%s]님의 임시 비밀번호가 [%s]로 초기화 되었습니다."
                , user.getUserName(), resetPassword);
        sendSMS(message);

        return ResponseEntity.ok().build();
    }

    void sendSMS(String message) {
        System.out.println("[문자메시지전송]");
        System.out.println(message);
    }

    @GetMapping("/api/user/{id}/notice/like")
    public List<NoticeLike> likeNotice(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        List<NoticeLike> noticeLikeList = noticeLikeRepository.findByUser(user);

        return noticeLikeList;
    }

}
