/*
 * Powered By SinaFenqi
 * Since 2017 - 2017
 */

package com.snfq.files.service;

import org.springframework.web.multipart.MultipartFile;

import com.snfq.base.dto.ResultDTO;
import com.snfq.files.domain.FilesDO;


/**
 * 业务逻辑接口
 */
public interface FilesService {
	ResultDTO<String> saveFile(MultipartFile file); 
	ResultDTO<FilesDO> getFile(String id);
	String getTempSavePath();
}
