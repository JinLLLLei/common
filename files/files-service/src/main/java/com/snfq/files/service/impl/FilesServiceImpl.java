/*
 * Powered By SinaFenqi
 * Since 2017 - 2017
 */

package com.snfq.files.service.impl;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.snfq.files.domain.FilesDO;
import com.snfq.base.common.ErrorCodeEnum;
import com.snfq.base.dto.ResultDTO;
import com.snfq.base.util.UUIDUtils;
import com.snfq.files.mapper.FilesDOMapper;
import com.snfq.files.service.FilesService;

/**
 * 业务逻辑实现
 * @author zeng hua
 */
@Service
public class FilesServiceImpl implements FilesService{
	private static Logger logger = LoggerFactory.getLogger(FilesServiceImpl.class);
	private static final String PATH_FORMATE = "yyyy/MM/dd/";
	@Value("${file.uploadPath}")
	private String basePath;
	@Value("${file.temp.path}")
	private String tempPath;
	@Resource
	private FilesDOMapper filesDOMapper;

	@Override
	public ResultDTO<String> saveFile(MultipartFile file) {
		logger.info("<<-------------文件上传 [name:{}] [size:{}]", file.getOriginalFilename(), file.getSize());
		String savePath = getSavePath(file.getOriginalFilename());
//		String fullPath = new StringBuilder(basePath).append(savePath).toString();
		File dest = new File(basePath, savePath);
		logger.info("save file {}", dest);
		if (!dest.exists()) {
			try {
				if (!dest.getParentFile().exists() ) {
					if ( !dest.getParentFile().mkdirs() ) {
						return ResultDTO.error(ErrorCodeEnum.FILE_PARENT_DIR_CREATE_ERROR);
					}
				}
				file.transferTo(dest);
				
				Date now = new Date();
				FilesDO filesDO = new FilesDO();
				filesDO.setId(UUIDUtils.getUUID());
				filesDO.setContentType(file.getContentType());
				filesDO.setSize(file.getSize());
				filesDO.setPath(savePath);
				filesDO.setCreator("sys");
				filesDO.setGmtCreated(now);
				filesDO.setEditor("sys");
				filesDO.setGmtModified(now);
				filesDOMapper.insert(filesDO);
				
				return ResultDTO.ok("上传成功", filesDO.getId());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("文件上传异常", e);
			}
		} else {
			logger.warn("目标文件已存在[{}]", dest);
		}
		
		return ResultDTO.error(ErrorCodeEnum.FILE_UPLOAD_ERROR);
	}
	
	private String getSavePath(String orginalFilename) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(DateFormatUtils.format(new Date(), PATH_FORMATE));
		sb.append(UUIDUtils.getUUID());
		String ext = FilenameUtils.getExtension(orginalFilename);
		if ( StringUtils.isNotEmpty(ext) ) {
			sb.append(".").append(ext);
		}
		
		return sb.toString();
	}

	@Override
	public ResultDTO<FilesDO> getFile(String id) {
		FilesDO filesDO = filesDOMapper.getByPrimary(id);
		if (filesDO != null) {
			filesDO.setPath(this.basePath+filesDO.getPath());
			return ResultDTO.ok("", filesDO);
		}
		return ResultDTO.error(ErrorCodeEnum.FILE_NOT_EXISTS_ERROR);
	}

	@Override
	public String getTempSavePath() {
		return this.tempPath;
	}
	//TODO 定期清理临时文件，临时文件最多保存1天，超过1天将被清理
}
