package com.example.practice.notice.controller;

import com.example.practice.notice.entity.Notice;
import com.example.practice.notice.exception.AlreadyDeletedException;
import com.example.practice.notice.exception.DuplicateNoticeException;
import com.example.practice.notice.exception.NoticeNotFoundException;
import com.example.practice.notice.model.NoticeDeleteInput;
import com.example.practice.notice.model.NoticeInput;
import com.example.practice.notice.model.NoticeModel;
import com.example.practice.notice.model.ResponseError;
import com.example.practice.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ApiNoticeController {

    private final NoticeRepository noticeRepository;

    @GetMapping("/api/notice2")
    //임의로 설정한 notice(공지사항) 객체 반환
    public NoticeModel notice() {

        LocalDateTime regDate = LocalDateTime.of(2021, 2, 8, 0, 0);

        NoticeModel notice = new NoticeModel();
        notice.setId(1);
        notice.setTitle("공지사항입니다");
        notice.setContents("공지사항 내용입니다");
        notice.setRegDate(regDate);

        return notice;
    }

    @GetMapping("/api/notice")
    //임의로 설정한 notice 두개 리스트로 묶어서 반환
    public List<NoticeModel> notice2() {

        List<NoticeModel> noticeModelList = new ArrayList<>();

        LocalDateTime regDate2 = LocalDateTime.of(2021, 1, 31, 0, 0);

        noticeModelList.add(NoticeModel.builder()
                .Id(1)
                .title("공지사항입니다.")
                .contents("공지사항내용입니다.")
                .regDate(LocalDateTime.of(2021, 1, 30, 0, 0))
                .build());

        noticeModelList.add(NoticeModel.builder()
                .Id(2)
                .title("두번째 공지사항입니다.")
                .contents("두번째 공지사항 내용입니다.")
                .regDate(regDate2)
                .build());

        return noticeModelList;
    }

    @GetMapping("/api/notice/{id}")
    //notice id로 탐색 후 없으면 예외처리
    public Notice getNotice(@PathVariable Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        return notice;
    }

    @ExceptionHandler(NoticeNotFoundException.class)
    public ResponseEntity<String> handlerNoticeNotFoundException(NoticeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/notice/{id}")
    public void updateNotice(@RequestBody NoticeInput noticeInput, @PathVariable Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        notice.setTitle(noticeInput.getTitle());
        notice.setContents(noticeInput.getContents());
        notice.setUpdateDate(LocalDateTime.now());
        noticeRepository.save(notice);
    }

    @PatchMapping("api/notice/{id}/hits")
    public void noticeHits(@PathVariable Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        notice.setHits(notice.getHits() + 1);
        noticeRepository.save(notice);
    }

    @ExceptionHandler(AlreadyDeletedException.class)
    public ResponseEntity<String> handlerAlreadyDeletedException(AlreadyDeletedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
    }

    @DeleteMapping("api/notice/{id}")
    public void deleteNotice(@PathVariable Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
        if (notice.isDeleted()) {
            throw new AlreadyDeletedException("이미 삭제된 글입니다.");
        }

        notice.setDeleted(true);
        notice.setDeletedDate(LocalDateTime.now());

        noticeRepository.save(notice);
    }

    @DeleteMapping("api/notice")
    public void deleteNoticeList(@RequestBody NoticeDeleteInput noticeDeleteInput) {

        List<Notice> noticeList = noticeRepository.findByIdIn(noticeDeleteInput.getIdList())
                .orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        noticeList.stream().forEach(e -> {
            e.setDeleted(true);
            e.setDeletedDate(LocalDateTime.now());
        });

        noticeRepository.saveAll(noticeList);
    }

    @DeleteMapping("api/notice/all")
    public void deleteAll() {

        noticeRepository.deleteAll();
    }

//    @PostMapping("api/notice")
//    public ResponseEntity<Object> addNotice(@RequestBody @Valid NoticeInput noticeInput
//            , Errors errors) {
//
//        if (errors.hasErrors()) {
//            List<ResponseError> responseErrors = new ArrayList<>();
//
//            errors.getAllErrors().stream().forEach(e->{
//                responseErrors.add(ResponseError.of((FieldError) e));
//            });
//
//            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
//        }
//
//        noticeRepository.save(Notice.builder()
//                .title(noticeInput.getTitle())
//                .contents(noticeInput.getContents())
//                .hits(0)
//                .likes(0)
//                .regDate(LocalDateTime.now())
//                .build());
//
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/api/notice/latest/{size}")
    public Page<Notice> noticeLatest(@PathVariable int size) {

        Page<Notice> noticeList = noticeRepository.findAll(PageRequest.of(0, size, Sort.Direction.DESC, "regDate"));

        return noticeList;
    }

    @ExceptionHandler(DuplicateNoticeException.class)
    public ResponseEntity<?> handlerDuplicateNoticeException(DuplicateNoticeException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("api/notice")
    public void addNotice(@RequestBody NoticeInput noticeInput) {

        LocalDateTime checkDate = LocalDateTime.now().minusMinutes(1);

        int noticeCount = noticeRepository.countByTitleAndContentsAndRegDateIsGreaterThanEqual(
                noticeInput.getTitle()
                , noticeInput.getContents()
                , checkDate);
        if (noticeCount > 0) {
            throw new DuplicateNoticeException("1분이내에 등록된 동일한 공지사항이 존재합니다.");
        }

        noticeRepository.save(Notice.builder()
                .title(noticeInput.getTitle())
                .contents(noticeInput.getContents())
                .hits(0)
                .likes(0)
                .regDate(LocalDateTime.now())
                .build());
    }
}
