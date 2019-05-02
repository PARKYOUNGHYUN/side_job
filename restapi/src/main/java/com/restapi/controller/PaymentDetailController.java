package com.restapi.controller;

import java.util.Optional;

import com.restapi.entity.PaymentDetail;
import com.restapi.entity.PaymentDetailId;
import com.restapi.repository.PaymentDetailRepository;
import com.restapi.service.PaymentDetailService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 決済内訳操作のコントローラ
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/paymentDetails")
public class PaymentDetailController {

    @Autowired
    private PaymentDetailService progresService;

    @NonNull
    private final PaymentDetailRepository progresRepository;

    /**
     * 決済内訳検索
     *
     * @param id 決済内訳ID
     * @return 決済内訳
     */
    @GetMapping
    public ResponseEntity<PaymentDetail> getPaymentDetailById(@RequestBody PaymentDetailId epId) {
        Optional<PaymentDetail> paymentDetail = progresService.getPaymentDetail(epId);
        if(paymentDetail.isPresent())
            return new ResponseEntity<PaymentDetail>(paymentDetail.get(), HttpStatus.OK);
        return new ResponseEntity<PaymentDetail>(HttpStatus.NOT_FOUND);
    }

    /**
     * 決済内訳登録
     *
     * @param progresBody リクエストボディ
     * @return 更新後の決済内訳
     */
    @PutMapping
    public ResponseEntity<PaymentDetail> createPaymentDetail(@RequestBody @Validated PaymentDetail progress) {
        PaymentDetail createdProgress = progresService.createPaymentDetail(progress);
        if (createdProgress != null)
            return new ResponseEntity<PaymentDetail>(createdProgress, HttpStatus.CONFLICT);
        return new ResponseEntity<PaymentDetail>(HttpStatus.CREATED); 
    }

    /**
     * 決済内訳修正
     *
     * @param progresBody リクエストボディ
     * @return 更新後の決済内訳
     */
    // @PatchMapping
    // public ResponseEntity<PaymentDetail> patchPaymentDetail(@RequestBody @Valid PaymentDetail progress) {
    //     PaymentDetail fetchedProgress = progresService.patchPaymentDetail(progress); 
    //     if(fetchedProgress != null)
    //         return new ResponseEntity<PaymentDetail>(fetchedProgress, HttpStatus.OK);
    //     return new ResponseEntity<PaymentDetail>(HttpStatus.NOT_FOUND);
    // }
}
