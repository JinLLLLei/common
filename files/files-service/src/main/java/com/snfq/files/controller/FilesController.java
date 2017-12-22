/*
 * Powered By SinaFenqi
 * Since 2017 - 2017
 */

package com.snfq.files.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.snfq.base.common.ErrorCodeEnum;
import com.snfq.base.controller.BaseController;
import com.snfq.base.dto.ResultDTO;
import com.snfq.files.domain.FilesDO;
import com.snfq.files.service.FilesService;

@Controller
@RequestMapping("/files")
public class FilesController extends BaseController implements FilesResourceDefinition {

	private static Logger log = LoggerFactory.getLogger(FilesController.class);
	@Resource
	private FilesService filesService;

	@ResponseBody
	@RequestMapping(value = "/temp/path", method = RequestMethod.GET)
	public ResultDTO<String> getTempSavePath() {
		return ResultDTO.ok(filesService.getTempSavePath());
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResultDTO<String> saveFile(@RequestParam("file") MultipartFile file) {
		return filesService.saveFile(file);
	}

	@RequestMapping(value = "/temp/{path:.+}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getTempFile(@PathVariable("path") String path) {
		String fullPath = this.filesService.getTempSavePath() + path;
		File file = new File(fullPath);
		if (!file.exists()) {
			return ResponseEntity.notFound().build();
		}
		try {
			String fileName = FilenameUtils.getName(fullPath);
			int lastIndex = fileName.lastIndexOf("_");
			if (lastIndex > -1) {
				fileName = fileName.substring(lastIndex + 1);
			}
			HttpHeaders headers = new HttpHeaders();
			String downloadFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); // 少了这句，可能导致下载中文文件名的文档，只有后缀名的情况
			headers.setContentDispositionFormData("attachment", downloadFileName);// 告知浏览器以下载方式打开
			headers.setContentType(MediaType.parseMediaType("application/octet-stream; charset=utf-8"));
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);//
		} catch (Exception e) {
			log.error("文件下载异常", e);
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(@PathVariable("id") String id) {
		ResultDTO<FilesDO> result = filesService.getFile(id);
		if (result.isOk()) {
			try {
				FilesDO filesDO = result.getResult();
				File file = new File(filesDO.getPath());
				if (!file.exists()) {
					return ResponseEntity.notFound().build();
				}
				String filename = FilenameUtils.getName(file.getName());
				HttpHeaders headers = new HttpHeaders();
				String downloadFileName = new String(filename.getBytes("UTF-8"), "ISO-8859-1"); // 少了这句，可能导致下载中文文件名的文档，只有后缀名的情况
				headers.setContentDispositionFormData("attachment", downloadFileName);// 告知浏览器以下载方式打开
				headers.setContentType(MediaType.parseMediaType(filesDO.getContentType()));
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);//
			} catch (Exception e) {
				log.error("文件下载异常", e);
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/getImage/{id}", method = RequestMethod.GET)
	public void getImage(@PathVariable("id") String id, HttpServletResponse response) {
		ResultDTO<FilesDO> result = filesService.getFile(id);
		if (result.isOk()) {
			try {
				FilesDO filesDO = result.getResult();
				File file = new File(filesDO.getPath());

				String filename = FilenameUtils.getName(file.getName());
				response.setContentType(filesDO.getContentType());
				ImageIO.write(ImageIO.read(file), filename.substring(filename.lastIndexOf('.')+1, filename.length()),
						response.getOutputStream());

			} catch (Exception e) {
				log.error("文件下载异常", e);

			}
		} else {
		}
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/path", method = RequestMethod.GET)
	public ResultDTO<String> getFilePath(@PathVariable("id") String id) {
		ResultDTO<FilesDO> result = filesService.getFile(id);
		if (!result.isOk()) {
			return ResultDTO.error(ErrorCodeEnum.FILE_NOT_EXISTS_ERROR);
		}
		FilesDO filesDO = result.getResult();
		if (filesDO == null) {
			return ResultDTO.error(ErrorCodeEnum.FILE_NOT_EXISTS_ERROR);
		}
		return ResultDTO.ok(null, filesDO.getPath());
	}
}
