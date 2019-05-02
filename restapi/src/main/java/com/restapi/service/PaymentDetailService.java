package com.restapi.service;

import java.util.Optional;

import com.restapi.entity.PaymentDetail;
import com.restapi.entity.PaymentDetailId;
import com.restapi.repository.PaymentDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailService {

    @Autowired
    private PaymentDetailRepository paymentDetailRepository;

    public PaymentDetail createPaymentDetail(PaymentDetail detail) {
        try {
            return paymentDetailRepository.save(detail);
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<PaymentDetail> getPaymentDetail(PaymentDetailId epId) {
        return paymentDetailRepository.findById(epId);
    }

    // public PaymentDetail patchEntryDetail(PaymentDetail detail){
    //     final Optional<PaymentDetail> fetchDetail = paymentDetailRepository.findById(detail.getPdId());
    //     if(fetchDetail.isPresent()){
    //         if(detail.getEntryStatus() > 0 
    //             && detail.getEntryStatus() != fetchDetail.get().getEntryStatus())
    //             fetchDetail.get().setEntryStatus(detail.getEntryStatus());
    //         return paymentDetailRepository.save(fetchDetail.get());
    //     }
    //     else return null;
    // }
}