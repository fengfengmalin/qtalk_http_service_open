package com.qunar.qchat.service;

import com.qunar.qchat.protocol.responce.userResponce.GetVCardResult;

import java.util.List;

public interface IUserVcardService {
    GetVCardResult.VCardInfoN getBnbUserInfo(String userid);
    GetVCardResult.VCardInfoN getQCVirtualUserInfo(String userid);
    GetVCardResult.VCardInfoN getQTVirtualUserInfo(String userid);
    List<GetVCardResult.VCardInfoN> getUserInfoByDomain(final String domain,
                                                        final List<String> userids);
}
