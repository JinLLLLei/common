/*
 * Powered By SinaFenqi
 * Since 2017 - 2017
 */

package com.snfq.files.mapper;

import com.snfq.files.domain.FilesDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 持久化接口
 */
@Mapper
public interface FilesDOMapper {
	FilesDO getByPrimary(String id);
	Long insert(FilesDO filesDO);
}