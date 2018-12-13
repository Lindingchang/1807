package com.jt.manage.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

@Service
public interface FileService {
	PicUploadResult uploadFile(MultipartFile uploadFile);
} 
