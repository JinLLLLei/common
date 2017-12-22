package com.snfq.files.client;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snfq.base.common.ErrorCodeEnum;
import com.snfq.base.dto.ResultDTO;
import com.snfq.base.exception.BizException;

@Component
public class FilesResourceClientStub {
	@Autowired
	private FilesResourceClient filesResourceClient;
	
	/**
	 * 获取文件
	 * @param id
	 * 				文件Id
	 * @return
	 */
	public File getFile(String id) {
		ResultDTO<String> result = filesResourceClient.getFilePath(id);
		if (!result.isOk()) {
			throw new BizException(result);
		}
		String path = result.getResult();
		File file = new File(path);
		if (!file.exists()) {
			throw new BizException(ErrorCodeEnum.FILE_NOT_EXISTS_ERROR);
		}
		return file;
	}
	
	/**
	 * 获取临时文件保存路径
	 * @param path
	 * @return
	 */
	public String getTempFilePath(String path) {
		ResultDTO<String> result =  this.filesResourceClient.getTempSavePath();
		if (!result.isOk()) {
			throw new BizException(result);
		}
		return result.getResult() + path;
	}
	
	/**
	 * 获取文件保存路径
	 * @param path
	 * @return
	 */
	public File getFileByPath(String path) {
		File file = new File(path);
		return file;
	}
}
